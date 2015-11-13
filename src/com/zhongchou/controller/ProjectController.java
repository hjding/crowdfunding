package com.zhongchou.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhongchou.model.Project;
import com.zhongchou.model.ProjectOrder;
import com.zhongchou.model.User;
import com.zhongchou.run.Constant;
import com.zhongchou.run.UpFileRenamePolicy;
import com.zhongchou.tools.Cookies;

public class ProjectController extends BaseController {

	public void index() {
		list();
	}

	public void add() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {

				getFile("introduce_pic");
				String name = getPara("name");
				String introduce_text = getPara("introduce_text");
				String saveDirectory = UpFileRenamePolicy.relative_directory;
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
						setAttr("reason", Constant.PROJECT_SAVE_ERROR);
					}
				} else {
					// info edit error
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.PROJECT_INFO_EDIT_ERROR);
				}
			} else {
				// user permission not enough
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_PERMISSION_NOT_ENOUGH);
			}
		} else {
			// cookie error code
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id_currentUser));
		}
		renderJson();
	}

	public void delete() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {

				String project_id = getPara("project_id");
				if (project_id != null && project_id != "") {
					if (Project.dao.delete()) {
						setAttr("status", Constant.STATUS_SUCCESS);
					} else {
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.PROJECT_DELETE_ERROR);
					}
				} else {
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.PROJECT_ID_EDIT_ERROR);
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

	public void query() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {

				String project_id = getPara("project_id");
				if (project_id != null && project_id != "") {
					Map<String, String> map = Project.dao.query(Integer
							.parseInt(project_id));
					if (map != null) {
						setAttr("status", Constant.STATUS_SUCCESS);
						setAttr("data", map);
					} else {
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.PROJECT_QUERY_ERROR);
					}
				} else {
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.PROJECT_ID_EDIT_ERROR);
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

	public void list() {
		String pageNubmer = getPara("pageNumber");
		String pageSize = getPara("pageSize");
		if (pageNubmer != null && pageNubmer != "" && pageSize != null
				&& pageSize != "") {
			List<Project> projects = Project.dao.paginate(
					Integer.parseInt(pageNubmer), Integer.parseInt(pageSize));
			if (projects != null && projects.size() > 0) {
				Map<String, List<Project>> map = new HashMap<>();
				map.put("projects", projects);
				setAttr("status", Constant.STATUS_SUCCESS);
				setAttr("data", map);
			} else {
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.PROJECT_LIST_ERROR);
			}
		} else {
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", Constant.PROJECT_LIST_PARA_ERROR);
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
				getFile("introduce_pic");
				String project_id = getPara("project_id");
				String name = getPara("name");
				String introduce_text = getPara("introduce_text");
				String saveDirectory = UpFileRenamePolicy.relative_directory;
				String predict_profit = getPara("predict_profit");
				String actual_profit = getPara("actual_profit");

				if ((name != null && name != "")
						&& (introduce_text != null && introduce_text != "")
						&& (saveDirectory != null && saveDirectory != "")
						&& (predict_profit != null && predict_profit != "")) {

					if (Project.dao.update(Integer.parseInt(project_id), name,
							introduce_text, saveDirectory, predict_profit,
							actual_profit)) {
						// edit success
						setAttr("status", Constant.STATUS_SUCCESS);
					} else {
						// edit failed
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.PROJECT_UPDATE_ERROR);
					}
				} else {
					// info edit error
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.PROJECT_INFO_EDIT_ERROR);
				}
			} else {
				// user permission not enough
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_PERMISSION_NOT_ENOUGH);
			}
		} else {
			// cookie error code
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id_currentUser));
		}
		renderJson();
	}

	public void order() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {

				String project_id = getPara("project_id");
				String pageNubmer = getPara("pageNumber");
				String pageSize = getPara("pageSize");
				if (project_id != null && project_id != ""
						&& pageNubmer != null && pageNubmer != ""
						&& pageSize != null && pageSize != "") {
					List<ProjectOrder> projectOrders = ProjectOrder.dao
							.paginate(Integer.parseInt(project_id),
									Integer.parseInt(pageNubmer),
									Integer.parseInt(pageSize));
					if (projectOrders != null && projectOrders.size() > 0) {
						Map<String, List<ProjectOrder>> map = new HashMap<>();
						map.put("projectOrders", projectOrders);
						setAttr("status", Constant.STATUS_SUCCESS);
						setAttr("data", map);
					} else {
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.PROJECT_ORDER_LIST_ERROR);
					}
				} else {
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.PROJECT_ORDER_PARA_ERROR);
				}
			} else {
				// user permission not enough
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_PERMISSION_NOT_ENOUGH);
			}
		} else {
			// cookie error code
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id_currentUser));
		}
		renderJson();
	}

	public void profitDistribution() {
		String cookie = getCookie(Constant.COOKIE_USER);
		int user_id_currentUser = Cookies.authenticationCookie(cookie);
		if (user_id_currentUser > 0) {
			User user = User.dao.findByIdLoadColumns(user_id_currentUser,
					"type");
			String type_currentUser = user.get("type");
			if (type_currentUser.equals("砖石会员")) {

				String project_id = getPara("project_id");
				String pageNubmer = getPara("pageNumber");
				String pageSize = getPara("pageSize");
				if (project_id != null && project_id != ""
						&& pageNubmer != null && pageNubmer != ""
						&& pageSize != null && pageSize != "") {
					List<ProjectOrder> projectOrders = ProjectOrder.dao
							.paginate(Integer.parseInt(project_id),
									Integer.parseInt(pageNubmer),
									Integer.parseInt(pageSize));
					if (projectOrders != null && projectOrders.size() > 0) {
						Map<String, List<ProjectOrder>> map = new HashMap<>();
						map.put("projectOrders", projectOrders);
						setAttr("status", Constant.STATUS_SUCCESS);
						setAttr("data", map);
					} else {
						setAttr("status", Constant.STATUS_FAILED);
						setAttr("reason", Constant.PROJECT_ORDER_LIST_ERROR);
					}
				} else {
					setAttr("status", Constant.STATUS_FAILED);
					setAttr("reason", Constant.PROJECT_ORDER_PARA_ERROR);
				}
			} else {
				// user permission not enough
				setAttr("status", Constant.STATUS_FAILED);
				setAttr("reason", Constant.USER_PERMISSION_NOT_ENOUGH);
			}
		} else {
			// cookie error code
			setAttr("status", Constant.STATUS_FAILED);
			setAttr("reason", String.valueOf(user_id_currentUser));
		}
		renderJson();
	}
}
