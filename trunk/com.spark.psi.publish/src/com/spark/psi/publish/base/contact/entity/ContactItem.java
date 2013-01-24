package com.spark.psi.publish.base.contact.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 通讯录条目<br>
 * 查询方法：ListEntity<ContactItem> + GetContactListKey
 */
public class ContactItem {

	/**
	 * 条目ID
	 */
	private GUID id;

	/**
	 * 姓名
	 */
	protected String name;

	/**
	 * 客户或者供应商名称
	 */
	protected String partnerName;

	/**
	 * 部门
	 */
	protected String department;

	/**
	 * 职位
	 */
	protected String position;

	/**
	 * 固定电话
	 */
	protected String landLineNumber;

	/**
	 * 手机
	 */
	protected String mobileNumber;

	/**
	 * email地址
	 */
	protected String email;

	/**
	 * 地址
	 */
	protected String address;

	/**
	 * 
	 */
	protected String postCode;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the partnerName
	 */
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @return the landLineNumber
	 */
	public String getLandLineNumber() {
		return landLineNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNo() {
		return mobileNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @return
	 */
	public String getPostCode() {
		return postCode;
	}

}
