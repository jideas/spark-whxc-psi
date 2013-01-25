/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.config.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-9    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.config.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-9    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>维护企业信息</p>
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
	 * 企业全称
	 */
	private String companyName;
	/**
	 * 企业简称
	 */
	private String companyShortName;
	/**
	 * 系统名称
	 */
	private String systemName;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 县
	 */
	private String district;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String postcode ;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 传真
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
