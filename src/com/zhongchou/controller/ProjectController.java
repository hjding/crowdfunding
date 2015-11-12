package com.zhongchou.controller;

import java.util.Map;

import com.zhongchou.model.Project;
import com.zhongchou.model.User;
import com.zhongchou.run.Constant;
import com.zhongchou.run.UpFileRenamePolicy;
import com.zhongchou.tools.Cookies;

public class ProjectController extends BaseController {

	public void index() {
		render("/index/index.html");
	}

	public void add() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {
				String name = getPara("name");
				String introduce_text = getPara("introduce_text");
				String saveDirectory = getPara("saveDirectory");
				String predict_profit = getPara("predict_profit");
				if ((name != null && name != "")
						&& (introduce_text != null && introduce_text != "")
						&& (saveDirectory != null && saveDirectory != "")
						&& (predict_profit != null && predict_profit != "")) {

					if (Project.dao.add(name, introduce_text, saveDirectory,
							predict_profit)) {
						// add success
						setAttr("status", Constant.STATUS_SUCCESS);
					} else {
						// add failed
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.USER_UPDATE_ERROR);
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
		String project_id = getPara("project_id");
		Map<String, String> map = Project.dao.query(Integer
				.parseInt(project_id));
		if (map != null) {
			setAttr("status", "SUCCESS");
			setAttr("data", map);
		} else {
			setAttr("status", "FAILED");
			setAttr("reason", "未找到项目");
		}

		renderJson();
	}

	public void order() {
		render("/project/order.html");
	}

	public void list() {
		render("/project/list.html");
	}

	public void profit_distribution() {
		render("/project/profit_distribution.html");
	}
}
