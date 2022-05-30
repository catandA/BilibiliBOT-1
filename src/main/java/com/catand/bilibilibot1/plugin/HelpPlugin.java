package com.catand.bilibilibot1.plugin;

import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class HelpPlugin extends BotPlugin {
	MsgUtils sendMsg;
	@Override
	public int onGroupMessage(@NotNull Bot bot, @NotNull GroupMessageEvent event) {
		if(event.getRawMessage().contains("帮助")||event.getRawMessage().contains("help")){
			sendMsg = MsgUtils.builder().text("这是catand的机器人");
			bot.sendGroupMsg(event.getGroupId(), sendMsg.build(), false);
		}
		return MESSAGE_IGNORE;
	}
}