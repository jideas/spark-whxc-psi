package com.spark.psi.publish.base.contact.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.EnumEntity;

/**
 * ��ϵ�˺�ͨѶ¼��Ϣ<br>
 * ��ѯ������ֱ��ͨ��id��ѯContactBookInfo����
 */
public interface ContactBookInfo {
	
	public GUID getId();
	
	/**
	 * ����
	 * @return the name
	 */
	public String getName();

	/**
	 * ���
	 * @return the nickName
	 */
	public String getNickName();

	/**
	 * �ͻ����
	 * @return the partnerId
	 */
	public GUID getPartnerId();

	/**
	 * �ͻ�����
	 * @return the partnerName
	 */
	public String getPartnerName();

	/**
	 * ��λ
	 */
	public String getCompany();
	
	/**
	 * ��������
	 * @return the department
	 */
	public String getDepartment();

	/**
	 * ְ��
	 * @return the position
	 */
	public String getPosition();

	/**
	 * �̶��绰
	 * @return the landLineNumber
	 */
	public String getLandLineNumber();

	/**
	 * �ֻ�
	 * @return the mobileNumber
	 */
	public String getMobileNo();
	

	/**
	 * email
	 * @return the email
	 */
	public String getEmail();

	/**
	 * �Ա�
	 * @return the sex
	 */
	public EnumEntity getSex();

	/**
	 * ����
	 * @return the birth
	 */
	public long getBirth();

	/**
	 * MSN
	 * @return the msnNumber
	 */
	public String getMsnNumber();
	/**
	 * QQ
	 * @return the qqNumber
	 */
	public String getQqNumber();

	/**
	 * ����
	 * @return the wwNumber
	 */
	public String getWwNumber();

	/**
	 * ����
	 * @return the hobbies
	 */
	public String getHobbies();

	/**
	 * ��ע
	 * @return the memo
	 */
	public String getRemark();

	/**
	 * ����
	 * @return the type
	 */
	public ContactType getType();

	/**
	 * ʡ
	 * @return the province
	 */
	public EnumEntity getProvince();

	/**
	 * ��
	 * @return the city
	 */
	public EnumEntity getCity();

	/**
	 * ��
	 * @return the district
	 */
	public EnumEntity getDistrict() ;

	/**
	 * ��ϸ��ַ
	 * @return the address
	 */
	public String getAddress();

	/**
	 * �ʱ�
	 * @return the postCode
	 */
	public String getPostCode();
	
	
	/***
	 * �Ƿ�����ϵ��
	 * @return
	 */
	public boolean isMain();

	/**
	 * ���ʹ������
	 * 
	 * @return long
	 */
	public long getLastDate();
}
