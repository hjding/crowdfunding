package com.zhongchou.model;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Project extends Model<Project> {

	public static final Project dao = new Project();

	/**
	 * paginate
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Project> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *",
				"from project order by project_id asc");
	}

	public boolean add(String name, String introduce_text,
			String saveDirectory, String predict_profit) {
		if (new Project().set("name", name)
				.set("introduce_text", introduce_text)
				.set("introduce_pic", saveDirectory)
				.set("predict_profit", predict_profit).save()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * delete by project_id
	 * 
	 * @param project_id
	 * @return
	 */
	public boolean delete(int project_id) {
		if (dao.deleteById(project_id)) {
			return true;
		}
		return false;
	}

	public Map<String, String> query(int project_id) {
		Project project = dao.findById(project_id);
		if (project != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("project_id", String.valueOf(project.get("project_id")));
			map.put("name", String.valueOf(project.get("name")));
			map.put("introduce_text",
					String.valueOf(project.get("introduce_text")));
			map.put("introduce_pic",
					String.valueOf(project.get("introduce_pic")));
			map.put("predict_profit",
					String.valueOf(project.get("predict_profit")));
			map.put("actual_profit",
					String.valueOf(project.get("actual_profit")));

			return map;
		} else {
			return null;
		}
	}

	public boolean update(int project_id, String name, String introduce_text,
			String saveDirectory, String predict_profit, String actual_profit) {
		if (dao.findById(project_id).set("name", name).set("name", name)
				.set("introduce_text", introduce_text)
				.set("introduce_pic", saveDirectory)
				.set("predict_profit", predict_profit)
				.set("actual_profit", actual_profit).update()) {
			return true;
		} else {
			return false;
		}
	}
}
