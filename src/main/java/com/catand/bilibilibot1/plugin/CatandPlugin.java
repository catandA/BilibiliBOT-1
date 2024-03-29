package com.catand.bilibilibot1.plugin;

import com.catand.bilibilibot1.BotApplication;
import com.catand.bilibilibot1.util.BilibiliUtil;
import com.catand.bilibilibot1.util.DayTimer;
import com.catand.bilibilibot1.util.json.config.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Component
public class CatandPlugin extends BotPlugin {

	public final static Pattern bilibiliUrlPattern = Pattern.compile("(http:)?(https:)?(//)?((([a-zA-Z0-9_-])+(\\.)?){1,2}\\.)?(bilibili.com)+(:\\d+)?(/((\\.)?(\\?)?=?&?%?[#!a-zA-Z0-9_-](\\?)?)*)*$");

    private final static String[] WEEK = {"日", "一", "二", "三", "四", "五", "六"};
	private static Bot bot;
	private static final File content;
	private static final DayTimer DAY_TIMER = new DayTimer();
	private static int random;

    static {
        // get bot jar dir
//		String path = System.getProperty("java.class.path");
//		int lastIndex = path.lastIndexOf(File.separator) + 1;
//		path = path.substring(0, lastIndex); // can replace with File.getAbsolutePath();
        String pathToConfig = System.getProperty("configFile", new File("content.json").getAbsolutePath());

        // check config.json is exists, if not, exit the application
        content = new File(pathToConfig);
        System.out.println("Load config from file: " + content);
        if (!content.exists()) {
			// TODO 自动创建json
            BotApplication.getInstance().exitApplication("未找到配置文件\"content.json\" 请添加后重新启动");
        }
        DAY_TIMER.TimerManager();
    }

    public static void share() {
        try {
            //读取本地JSON
            ObjectMapper objectMapper = new ObjectMapper();
            Config config;
            config = objectMapper.readValue(CatandPlugin.content, new TypeReference<Config>() {
            });

			List<String> targetGroups = config.getTargetGroups(); // 目标群组

            //选择随机成员
            Random random = new Random();
            CatandPlugin.random = random.nextInt(config.getBilibiliVideos().size());
            String BvId = config.getBilibiliVideos().get(CatandPlugin.random).getUrl();

            //构造消息头部
            Calendar c = Calendar.getInstance();
			MsgUtils sendMsg = MsgUtils.builder().text(String.format("[%d月%d日星期%s]今天推荐的%s是:",
					c.get(Calendar.MONTH) + 1,
					c.get(Calendar.DAY_OF_MONTH),
					WEEK[c.get(Calendar.DAY_OF_WEEK) - 1],
					config.getBilibiliVideos().get(CatandPlugin.random).getType()
			));

            //获取视频详情并拼接消息
            String msg = sendMsg.build() + BilibiliUtil.bilibiliFormatMsgBuilder(BvId).build();
			targetGroups.forEach(targetGroup -> bot.sendGroupMsg(Long.parseLong(targetGroup), msg, false));

        } catch (Exception e) {
            e.printStackTrace();
			System.out.println("获取时发生错误, 请检查配置文件是否配置正确");
        }
    }

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull GroupMessageEvent event) {
        String messageRaw = event.getRawMessage();
        CatandPlugin.bot = bot;
        if ("视频推荐".equals(messageRaw)) {
            share();
        }

        // bilibili直链解析
//        if (messageRaw.contains(BilibiliUtil.BILI_LINK_IDENTIFIER)) {
//            sendMsg = BilibiliUtil.getbilibiliFormatMsgFromLink(messageRaw);
//            bot.sendGroupMsg(event.getGroupId(), sendMsg.build(), false);
//        }

		// 使用正则匹配Bilibili链接

		for (String videoLink : bilibiliUrlPattern.split(messageRaw)) {
			MsgUtils sendMsg;
			try {
				sendMsg = BilibiliUtil.getbilibiliFormatMsgFromLink(messageRaw);
			} catch (Throwable error) {
				error.printStackTrace();
				sendMsg = new MsgUtils();
				sendMsg.text(videoLink + " 或许不是一个正确的Bilibili视频链接");
			}
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
