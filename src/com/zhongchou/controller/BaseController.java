package com.zhongchou.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;

public abstract class BaseController extends Controller {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BaseController.class);

	/**
	 * 打印到控制台
	 */
	public void print(Object object) {
		System.out.println(object.toString());
	}

	/**
	 * 请求/WEB-INF/下的视图文件
	 */
	public void toUrl() {
		String toUrl = getPara("toUrl");
		render(toUrl);
	}

	/**
	 * 获取当前请求国际化参数
	 * 
	 * @return
	 */
	protected String getI18nPram() {
		return getAttr("localePram");
	}

	/**
	 * 获取当前国际化资源
	 * 
	 * @return
	 */
	protected Map<String, String> getI18nMap() {
		return getAttr("i18nMap");
	}

	/**
	 * 获取当前国际化资源值
	 * 
	 * @return
	 */
	protected String getI18nVal(String key) {
		Map<String, String> i18nMap = getI18nMap();
		return i18nMap.get(key);
	}

	/**
	 * 获取项目请求根路径
	 * 
	 * @return
	 */
	protected String getCxt() {
		return getAttr("cxt");
	}

	/**
	 * 获取当前用户id
	 * 
	 * @return
	 */
	protected String getCUserIds() {
		return getAttr("cUserIds");
	}

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	protected String getCUser() {
		return getAttr("cUser");
	}

	/**
	 * 获取ParamMap
	 * 
	 * @return
	 */
	protected Map<String, String> getParamMap() {
		return getAttr("paramMap");
	}

	/**
	 * 添加值到ParamMap
	 * 
	 * @return
	 */
	protected void addToParamMap(String key, String value) {
		Map<String, String> map = getAttr("paramMap");
		map.put(key, value);
	}

	/**
	 * 获取checkbox值，数组
	 * 
	 * @param name
	 * @return
	 */
	protected String[] getParas(String name) {
		return getRequest().getParameterValues(name);
	}

	/**
	 * 获取查询参数 说明：和分页分拣一样，但是应用场景不一样，主要是给查询导出的之类的功能使用
	 * 
	 * @return
	 */
	protected Map<String, String> getQueryParam() {
		Map<String, String> queryParam = new HashMap<String, String>();
		Enumeration<String> paramNames = getParaNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = getPara(name);
			if (name.startsWith("_query") && !value.isEmpty()) {// 查询参数分拣
				String key = name.substring(7);
				if (null != value && !value.trim().equals("")) {
					queryParam.put(key, value.trim());
				}
			}
		}

		return queryParam;
	}

	/**
	 * 排序条件 说明：和分页分拣一样，但是应用场景不一样，主要是给查询导出的之类的功能使用
	 * 
	 * @return
	 */
	protected String getOrderColunm() {
		String orderColunm = getPara("orderColunm");
		return orderColunm;
	}

	/**
	 * 排序方式 说明：和分页分拣一样，但是应用场景不一样，主要是给查询导出的之类的功能使用
	 * 
	 * @return
	 */
	protected String getOrderMode() {
		String orderMode = getPara("orderMode");
		return orderMode;
	}

	/**
	 * java EE6.0设置安全cookie的方法
	 * 
	 * @param name
	 * @param value
	 * @param maxAgeInSeconds
	 * @return
	 */
	public Controller setCookieHttpOnly(String name, String value,
			int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		// cookie.setDomain(Constant.COOKIE_DOMAIN_NAME);
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		setCookie(cookie);
		return this;
	}
}
