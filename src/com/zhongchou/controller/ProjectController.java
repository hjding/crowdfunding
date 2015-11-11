package com.zhongchou.controller;

import java.util.Map;

import com.zhongchou.model.Project;
import com.zhongchou.run.UpFileRenamePolicy;
import com.zhongchou.tools.Cookies;

public class ProjectController extends BaseController {

	public void index() {
		render("/index/index.html");
	}

	public void add() {
		render("/project/add.html");
	}

	public void addProject() {
		String cookie = getCookie(Cookies.cookie_domain_name);
		if (cookie != null && cookie != "") {
			int user_id = Cookies.checkCookie(cookie);
			print("\nuser_id = " + user_id);
			if (user_id > 0) {
				// cookie有效
				getFile("introduce_pic");
				String saveDirectory = UpFileRenamePolicy.relative_directory;
				String name = getPara("name");
				String introduce_text = getPara("introduce_text");
				String predict_profit = getPara("predict_profit");

				if (Project.me.add(name, introduce_text, saveDirectory,
						predict_profit)) {
					setAttr("status", "SUCCESS");
				} else {
					setAttr("status", "FAILED");
				}

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

	public void edit() {
		// render("/project/edit.html");
		String project_id = getPara("project_id");
		Map map = Project.getProjectInfo(Integer.parseInt(project_id));
		if (map != null) {
			setAttr("status", "SUCCESS");
			setAttr("data", map);
		} else {
			setAttr("status", "FAILED");
			setAttr("reason", "未找到项目");
		}

		renderJson();
	}

	public void editProject() {
		String project_id = getPara("project_id");
		String name = getPara("name");
		String introduce_text = getPara("introduce_text");
		String introduce_pic = getPara("introduce_pic");
		String predict_profit = getPara("predict_profit");
		String actual_profit = getPara("actual_profit");

		Project project = Project.getProjectByProject_id(Integer
				.parseInt(project_id));

		project.set("name", name).set("introduce_pic", introduce_pic)
				.set("introduce_text", introduce_text)
				.set("predict_profit", predict_profit)
				.set("actual_profit", actual_profit);
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
