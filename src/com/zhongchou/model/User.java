package com.zhongchou.model;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class User extends Model<User> {
	/**
	 * database access object : dao
	 */
	public static final User dao = new User();

	/**
	 * paginate
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<User> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *",
				"from user order by user_id asc");
	}

	/**
	 * check if user_id and password is right
	 * 
	 * @param user_id
	 * @param password
	 * @return
	 */
	public boolean checkUser(int user_id, String password) {
		String str_sql = "select * from user where user_id = ? and password = ?";
		User user = User.dao.findFirst(str_sql, user_id, password);
		if (user != null)
			return true;
		return false;
	}

	/**
	 * add
	 * 
	 * @param user_id
	 * @param name
	 * @param password
	 * @param payment
	 * @param ID_number
	 * @param handle_person
	 * @param account_bank
	 * @param bank_account
	 * @param sex
	 * @param type
	 * @param address
	 * @param birthday
	 * @param telephone
	 * @param referrer_number
	 * @param add_time
	 * @return
	 */
	public boolean add(int user_id, String name, String password,
			String payment, String ID_number, String handle_person,
			String account_bank, String bank_account, String sex, String type,
			String address, String birthday, String telephone,
			String referrer_number, String add_time) {
		if (new User().set("user_id", user_id).set("name", name)
				.set("password", password).set("payment", payment)
				.set("ID_number", ID_number)
				.set("handle_person", handle_person)
				.set("account_bank", account_bank)
				.set("bank_account", bank_account).set("sex", sex)
				.set("type", type).set("address", address)
				.set("birthday", birthday).set("telephone", telephone)
				.set("referrer_number", referrer_number)
				.set("add_time", add_time).save()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * delete by user_id
	 * 
	 * @param user_id
	 * @return
	 */
	public boolean delete(int user_id) {
		if (dao.deleteById(user_id)) {
			return true;
		}
		return false;
	}

	/**
	 * query
	 * 
	 * @param user_id
	 * @return
	 */
	public Map<String, String> query(int user_id) {
		User user = dao.findById(user_id);
		if (user != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_id", String.valueOf(user.get("user_id")));
			map.put("password", String.valueOf(user.get("password")));
			map.put("user_name", String.valueOf(user.get("user_name")));
			map.put("payment", String.valueOf(user.get("payment")));
			map.put("ID_number", String.valueOf(user.get("ID_number")));
			map.put("handle_person", String.valueOf(user.get("handle_person")));
			map.put("account_bank", String.valueOf(user.get("account_bank")));
			map.put("bank_account", String.valueOf(user.get("bank_account")));
			map.put("sex", String.valueOf(user.get("sex")));
			map.put("type", String.valueOf(user.get("type")));
			map.put("address", String.valueOf(user.get("address")));
			map.put("birthday", String.valueOf(user.get("birthday")));
			map.put("telephone", String.valueOf(user.get("telephone")));
			map.put("referrer_number",
					String.valueOf(user.get("referrer_number")));
			map.put("add_time", String.valueOf(user.get("add_time")));

			return map;
		} else {
			return null;
		}
	}

	/**
	 * update
	 * 
	 * @param user_id
	 * @param name
	 * @param password
	 * @param payment
	 * @param ID_number
	 * @param handle_person
	 * @param account_bank
	 * @param bank_account
	 * @param sex
	 * @param type
	 * @param address
	 * @param birthday
	 * @param telephone
	 * @param referrer_number
	 * @param add_time
	 * @return
	 */
	public boolean update(int user_id, String name, String password,
			String payment, String ID_number, String handle_person,
			String account_bank, String bank_account, String sex, String type,
			String address, String birthday, String telephone,
			String referrer_number, String add_time) {
		if (dao.findById(user_id).set("name", name).set("password", password)
				.set("payment", payment).set("ID_number", ID_number)
				.set("handle_person", handle_person)
				.set("account_bank", account_bank)
				.set("bank_account", bank_account).set("sex", sex)
				.set("type", type).set("address", address)
				.set("birthday", birthday).set("telephone", telephone)
				.set("referrer_number", referrer_number)
				.set("add_time", add_time).update()) {
			return true;
		} else {
			return false;
		}
	}
}
