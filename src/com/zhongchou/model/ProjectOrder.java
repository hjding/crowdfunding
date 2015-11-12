package com.zhongchou.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class ProjectOrder extends Model<ProjectOrder> {
	public static final ProjectOrder dao = new ProjectOrder();

	public List<ProjectOrder> paginate(int project_id, int pageNumber,
			int pageSize) {
		Page<ProjectOrder> page = paginate(pageNumber, pageSize, "select *", "from project_order where project_id = ?",
				project_id);
		if (page.getList() != null) {
			return page.getList();
		} else {
			return null;
		}
	}
}
