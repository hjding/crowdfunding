package com.zhongchou.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class Project extends Model<Project> {
	public static final Project me = new Project();

	// 分页
	public Page<Project> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *",
				"from project order by project_id asc");
	}

	public boolean add(String name, String introduce_text,
			String saveDirectory, String predict_profit) {

		Record project = new Record()
				.set("name", name)
				.set("introduce_text",
						introduce_text == null ? "" : introduce_text)
				.set("introduce_pic", saveDirectory)
				.set("predict_profit", predict_profit);

		if (Db.save("project", project)) {
			return true;
		}
		return false;
	}

	public static Project getProjectByProject_id(int project_id) {
		String str_sql = "select * from project where project_id = "
				+ project_id + ";";
		System.out.println(str_sql);
		List<Project> projects = me.find(str_sql);
		if (projects.size() == 1) {
			Project project = projects.get(0);
			return project;
		}

		return null;
	}

	public static Map getProjectInfo(int project_id) {
		String str_sql = "select * from project where project_id = "
				+ project_id + ";";
		System.out.println(str_sql);
		List<Project> projects = me.find(str_sql);

		if (!projects.isEmpty()) {
			Project project = projects.get(0);
			Map map = new HashMap();
			map.put("project_id", project.get("project_id"));
			map.put("introduce_text", project.get("introduce_text"));
			map.put("introduce_pic", project.get("introduce_pic"));
			map.put("predict_profit", project.get("predict_profit"));
			map.put("actual_profit", project.get("actual_profit"));

			return map;
		}

		return null;
	}
}
