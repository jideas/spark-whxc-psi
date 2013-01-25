package com.spark.psi.publish.base.contact.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>联系人列表项</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-3
 */

public interface ContactPersonItem{

	/**类型[1、联系人；2、同事]*/
	public static final String CONTACT_PERSON = "1"; //联系人
	
	public static final String COLLEAGUE = "2"; //同事
	
	/**
	 * ID
	 */
	public GUID getId();

	/**
	 * 联系人名称
	 */
	public String getName();

	/**
	 * (单位)部门
	 */
	public String getDepartment();
	
	/**
	 * 职位
	 */
	public String getJob();

	/**
	 * 固定电话
	 */
	public String getPhone();

	/**
	 * 手机
	 */
	public String getMobile();

	/**
	 * 邮箱
	 */
	public String getEmail();
	
	/**
	 * 类型
	 */
	public String getType();
}
