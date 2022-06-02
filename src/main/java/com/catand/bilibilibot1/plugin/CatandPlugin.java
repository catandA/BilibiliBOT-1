package com.catand.bilibilibot1.plugin;

import com.catand.bilibilibot1.BilibiliBot1Application;
import com.catand.bilibilibot1.Util.BilibiliUtil;
import com.catand.bilibilibot1.Util.JSONUtil.JSONUtil;
import com.catand.bilibilibot1.Util.Timer1;
import com.catand.bilibilibot1.Util.JSONUtil.config.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikuac.shiro.bean.MsgChainBean;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class CatandPlugin extends BotPlugin {

	//TODO 使用外部配置文件来指定目标发送群聊
	final static long GROUP = 933121309L;
	final static long GROUPTEST = 180901798L;

	final static String[] WEEK = {"日", "一", "二", "三", "四", "五", "六"};
	static Bot bot;
	static File content;
	static BufferedWriter bufferedWriter;
	static MsgUtils sendMsg;
	static Timer1 timer1 = new Timer1();
	static int random;

	static {
		//获取jar当前路径
		String path = System.getProperty("java.class.path");
		int lastIndex = path.lastIndexOf(File.separator) + 1;
		path = path.substring(0, lastIndex);

		//检验config.json
		content = new File(path + "content.json");
		System.out.println(content);
		if (!content.exists()) {
			System.out.println("未找到配置文件\"content.json\"，请添加后重新启动");
			BilibiliBot1Application.exitApplication();
		}
		timer1.TimerManager();
	}

	@Override
	public int onGroupMessage(@NotNull Bot bot, @NotNull GroupMessageEvent event) {
		String messageRaw = event.getRawMessage();
		CatandPlugin.bot = bot;
		if ("视频推荐".equals(messageRaw)) {
			share();
		}

		//bilibili直链解析
		if (messageRaw.contains(BilibiliUtil.BILI_LINK_IDENTIFIER)) {
			sendMsg = BilibiliUtil.getbilibiliFormatMsgFromLink(messageRaw);
			bot.sendGroupMsg(event.getGroupId(), sendMsg.build(), false);
		}

		//TODO bilibili站外分享解析
/*
		if (messageRaw.contains("com.tencent.miniapp_01") && messageRaw.contains("哔哩哔哩")) {
			MsgChainBean msgChainBean = event.getArrayMsg().get(1);
			if (msgChainBean.getType().equals("json")) {
				String map = msgChainBean.getData().toString();
				String JSON = map.substring(6,map.length() - 1);
				if (JSONUtil.isValidJSON(JSON)) {
					sendMsg = BilibiliUtil.getbilibiliFormatMsgFromMiniappJSON(JSON);
					bot.sendGroupMsg(event.getGroupId(), sendMsg.build(), false);
				}
			}
		}
*/

		return MESSAGE_IGNORE;
	}

	public static void share() {
		try {
			//读取本地JSON
			ObjectMapper objectMapper = new ObjectMapper();
			Config config;
			config = objectMapper.readValue(CatandPlugin.content, new TypeReference<Config>() {
			});

			//选择随机成员
			Random random = new Random();
			CatandPlugin.random = random.nextInt(config.getBilibilis().size());
			String BV = config.getBilibilis().get(CatandPlugin.random).getUrl();

			//构造消息头部
			Calendar c = Calendar.getInstance();
			sendMsg = MsgUtils.builder().text(String.format("[%d月%d日星期%s]今天推荐的%s是:",
					c.get(Calendar.MONTH) + 1,
					c.get(Calendar.DAY_OF_MONTH),
					WEEK[c.get(Calendar.DAY_OF_WEEK) - 1],
					config.getBilibilis().get(CatandPlugin.random).getType()
			));

			//获取视频详情并拼接消息
			StringBuffer sb = new StringBuffer(sendMsg.build()).append(BilibiliUtil.bilibiliFormatMsgBuilder(BV).build());
			String msg = String.valueOf(sb);
			bot.sendGroupMsg(GROUP, msg, false);

		} catch (Exception e) {
			e.printStackTrace();
			sendMsg = MsgUtils.builder().text("出错了，快去叫人调试");
			bot.sendGroupMsg(GROUP, sendMsg.build(), false);
		}

	}
	/*
    //TODO 从QQ往JSON添加数据
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull PrivateMessageEvent event) {
        String messageRaw = event.getRawMessage();
        if (event.getUserId() == 779038666L) {
            int index1;
            int index2;
            index1 = messageRaw.indexOf("\t");
            index2 = messageRaw.indexOf("\t", index1 + 1);
            String name = messageRaw.substring(0, index1);
            System.out.print(name + "\n");
            String type = messageRaw.substring(index1 + 1, index2);
            System.out.print(type + "\n");
            String url = messageRaw.substring(index2 + 1);
            url = url.substring(31, 43);
            System.out.print(url + "\n");
            Bilibili bilibili = new Bilibili(name, type, url);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Config config;
                config = objectMapper.readValue(content, new TypeReference<Config>() {
                });
                config.getBilibilis().add(bilibili);

                bufferedWriter = new BufferedWriter(new FileWriter(content, false));
                bufferedWriter.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(config));
                bufferedWriter.flush();
                bufferedWriter.close();

                sendMsg = MsgUtils.builder().text("ok");
                bot.sendPrivateMsg(event.getUserId(), sendMsg.build(), false);
            } catch (Exception e) {
                e.printStackTrace();
                sendMsg = MsgUtils.builder().text("你妈,添加出BUG了,快去控制台看看日志");
                bot.sendPrivateMsg(event.getUserId(), sendMsg.build(), false);
                return MESSAGE_BLOCK;
            }
        }
        return MESSAGE_IGNORE;
    }
     */
}
