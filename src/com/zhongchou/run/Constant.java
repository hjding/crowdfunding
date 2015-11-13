package com.zhongchou.run;

public class Constant {

	public final static String STATUS_SUCCESS = "SUCCESS";
	public final static String STATUS_FAILED = "FAILED";

	public final static String USER_HAS_EXISTED = "用户已存在";
	public final static String USER_INFO_EDIT_ERROR = "用户信息填写出错";
	public final static String USER_SAVE_ERROR = "用户信息创建出错";
	public final static String USER_INFO_GET_ERROR = "用户信息获取出错";
	public final static String USER_UPDATE_ERROR = "用户信息更新出错";
	public final static String USER_ID_OR_PASSWORD_ERROR = "用户账号或密码错误";
	public final static String USER_ID_OR_PASSWORD_EDIT_ERROR = "用户账号或密码不能为空";
	public final static String USER_NOT_FOUND = "用户未找到";
	public final static String USER_PERMISSION_NOT_ENOUGH = "用户权限不够";

	public final static String COOKIE_DOMAIN_NAME = "zhongchou.com";
	// public final static String COOKIE_DOMAIN_NAME = "localhost";
	public final static String COOKIE_USER = "zhongchou.com.uid";
	public final static String COOKIE_WEB_KEY = "zhongchou";
	public final static long COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 7;

	public final static int COOKIE_NOT_FOUND_USER = -2;
	public final static int COOKIE_AUTHENTICATION_ERROR = -3;
	public final static int COOKIE_OVER_DUE = -4;
	public final static int COOKIE_BASE64_DECODE_ERROR = -5;
	public final static int COOKIE_NOT_VALID = -6;
	public final static int COOKIE_NOT_FOUND = -7;

	public final static String PROJECT_SAVE_ERROR = "项目信息创建出错";
	public final static String PROJECT_DELETE_ERROR = "项目删除出错";
	public final static String PROJECT_QUERY_ERROR = "项目查找出错";
	public final static String PROJECT_UPDATE_ERROR = "项目信息更新出错";
	public final static String PROJECT_INFO_EDIT_ERROR = "项目信息填写出错";
	public final static String PROJECT_ID_EDIT_ERROR = "项目id填写出错";
	public final static String PROJECT_LIST_ERROR = "项目列表查询出错";
	public final static String PROJECT_LIST_PARA_ERROR = "项目列表参数pageNumber或pageSize出错";

	public final static String PROJECT_ORDER_PARA_ERROR = "项目预订参数pageNumber或pageSize出错";
	public final static String PROJECT_ORDER_LIST_ERROR = "项目预订列表查询出错";

	public final static String USER_ID_IS_NULL = "user_id为空";
	public final static String PROJECT_ID_IS_NULL = "project_id为空";
	public final static String ORDER_DATE_IS_NULL = "order_date为null或空字符";
	public final static String ORDER_CONTENT_IS_NULL = "order_content为null或空字符";
	public final static String ORDER_TYPE_IS_NULL = "order_type为null或空字符";

	public final static String ORDER_INSERT_FAILED = "项目预订增加失败";
	public final static String ORDER_DELETE_FAILED = "项目预订删除失败";
	public final static String ORDER_QUERY_FAILED = "项目预订查询失败";
	public final static String ORDER_UPDATE_FAILED = "项目预订修改失败";
	public final static String ORDER_LIST_FAILED = "项目预订列表查询失败";
}
