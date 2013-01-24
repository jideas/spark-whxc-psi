package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class CompanyOrmEntity{
	
	private GUID id;
	private String companyName;
	private String companyShortName;
	private String systemName;
	private String province;
	private String city;
	private String district;
	private String address;
	private String postcode ;
	private String landLineNumber;
	private String faxNumber;
	private byte[] logo;
	
	
	public byte[] getLogo(){
    	return logo;
    }
	public void setLogo(byte[] logo){
    	this.logo = logo;
    }
	public GUID getId(){
    	return id;
    }
	public void setId(GUID id){
    	this.id = id;
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
	public String getLandLineNumber(){
    	return landLineNumber;
    }
	public void setLandLineNumber(String landLineNumber){
    	this.landLineNumber = landLineNumber;
    }
	public String getFaxNumber(){
    	return faxNumber;
    }
	public void setFaxNumber(String faxNumber){
    	this.faxNumber = faxNumber;
    }

}
