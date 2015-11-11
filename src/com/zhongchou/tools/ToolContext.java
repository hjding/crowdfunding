package com.zhongchou.tools;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.zhongchou.model.User;

/**
 * WEB上下文工具类
 * 
 * @author 董华健 2012-9-7 下午1:51:04
 */
public class ToolContext {

	/**
	 * 获取当前登录用户
	 * @param request
	 * @param userAgentVali 是否验证 User-Agent
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request, boolean userAgentVali) {
		String loginCookie = ToolWeb.getCookieValueByName(request, "authmark");
		if (null != loginCookie && !loginCookie.equals("")) {
			// 1. Base64解码cookie令牌
			try {
				loginCookie = ToolString.decode(loginCookie);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 2. 解密cookie令牌
			byte[] securityByte = Base64.decodeBase64(loginCookie);

//			String securityKey = (String) PropertiesPlugin.getParamMapValue(DictKeys.config_securityKey_key);
			String securityKey = "zhongchou.config.securityKey";
			byte[] keyByte = Base64.decodeBase64(securityKey);

			byte[] dataByte = null;
			try {
				dataByte = ToolSecurityIDEA.decrypt(securityByte, keyByte);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String data = new String(dataByte);
			String[] datas = data.split(".#.");	//arr[0]：时间戳，arr[1]：USERID，arr[2]：USER_IP， arr[3]：USER_AGENT
			
			// 3. 获取数据
			long loginDateTimes = Long.parseLong(datas[0]);// 时间戳
			String userIds = datas[1];// 用户id
			String ips = datas[2];// ip地址
			String userAgent = datas[3];// USER_AGENT

			String newIp = ToolWeb.getIpAddr(request);
			String newUserAgent = request.getHeader("User-Agent");

			Date start = ToolDateTime.getDate();
			start.setTime(loginDateTimes);
			int day = ToolDateTime.getDateDaySpace(start, ToolDateTime.getDate());
			
			// 4. 验证数据有效性
			if (ips.equals(newIp) && (userAgentVali ? userAgent.equals(newUserAgent) : true) && day <= 365) {
//				Object userObj = User.dao.cacheGet(userIds);
//				if (null != userObj) {
//					User user = (User) userObj;
//					return user;
//				}
				
			}
		}

		return null;
	}

	/**
	 * 设置当前登录用户
	 * @param request
	 * @param response
	 * @param user
	 * @param autoLogin
	 */
	public static void setCurrentUser(HttpServletRequest request, HttpServletResponse response, User user, boolean autoLogin) {
		// 1.设置cookie有效时间
		int maxAgeTemp = -1;
		if (autoLogin) {
//			maxAgeTemp = ((Integer) PropertiesPlugin.getParamMapValue(DictKeys.config_maxAge_key)).intValue();
			maxAgeTemp = 7 * 24 * 3600 * 1000;//7天有效cookie
		}

		// 2.设置用户名到cookie
		ToolWeb.addCookie(response, "", "/", true, "userName", user.getStr("name"), maxAgeTemp);

		// 3.生成登陆认证cookie
//		String userIds = user.getPKValue();
		String userIds = "1";
		String ips = ToolWeb.getIpAddr(request);
		String userAgent = request.getHeader("User-Agent");
		long date = ToolDateTime.getDateByTime();

		StringBuilder token = new StringBuilder();// 时间戳#USERID#USER_IP#USER_AGENT
		token.append(date).append(".#.").append(userIds).append(".#.").append(ips).append(".#.").append(userAgent);
		String authToken = token.toString();
		byte[] authTokenByte = null;
		try {
			authTokenByte = authToken.getBytes(ToolString.encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		String securityKey = (String) PropertiesPlugin.getParamMapValue(DictKeys.config_securityKey_key);
		String securityKey = "zhongchou.config.securityKey";
		byte[] keyByte = Base64.decodeBase64(securityKey);

		// 4. 认证cookie加密
		byte[] securityByte = null;
		try {
			securityByte = ToolSecurityIDEA.encrypt(authTokenByte, keyByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String securityCookie = Base64.encodeBase64String(securityByte);

		// 5. 认证cookie Base64编码
		try {
			securityCookie = ToolString.encode(securityCookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 6. 添加到Cookie
		ToolWeb.addCookie(response,  "", "/", true, "zhongchou.authmark", securityCookie, maxAgeTemp);
	}
}
