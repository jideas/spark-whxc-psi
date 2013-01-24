/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.config.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-9    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.config.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-9    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ά����ҵ��Ϣ</p>
 *


 *
 
 * @version 2012-4-9
 */

public class UpdateCompanyInfoTask extends Task<UpdateCompanyInfoTask.Method>{
	
	public enum Method{
		Create,
		Update
	}

	/**
	 * ��ҵȫ��
	 */
	private String companyName;
	/**
	 * ��ҵ���
	 */
	private String companyShortName;
	/**
	 * ϵͳ����
	 */
	private String systemName;
	/**
	 * ʡ
	 */
	private String province;
	/**
	 * ��
	 */
	private String city;
	/**
	 * ��
	 */
	private String district;
	/**
	 * ��ϸ��ַ
	 */
	private String address;
	/**
	 * �ʱ�
	 */
	private String postcode ;
	/**
	 * �绰
	 */
	private String phone;
	/**
	 * ����
	 */
	private String fax;
	
	private byte[] logo;
	
	private GUID id;
	
	
	
	public GUID getId(){
    	return id;
    }
	public void setId(GUID id){
    	this.id = id;
    }
	public byte[] getLogo(){
    	return logo;
    }
	public void setLogo(byte[] logo){
    	this.logo = logo;
    }
	public String getCompanyName(){
    	return companyName;
    }
	public void setCompanyName(String companyName){
    	this.companyName = companyName;
    }
	public String getCompanyShortName(){
    	return companyShortName;
    }
	public void setCompanyShortName(String companyShortName){
    	this.companyShortName = companyShortName;
    }
	public String getSystemName(){
    	return systemName;
    }
	public void setSystemName(String systemName){
    	this.systemName = systemName;
    }
	public String getProvince(){
    	return province;
    }
	public void setProvince(String province){
    	this.province = province;
    }
	public String getCity(){
    	return city;
    }
	public void setCity(String city){
    	this.city = city;
    }
	public String getDistrict(){
    	return district;
    }
	public void setDistrict(String district){
    	this.district = district;
    }
	public String getAddress(){
    	return address;
    }
	public void setAddress(String address){
    	this.address = address;
    }
	public String getPostcode(){
    	return postcode;
    }
	public void setPostcode(String postcode){
    	this.postcode = postcode;
    }
	public String getPhone(){
    	return phone;
    }
	public void setPhone(String phone){
    	this.phone = phone;
    }
	public String getFax(){
    	return fax;
    }
	public void setFax(String fax){
    	this.fax = fax;
    }

	
}
