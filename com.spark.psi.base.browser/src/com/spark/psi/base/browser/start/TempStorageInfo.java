package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ʱ����ֿ���Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-2
 */

public class TempStorageInfo{
	/**ID*/
	private GUID id;

	/**�ֿ�����*/
	private String name;

	/**�̻�*/
	private String phone;

	/**�ջ���*/
	private String consignee;

	/**�ֻ�*/
	private String mobile;

	/**ʡ*/
	private String province;

	/**��*/
	private String city;

	/**��*/
	private String district;

	/**��ϸ��ַ*/
	private String address;

	/**�ʱ�*/
	private String postcode;

	/**��ԱID��*/
	private String keeperIds;

	/**���Ա����*/
	private String keeperNames;

	/**������ԱID��*/
	private String saleIds;

	/**������Ա����*/
	private String saleNames;

	/**�ֿ��Ƿ�����*/
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
