package com.catand.bilibilibot1.Util;

import com.catand.bilibilibot1.Util.JSONUtil.API.BilibiliJSONClass;
import com.catand.bilibilibot1.Util.JSONUtil.API.BilibiliMiniapp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikuac.shiro.common.utils.MsgUtils;

public class BilibiliUtil {
	public static final String BILI_LINK_IDENTIFIER = "bilibili.com/video/BV";

	public static MsgUtils bilibiliFormatMsgBuilder(String BV) {
		MsgUtils sendMsg;
		try {
			//构造消息
			BilibiliJSONClass bilitAPI;
			bilitAPI = bilibiliAPIJSONLoader(getBilibiliAPIStringFromBV(BV));
			sendMsg = MsgUtils.builder()
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
			sendMsg = MsgUtils.builder().text("出错了，快去叫人调试");
			return sendMsg;
		}
	}

	public static String getBilibiliAPIStringFromBV(String BV) {
		try {
			//从哔哩哔哩API获取数据
			String url = "https://api.bilibili.com/x/web-interface/view?bvid=" + BV;
			byte[] bytes = HttpsUtil.doGet(url);
			String BILIAPIHTTPS = new String(bytes, "utf-8");
			return BILIAPIHTTPS;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static BilibiliJSONClass bilibiliAPIJSONLoader(String JSON) {
		try {
			//构造消息
			ObjectMapper objectMapper = new ObjectMapper();
			BilibiliJSONClass bilitAPI;
			bilitAPI = objectMapper.readValue(JSON, new TypeReference<BilibiliJSONClass>() {
			});
			return bilitAPI;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String findBVFromMiniappLink(String miniappLink) {
		try {
			String link = HttpsUtil.doGetHeaderLocation(miniappLink);
			return findBVFromLink(link);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String findMiniappLinkFromMiniappJSON(String JSON) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			BilibiliMiniapp bilibiliMiniapp;
			bilibiliMiniapp = objectMapper.readValue(JSON, new TypeReference<BilibiliMiniapp>() {
			});
			return bilibiliMiniapp.getMeta().getDetail_1().getQqdocurl();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String findBVFromLink(String Link) {
		int BVIndex = Link.indexOf(BILI_LINK_IDENTIFIER) + 19;
		return Link.substring(BVIndex, BVIndex + 12);
	}

	public static String findBVFromMiniappJSON(String JSON) {
		String miniappLink = findMiniappLinkFromMiniappJSON(JSON);
		return findBVFromMiniappLink(miniappLink);
	}

	public static MsgUtils getbilibiliFormatMsgFromMiniappJSON(String JSON) {
		return bilibiliFormatMsgBuilder(findBVFromMiniappJSON(JSON));
	}

	public static MsgUtils getbilibiliFormatMsgFromLink(String Link) {
		return bilibiliFormatMsgBuilder(findBVFromLink(Link));
	}
}
