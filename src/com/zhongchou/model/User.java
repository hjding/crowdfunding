package com.zhongchou.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class User extends Model<User> {
	public static final User me = new User();

	// 分页
	public Page<User> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}

	// 验证
	public int checkUser(String name, String password) {
		String str_sql = "select * from user where name = '" + name + "'" + " and password = '" + password + "';";
		// System.out.println(str_sql);
		List<User> users = User.me.find(str_sql);
		// System.err.println(users);
		if (!users.isEmpty()) {
			for (int i = 0; i < users.size();) {
				User user = users.get(i);
				if (user != null)
					System.out.println(user.getInt("user_id"));
				return user.getInt("user_id");
			}
		}
		return -1;
	}

	// 注册
	public boolean add(int user_id, String name, String password, String payment, String ID_number,
			String handle_person, String account_bank, String bank_account, String sex, String type, String address,
			String birthday, String telephone, String referrer_number, String add_time) {

		Record user = new Record().set("user_id", user_id).set("name", name).set("password", password)
				.set("payment", payment).set("ID_number", ID_number).set("handle_person", handle_person)
				.set("account_bank", account_bank).set("bank_account", bank_account).set("sex", sex).set("type", type)
				.set("address", address).set("birthday", birthday).set("telephone", telephone)
				.set("referrer_number", referrer_number).set("add_time", add_time);
		if (Db.save("user", user)) {
			return true;
		}

		return false;
	}

	public boolean delete() {

		return false;
	}

	public boolean query() {

		return false;
	}

	public boolean edit() {

		return false;
	}

	public static Map getUserInfo(int user_id) {
		User user = me.findById(user_id);

		Map map = new HashMap();
		map.put("user_id", user.get("user_id"));
		map.put("name", user.get("name"));
		map.put("type", user.get("type"));

		return map;
	}

	public static final String USER_HAS_EXISTED = "用户已存在";
	public static final String USER_INFO_EDIT_ERROR = "用户信息填写有误";
}
