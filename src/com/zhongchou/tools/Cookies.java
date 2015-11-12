package com.zhongchou.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jodd.util.Base64;

import com.zhongchou.model.User;
import com.zhongchou.run.Constant;

/**
 * 
 * @author Administrator
 * 
 *         cookie 由 cookieName 和 cookieValue 组成 cookieName = CAR_CIRCLE
 *         cookieValue = (userId + ":" + validTime + ":" + cookieValueWithMd5)进行
 *         Base64 加密 userId = 用户的 Id，客户端可以通过对 cookieValue 进行 Base64
 *         解密获得用户的userId validTime = 系统的时间戳 + cookie 的有效时间 cookieValueWithMd5
 *         =(userId + password + validTime + Constants.WEB_KEY) 进行 MD5 加密
 *         password = 进行过解密的用户密码
 */
public class Cookies {

	public static String getCookieValue(int user_id, String password) {

		String userId = Integer.toString(user_id);
		String validTime = Long.toString(System.currentTimeMillis()
				+ Constant.COOKIE_MAX_AGE);
		String cookieValueWithMd5 = stringMD5(userId + password + validTime
				+ Constant.COOKIE_WEB_KEY);

		return Base64.encodeToString(userId + ":" + validTime + ":"
				+ cookieValueWithMd5);
	}

	public static final int checkCookie(String cookie) {

		String[] cookieValueArr = Base64.decodeToString(cookie).split(":");

		if (cookieValueArr.length == 3) {
			System.out.println("cookieValueArr.length = "
					+ cookieValueArr.length);
			Long validTime = Long.parseLong(cookieValueArr[1]);

			System.out.print("validTime = " + validTime);// 1447119457898
			System.out.print("\nSystem.currentTimeMillis() = "
					+ System.currentTimeMillis());

			if (validTime > System.currentTimeMillis()) {
				String userId = cookieValueArr[0];
				int user_id = Integer.parseInt(userId);
				User user = User.dao.findById(user_id);
				if (user != null) {
					String password = user.getStr("password");
					String cookieValueWithMd5Again = stringMD5(userId
							+ password + validTime + Constant.COOKIE_WEB_KEY);
					String cookieValueWithMd5 = cookieValueArr[2];

					System.out.print("\nuser = " + user);
					System.out.print(" user_id = " + user_id);
					System.out.print(" password = " + password);

					System.out.print("\ncookieValueWithMd5 = "
							+ cookieValueWithMd5);
					System.out.print("\ncookieValueWithMd5Again = "
							+ cookieValueWithMd5Again);
					System.out.println("");
					System.out.println(cookieValueWithMd5);
					System.out.println(cookieValueWithMd5Again);

					if (cookieValueWithMd5Again.equals(cookieValueWithMd5)) {
						// cookie验证通过
						return user_id;
					} else {
						// cookie验证错误
						return Constant.COOKIE_AUTHENTICATION_ERROR;
					}
				} else {
					// 找不到用户
					return Constant.COOKIE_NOT_FOUND_USER;
				}
			} else {
				// cookie过期
				return Constant.COOKIE_OVER_DUE;
			}
		} else {
			// cookie Base64解码错误
			return Constant.COOKIE_BASE64_DECODE_ERROR;
		}
	}

	/**
	 * @param cookie
	 * @return user_id or kind of cookie error
	 */
	public static int authenticationCookie(String cookie) {

		if (cookie != null && cookie != "") {
			int user_id = Cookies.checkCookie(cookie);
			System.out
					.println("Cookies.authenticationCookie(String cookie): user_id = "
							+ user_id);
			if (user_id > 0) {
				// cookie valid
				return user_id;

			} else {
				// cookie not valid
				return Constant.COOKIE_NOT_VALID;
			}
		} else {
			// cookie not found
			return Constant.COOKIE_NOT_FOUND;
		}
	}

	public static String stringMD5(String input) {
		try {
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			// 输入的字符串转换成字节数组
			byte[] inputByteArray = input.getBytes();

			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);

			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();

			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);

		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];

		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}

		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

}
