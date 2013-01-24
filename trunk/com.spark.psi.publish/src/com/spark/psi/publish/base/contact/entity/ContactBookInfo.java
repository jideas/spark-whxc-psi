package com.spark.psi.publish.base.contact.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.EnumEntity;

/**
 * 联系人和通讯录信息<br>
 * 查询方法：直接通过id查询ContactBookInfo对象
 */
public interface ContactBookInfo {
	
	public GUID getId();
	
	/**
	 * 姓名
	 * @return the name
	 */
	public String getName();

	/**
	 * 尊称
	 * @return the nickName
	 */
	public String getNickName();

	/**
	 * 客户编号
	 * @return the partnerId
	 */
	public GUID getPartnerId();

	/**
	 * 客户名称
	 * @return the partnerName
	 */
	public String getPartnerName();

	/**
	 * 单位
	 */
	public String getCompany();
	
	/**
	 * 部门名称
	 * @return the department
	 */
	public String getDepartment();

	/**
	 * 职务
	 * @return the position
	 */
	public String getPosition();

	/**
	 * 固定电话
	 * @return the landLineNumber
	 */
	public String getLandLineNumber();

	/**
	 * 手机
	 * @return the mobileNumber
	 */
	public String getMobileNo();
	

	/**
	 * email
	 * @return the email
	 */
	public String getEmail();

	/**
	 * 性别
	 * @return the sex
	 */
	public EnumEntity getSex();

	/**
	 * 生日
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
	 * 旺旺
	 * @return the wwNumber
	 */
	public String getWwNumber();

	/**
	 * 爱好
	 * @return the hobbies
	 */
	public String getHobbies();

	/**
	 * 备注
	 * @return the memo
	 */
	public String getRemark();

	/**
	 * 类型
	 * @return the type
	 */
	public ContactType getType();

	/**
	 * 省
	 * @return the province
	 */
	public EnumEntity getProvince();

	/**
	 * 市
	 * @return the city
	 */
	public EnumEntity getCity();

	/**
	 * 县
	 * @return the district
	 */
	public EnumEntity getDistrict() ;

	/**
	 * 详细地址
	 * @return the address
	 */
	public String getAddress();

	/**
	 * 邮编
	 * @return the postCode
	 */
	public String getPostCode();
	
	
	/***
	 * 是否主联系人
	 * @return
	 */
	public boolean isMain();

	/**
	 * 最后使用日期
	 * 
	 * @return long
	 */
	public long getLastDate();
}
