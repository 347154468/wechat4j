package com.iyuexian.wechat4j.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.kit.StringKit;
import com.blade.kit.http.HttpRequest;
import com.blade.kit.json.JSONKit;
import com.blade.kit.json.JSONObject;
import com.iyuexian.wechat4j.WechatException;
import com.iyuexian.wechat4j.WechatMeta;

public class WechatHttpClient {

	public static Logger LOGGER = LoggerFactory.getLogger(WechatHttpClient.class);
	private WechatMeta meta;

	public WechatHttpClient(WechatMeta meta) {
		super();
		this.meta = meta;
	}

	public JSONObject postJSON(String url, JSONObject body) {
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8").header("Cookie", meta.getCookie())
				.send(body.toString());
		String res = request.body();
		request.disconnect();
		if (StringKit.isBlank(res)) {
			throw new WechatException("请求微信接口异常");
		}

		JSONObject jsonObject = JSONKit.parseObject(res);
		JSONObject BaseResponse = jsonObject.get("BaseResponse").asJSONObject();
		if (null == BaseResponse || BaseResponse.getInt("Ret", -1) != 0) {
			LOGGER.warn("操作失败,{}" , jsonObject);
		} 
		return jsonObject;
	}

}
