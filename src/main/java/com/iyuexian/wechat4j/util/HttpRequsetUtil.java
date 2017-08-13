package com.iyuexian.wechat4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blade.kit.StringKit;
import com.blade.kit.http.HttpRequest;
import com.blade.kit.json.JSONKit;
import com.blade.kit.json.JSONObject;
import com.iyuexian.wechat4j.exception.WechatException;
import com.iyuexian.wechat4j.model.WechatMeta;

public class HttpRequsetUtil {

	public static Logger LOGGER = LoggerFactory.getLogger(HttpRequsetUtil.class);
	private WechatMeta meta;

	public HttpRequsetUtil(WechatMeta meta) {
		super();
		this.meta = meta;
	}

	public JSONObject postJSON(String url, JSONObject body) {
		LOGGER.debug("begin request,the request url is:{}", url);
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8").header("Cookie", meta.getCookie())
				.send(body.toString());
		String res = request.body();
		LOGGER.debug("response from  wechat:{}", res);
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
