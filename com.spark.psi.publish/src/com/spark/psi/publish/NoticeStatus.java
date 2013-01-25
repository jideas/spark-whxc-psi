package com.spark.psi.publish;

/**
 * <p>公告状态</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public enum NoticeStatus{

	NotRelease("01", "未发布公告"),
	Released("02", "已发布公告"),
	Cancel("03", "被撤消公告"),
	Overdue("04", "过期公告");

	final String key;
	final String name;

	public String getKey(){
		return key;
	}

	public String getName(){
		return name;
	}

	private NoticeStatus(String key, String name){
		this.key = key;
		this.name = name;
	}
}
