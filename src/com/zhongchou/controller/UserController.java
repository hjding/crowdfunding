package com.zhongchou.controller;

import java.util.Map;

import com.zhongchou.model.User;
import com.zhongchou.run.Constant;
import com.zhongchou.tools.Cookies;

public class UserController extends BaseController {

	public void index() {
		login();
	}

	/**
	 * login
	 * 
	 */
	public void login() {
		Integer user_id = getParaToInt("user_id");
		String password = getPara("password");
		if (user_id != null && password != null && !password.isEmpty()) {
			if (User.dao.checkUser(user_id, password)) {
				// return cookie
				String cookie = Cookies.getCookieValue(user_id, password);
				setCookieHttpOnly(Constant.COOKIE_USER, cookie,
						(int) (Constant.COOKIE_MAX_AGE / 1000));
				setAttr("status", Constant.STATUS_SUCCESS);
				Map<String, String> map = User.dao.query(user_id);
				setAttr("data", map);
			} else {
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_ID_OR_PASSWORD_ERROR);
			}
		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", Constant.USER_ID_OR_PASSWORD_EDIT_ERROR);
		}
		renderJson();
	}

	public void loginWithCookie() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id = Cookies.authenticationCookie(cookie);
		if (user_id > 0) {
			setAttr("status", Constant.STATUS_SUCCESS);
			Map<String, String> map = User.dao.query(user_id);
			setAttr("data", map);
		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id));
		}
		renderJson();
	}

	public void register() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {
				Integer user_id = getParaToInt("user_id");
				String name = getPara("name");
				String password = getPara("password");
				String payment = getPara("payment");
				String ID_number = getPara("ID_number");
				String handle_person = getPara("handle_person");
				String account_bank = getPara("account_bank");
				String bank_account = getPara("bank_account");
				String sex = getPara("sex");
				String type = getPara("type");
				String address = getPara("address");
				String birthday = getPara("birthday");
				String telephone = getPara("telephone");
				String referrer_number = getPara("referrer_number");
				String add_time = getPara("add_time");

				if (user_id != null && (name != null && name != "")
						&& (password != null && password != "")
						&& (payment != null && payment != "")
						&& (ID_number != null && ID_number != "")
						&& (handle_person != null && handle_person != "")
						&& (account_bank != null && account_bank != "")
						&& (bank_account != null && bank_account != "")
						&& (sex != null && sex != "")
						&& (type != null && type != "")
						&& (address != null && address != "")
						&& (birthday != null && birthday != "")
						&& (telephone != null && telephone != "")
						&& (referrer_number != null && referrer_number != "")
						&& (add_time != null && add_time != "")) {
					if (User.dao.findById(user_id) == null) {
						if (User.dao.update(user_id, name, password, payment,
								ID_number, handle_person, account_bank,
								bank_account, sex, type, address, birthday,
								telephone, referrer_number, add_time)) {
							// update success
							setAttr("status", Constant.STATUS_SUCCESS);
						} else {
							// update failed
							setAttr("status", Constant.STATUS_FAILED);
							setAttr("reason", Constant.USER_UPDATE_ERROR);
						}
					} else {
						// user exited
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.USER_HAS_EXISTED);
					}
				} else {
					// user info edit error
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.USER_INFO_EDIT_ERROR);
				}
			} else {
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_PERMISSION_NOT_ENOUGH);
			}
		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id_currentUser));
		}
		renderJson();
	}

	public void edit() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {
				Integer user_id = getParaToInt("user_id");
				String name = getPara("name");
				String password = getPara("password");
				String payment = getPara("payment");
				String ID_number = getPara("ID_number");
				String handle_person = getPara("handle_person");
				String account_bank = getPara("account_bank");
				String bank_account = getPara("bank_account");
				String sex = getPara("sex");
				String type = getPara("type");
				String address = getPara("address");
				String birthday = getPara("birthday");
				String telephone = getPara("telephone");
				String referrer_number = getPara("referrer_number");
				String add_time = getPara("add_time");

				if (user_id != null && (name != null && name != "")
						&& (password != null && password != "")
						&& (payment != null && payment != "")
						&& (ID_number != null && ID_number != "")
						&& (handle_person != null && handle_person != "")
						&& (account_bank != null && account_bank != "")
						&& (bank_account != null && bank_account != "")
						&& (sex != null && sex != "")
						&& (type != null && type != "")
						&& (address != null && address != "")
						&& (birthday != null && birthday != "")
						&& (telephone != null && telephone != "")
						&& (referrer_number != null && referrer_number != "")
						&& (add_time != null && add_time != "")) {
					if (User.dao.findById(user_id) == null) {
						if (User.dao.update(user_id, name, password, payment,
								ID_number, handle_person, account_bank,
								bank_account, sex, type, address, birthday,
								telephone, referrer_number, add_time)) {
							// update success
							setAttr("status", Constant.STATUS_SUCCESS);
						} else {
							// update success
							setAttr("status", Constant.STATUS_FAILED);
							setAttr("reason", Constant.USER_UPDATE_ERROR);
						}
					} else {
						// user exited
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.USER_HAS_EXISTED);
					}
				} else {
					// user info edit error
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.USER_INFO_EDIT_ERROR);
				}
			} else {
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_PERMISSION_NOT_ENOUGH);
			}
		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id_currentUser));
		}
		renderJson();
	}

	public void center() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id = Cookies.authenticationCookie(cookie);
		if (user_id > 0) {
			Map<String, String> map = User.dao.query(user_id);
			if (map != null) {
				setAttr("status", Constant.STATUS_SUCCESS);
				setAttr("data", map);
			} else {
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_INFO_GET_ERROR);
			}

		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id));
		}
		renderJson();
	}

	public void exit() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id = Cookies.authenticationCookie(cookie);
		if (user_id > 0) {
			User user = User.dao.findById(user_id);
			if (user != null) {
				removeCookie(Constant.COOKIE_USER);

				setAttr("status", Constant.STATUS_SUCCESS);
			} else {
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_NOT_FOUND);
			}

		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", Constant.COOKIE_NOT_VALID);
		}
		renderJson();
	}
}
