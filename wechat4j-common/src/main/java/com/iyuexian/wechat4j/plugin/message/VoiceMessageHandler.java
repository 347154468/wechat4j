package com.iyuexian.wechat4j.plugin.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.kit.json.JSONObject;
import com.iyuexian.wechat4j.config.Enums;
import com.iyuexian.wechat4j.core.WechatMeta;

public class VoiceMessageHandler extends AbstractMessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(VoiceMessageHandler.class);

	public VoiceMessageHandler(WechatMeta meta) {
		super(meta);
		this.meta = meta;
	}

	@Override
	public void process(JSONObject msg) {
		LOGGER.info("开始处理语音消息");
		download(msg, Enums.MsgType.VOICE);

	}

}
