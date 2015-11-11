package com.zhongchou.controller;

import java.util.HashMap;
import java.util.Map;

import com.zhongchou.model.User;
import com.zhongchou.tools.Cookies;

public class UserController extends BaseController {

	public void index() {
		login();
	}

	public void login() {
		String cookie = getCookie(Cookies.cookie_domain_name);
		if (cookie != null && cookie != "") {
			int user_id = Cookies.checkCookie(cookie);
			print("\nuser_id = " + user_id);
			if (user_id > 0) {
				// cookie有效
				setAttr("status", "SUCCESS");
				Map map = User.getUserInfo(user_id);
				setAttr("data", map);
				renderJson();
			} else {
				// cookie无效
				render("/user/login.html");
				// setAttr("status", "FAILED");
				// setAttr("reason", "cookie无效");
			}
		} else {
			// cookie不存在
			render("/user/login.html");
			// setAttr("status", "FAILED");
			// setAttr("reason", "cookie不存在");
		}
	}

	/**
	 * 验证用户身份
	 */
	public void authentication() {
		String name = getPara("name");
		String password = getPara("password");
		if (name != null && !name.isEmpty() && password != null && !password.isEmpty()) {
			int user_id = User.me.checkUser(name, password);
			if (user_id > 0) {
				// 返回cookie
				String cookie = Cookies.getCookieValue(user_id, password);
				setCookieHttpOnly(Cookies.cookie_domain_name, cookie, (int) (Cookies.cookie_max_age / 1000));
				setAttr("status", "SUCCESS");
				Map map = User.getUserInfo(user_id);
				setAttr("data", map);
				renderJson();
			} else {
				setAttr("status", "FAILED");
				renderJson();
			}
		}
	}

	public void register() {
		render("/user/register.html");
	}

	public void add() {
		System.out.println(getRequest());

		int user_id = Integer.parseInt(getPara("user_id"));
		String name = getPara("name");
		String password = getPara("password");
		String payment = getPara("payment");
		String ID_number = getPara("ID_number");
		String handl_person = getPara("handle_person");
		String account_bank = getPara("account_bank");
		String bank_account = getPara("bank_account");
		String sex = getPara("sex");
		String type = getPara("type");
		String address = getPara("address");
		String birthday = getPara("birthday");
		String telephone = getPara("telephone");
		String referrer_number = getPara("referrer_number");
		String add_time = getPara("add_time");

		if (User.me.findById(user_id) == null) {
			if (User.me.add(user_id, name, password, payment, ID_number, handl_person, account_bank, bank_account, sex,
					type, address, birthday, telephone, referrer_number, add_time)) {
				// 注册成功
				setAttr("status", "SUCCESS");
			} else {
				// 注册失败
				setAttr("status", "FAILED");
				setAttr("reason", User.USER_INFO_EDIT_ERROR);
			}
		} else {
			// 用户已存在
			setAttr("status", "FAILED");
			setAttr("reason", User.USER_HAS_EXISTED);
		}

		renderJson();
	}

	public void center() {
		render("/user/center.html");
		String cookie = getCookie(Cookies.cookie_domain_name);
		if (cookie != null && cookie != "") {
			int user_id = Cookies.checkCookie(cookie);
			print("\nuser_id = " + user_id);
			if (user_id > 0) {
				// cookie有效
				User user = User.me.findById(user_id);
				setAttr("status", "SUCCESS");
				Map map = new HashMap();
				map.put("user_id", user_id);
				map.put("password", user.get("password"));
				map.put("name", user.get("name"));
				map.put("payment", user.get("payment"));
				map.put("ID_number", user.get("ID_number"));
				map.put("handle_person", user.get("handle_person"));
				map.put("account_bank", user.get("account_bank"));
				map.put("bank_account", user.get("bank_account"));
				map.put("sex", user.get("sex"));
				map.put("type", user.get("type"));
				map.put("address", user.get("address"));
				map.put("birthday", user.get("birthday"));
				map.put("telephone", user.get("telephone"));
				map.put("referrer_number", user.get("referrer_number"));
				map.put("add_time", user.get("add_time"));
				setAttr("data", map);
				renderJson();
			} else {
				// cookie无效
				render("/user/login.html");
			}
		} else {
			// cookie不存在
			render("/user/login.html");
		}
	}

	public void exit() {
		setAttr("status", "SUCCESS");
		renderJson();
		// render("/user/login.html");
	}

	public void help() {
		// render("/user/help.html");
		renderJson("帮助中心");
	}
}
