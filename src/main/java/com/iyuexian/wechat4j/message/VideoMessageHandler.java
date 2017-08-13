package com.iyuexian.wechat4j.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.kit.json.JSONObject;
import com.iyuexian.wechat4j.model.WechatMeta;

public class VideoMessageHandler extends AbstractMessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoMessageHandler.class);

	public VideoMessageHandler(WechatMeta meta) {
		super(meta);
		this.meta = meta;
	}

	@Override
	public void process(JSONObject msg) {
		LOGGER.info("开始处理视频消息");
		download(msg, MsgType.VIDEO);
	}



}
