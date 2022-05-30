package com.catand.bilibilibot1.plugin;

import com.catand.bilibilibot1.BilibiliBot1Application;
import com.catand.bilibilibot1.JSONUtil.HttpsUtil;
import com.catand.bilibilibot1.JSONUtil.API.BilibiliAPI;
import com.catand.bilibilibot1.JSONUtil.Timer1;
import com.catand.bilibilibot1.JSONUtil.config.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	final static long GROUP = 933121309L;
	final static String[] WEEK = {"日", "一", "二", "三", "四", "五", "六"};
	;
	static Bot bot;
	static File content;
	static BufferedWriter bufferedWriter;
	static MsgUtils sendMsg;
	static Timer1 timer1;
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
	}

	@Override
	public int onGroupMessage(@NotNull Bot bot, @NotNull GroupMessageEvent event) {
		if (Objects.isNull(timer1)) {
			timer1 = new Timer1();
		}
		String messageRaw = event.getRawMessage();
		CatandPlugin.bot = bot;
		if ("视频推荐".equals(messageRaw)) {
			share();
		}
		return MESSAGE_IGNORE;
	}

	public static void share() {
		try {
			//读取本地JSON
			ObjectMapper objectMapper = new ObjectMapper();
			Config config;
			config = objectMapper.readValue(content, new TypeReference<Config>() {
			});

			//选择随机成员
			Random random = new Random();
			CatandPlugin.random = random.nextInt(config.getBilibilis().size());

			//获取哔哩哔哩API数据
			String BV = config.getBilibilis().get(CatandPlugin.random).getUrl();
			sendMsg = bilibiliMsg(BV);
			bot.sendGroupMsg(GROUP, sendMsg.build(), false);
		} catch (Exception e) {
			e.printStackTrace();
			sendMsg = MsgUtils.builder().text("你妈，出错了，快去叫人调试");
			bot.sendGroupMsg(GROUP, sendMsg.build(), false);
		}


	}

	public static MsgUtils bilibiliMsg(String BV) {
		try {
			//从哔哩哔哩API获取数据
			ObjectMapper objectMapper = new ObjectMapper();
			String url = "https://api.bilibili.com/x/web-interface/view?bvid=" + BV;
			byte[] bytes = HttpsUtil.doGet(url);
			String BILIAPIHTTPS = new String(bytes, "utf-8");

			//读取本地JSON
			Config config;
			config = objectMapper.readValue(content, new TypeReference<Config>() {
			});

			//构造消息
			BilibiliAPI bilitAPI;
			bilitAPI = objectMapper.readValue(BILIAPIHTTPS, new TypeReference<BilibiliAPI>() {
			});
			Calendar c = Calendar.getInstance();
			sendMsg = MsgUtils.builder().text(String.format("[%d月%d日星期%s]今天推荐的%s是:",
							c.get(Calendar.MONTH)+1,
							c.get(Calendar.DAY_OF_MONTH),
							WEEK[c.get(Calendar.DAY_OF_WEEK) - 1],
							config.getBilibilis().get(CatandPlugin.random).getType()
					))
					.img(bilitAPI.getData().getPic())
					.text(String.format("\n%s\n" +
									"UP:%s\n" +
									"播放:%s 弹幕:%s\n" +
									"投币:%s 点赞:%s\n" +
									"评论:%s 分享:%s\n" +
									"https://www.bilibili.com/video/%s"
							, bilitAPI.getData().getTitle()
							, bilitAPI.getData().getOwner().getName()
							, bilitAPI.getData().getStat().getView()
							, bilitAPI.getData().getStat().getDanmaku()
							, bilitAPI.getData().getStat().getCoin()
							, bilitAPI.getData().getStat().getLike()
							, bilitAPI.getData().getStat().getReply()
							, bilitAPI.getData().getStat().getShare()
							, BV
					));
			return sendMsg;
		} catch (Exception e) {
			e.printStackTrace();
			MsgUtils sendMsg = MsgUtils.builder().text("你妈，出错了，快去叫人调试");
			return sendMsg;
		}
	}

    /*
    //TODO 临时用来往JSON添加数据
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
