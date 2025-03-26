package com.longwang.util;

import java.util.HashMap;
import java.util.Map;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;

public class QQLoginUtil {

	private static String app_id="你的appid";
	private static String app_key = "你的appkey";


	/**
	 * 获取token
	 * @return
	 * @throws QQConnectException 
	 */
	public static String getAccessToken(String code) throws QQConnectException{
		String access_token = "";
		String redirect_url = "回调URL";
		String tokenUrl= String.format("https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s", app_id,app_key,code,redirect_url);//不用动
		//String result = HttpUT.get(tokenUrl).body();
		Map<String, String> tokenMap = getParams("0");
		if(null != tokenMap){
			access_token = tokenMap.get("access_token");
		}
        return access_token;
	}



	/**
	 * 跟据token获取openId
	 * @throws QQConnectException 
	 */
	public static String getOpenId(String accessToken) throws QQConnectException{
		//获取用户openid  
		/*	String openidUrl = String.format("https://graph.qq.com/oauth2.0/me?access_token=%s", accessToken);  
		String openid=HttpUT.get(openidUrl).body();*/

		OpenID openIDObj =  new OpenID(accessToken);
		String openid= openIDObj.getUserOpenID();
		System.out.println("openid:"+openid);
		return openid;
	}


	/**
	 * 跟据openid获取用户信息
	 */
	public static UserInfo getUserInfo(String accessToken ,String openid){

		UserInfo qqUserInfo = new UserInfo(accessToken, openid);

		return qqUserInfo;

	}


	public static Map<String, String> getParams(String urlParams){
		String[] queryStringSplit = urlParams.split("&");
		Map<String,String> queryStringMap =
				new HashMap<String,String>(queryStringSplit.length);
		String[] queryStringParam;
		for (String qs : queryStringSplit) {
			queryStringParam = qs.split("=");
			queryStringMap.put(queryStringParam[0], queryStringParam[1]);
		}
		return queryStringMap;
	}

}
