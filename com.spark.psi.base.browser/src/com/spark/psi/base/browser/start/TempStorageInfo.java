package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>临时缓存仓库信息</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-2
 */

public class TempStorageInfo{
	/**ID*/
	private GUID id;

	/**仓库名称*/
	private String name;

	/**固话*/
	private String phone;

	/**收货人*/
	private String consignee;

	/**手机*/
	private String mobile;

	/**省*/
	private String province;

	/**市*/
	private String city;

	/**县*/
	private String district;

	/**详细地址*/
	private String address;

	/**邮编*/
	private String postcode;

	/**库员ID串*/
	private String keeperIds;

	/**库管员名称*/
	private String keeperNames;

	/**零售人员ID串*/
	private String saleIds;

	/**零售人员名称*/
	private String saleNames;

	/**仓库是否启用*/
	private boolean enabled;

	public GUID getId(){
		return id;
	}

	public void setId(GUID id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getConsignee(){
		return consignee;
	}

	public void setConsignee(String consignee){
		this.consignee = consignee;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
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

	public String getKeeperIds(){
		return keeperIds;
	}

	public void setKeeperIds(String keeperIds){
		this.keeperIds = keeperIds;
	}

	public String getKeeperNames(){
		return keeperNames;
	}

	public void setKeeperNames(String keeperNames){
		this.keeperNames = keeperNames;
	}

	public String getSaleIds(){
		return saleIds;
	}

	public void setSaleIds(String saleIds){
		this.saleIds = saleIds;
	}

	public String getSaleNames(){
		return saleNames;
	}

	public void setSaleNames(String saleNames){
		this.saleNames = saleNames;
	}

	public boolean isEnabled(){
		return enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

}
