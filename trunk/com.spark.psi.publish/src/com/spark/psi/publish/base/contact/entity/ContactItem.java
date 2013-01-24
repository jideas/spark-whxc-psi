package com.spark.psi.publish.base.contact.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ͨѶ¼��Ŀ<br>
 * ��ѯ������ListEntity<ContactItem> + GetContactListKey
 */
public class ContactItem {

	/**
	 * ��ĿID
	 */
	private GUID id;

	/**
	 * ����
	 */
	protected String name;

	/**
	 * �ͻ����߹�Ӧ������
	 */
	protected String partnerName;

	/**
	 * ����
	 */
	protected String department;

	/**
	 * ְλ
	 */
	protected String position;

	/**
	 * �̶��绰
	 */
	protected String landLineNumber;

	/**
	 * �ֻ�
	 */
	protected String mobileNumber;

	/**
	 * email��ַ
	 */
	protected String email;

	/**
	 * ��ַ
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
