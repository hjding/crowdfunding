package com.zhongchou.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhongchou.run.Constant;

@SuppressWarnings("serial")
public class ProjectOrder extends Model<ProjectOrder> {
	public static final ProjectOrder dao = new ProjectOrder();

	public List<ProjectOrder> paginate(int project_id, int pageNumber,
			int pageSize) {
		Page<ProjectOrder> page = paginate(pageNumber, pageSize, "select *",
				"from project_order where project_id = ?", project_id);
		if (page.getList() != null) {
			return page.getList();
		} else {
			return null;
		}
	}

	public String add(int project_id, int user_id, String order_date,
			String order_content, String order_type) {
		if (Integer.valueOf(project_id) != null) {
			if (Integer.valueOf(user_id) != null) {
				if (order_date != null && order_date != "") {
					if (order_content != null && order_content != "") {
						if (order_type != null && order_type != "") {
							Map<String, Object> map = new HashMap<>();
							map.put("project_id", project_id);
							map.put("user_id", user_id);
							map.put("order_date", order_date);
							map.put("order_content", order_content);
							map.put("order_type", order_type);
							if (new ProjectOrder().setAttrs(map).save()) {
								return Constant.STATUS_SUCCESS;
							} else {
								return Constant.ORDER_INSERT_FAILED;
							}
						} else {
							return Constant.ORDER_TYPE_IS_NULL;
						}
					} else {
						return Constant.ORDER_CONTENT_IS_NULL;
					}
				} else {
					return Constant.ORDER_DATE_IS_NULL;
				}
			} else {
				return Constant.USER_ID_IS_NULL;
			}
		} else {
			return Constant.PROJECT_ID_IS_NULL;
		}
	}

	public String delete(int project_id, int user_id) {
		if (Integer.valueOf(project_id) != null) {
			if (Integer.valueOf(user_id) != null) {
				if (dao.deleteById(project_id, user_id)) {
					return Constant.STATUS_SUCCESS;
				} else {
					return Constant.ORDER_DELETE_FAILED;
				}
			} else {
				return Constant.USER_ID_IS_NULL;
			}
		} else {
			return Constant.PROJECT_ID_IS_NULL;
		}
	}

	public Map<String, Object> query(int project_id, int user_id) {
		Map<String, Object> map = new HashMap<>();
		if (Integer.valueOf(project_id) != null) {
			if (Integer.valueOf(user_id) != null) {
				ProjectOrder order = dao.findById(project_id, user_id);
				if (order != null) {
					map.put("order_date", order.get("order_date"));
					map.put("order_content", order.get("order_content"));
					map.put("order_type", order.get("order_type"));
					map.put("user_name", User.dao.findById(user_id).get("user_name"));
					map.put("project_name", Project.dao.findById(project_id).get("project_name"));
					return map;
				} else {
					map.put("reason", Constant.ORDER_QUERY_FAILED);
					return map;
				}
			} else {
				map.put("reason", Constant.USER_ID_IS_NULL);
				return map;
			}
		} else {
			map.put("reason", Constant.PROJECT_ID_IS_NULL);
			return map;
		}
	}
	
	public String update(int project_id, int user_id, String order_date,
			String order_content, String order_type) {
		if (Integer.valueOf(project_id) != null) {
			if (Integer.valueOf(user_id) != null) {
				if (order_date != null && order_date != "") {
					if (order_content != null && order_content != "") {
						if (order_type != null && order_type != "") {
							Map<String, Object> map = new HashMap<>();
							map.put("order_date", order_date);
							map.put("order_content", order_content);
							map.put("order_type", order_type);
							if (new ProjectOrder().setAttrs(map).save()) {
								return Constant.STATUS_SUCCESS;
							} else {
								return Constant.ORDER_UPDATE_FAILED;
							}
						} else {
							return Constant.ORDER_TYPE_IS_NULL;
						}
					} else {
						return Constant.ORDER_CONTENT_IS_NULL;
					}
				} else {
					return Constant.ORDER_DATE_IS_NULL;
				}
			} else {
				return Constant.USER_ID_IS_NULL;
			}
		} else {
			return Constant.PROJECT_ID_IS_NULL;
		}
	}
}
