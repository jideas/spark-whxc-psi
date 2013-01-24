package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.config.entity.CompanyInfo;

public class CompanyInfoImpl implements CompanyInfo{

	private GUID id;
	private String companyName;
	private String companyShortName;
	private String systemName;
	private EnumEntity province;
	private EnumEntity city;
	private EnumEntity district;
	private String address;
	private String postcode ;
	private String landLineNumber;
	private String faxNumber;
	private byte[] logo;
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
	public EnumEntity getProvince(){
    	return province;
    }
	public void setProvince(EnumEntity province){
    	this.province = province;
    }
	public EnumEntity getCity(){
    	return city;
    }
	public void setCity(EnumEntity city){
    	this.city = city;
    }
	public EnumEntity getDistrict(){
    	return district;
    }
	public void setDistrict(EnumEntity district){
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
	public byte[] getLogo(){
    	return logo;
    }
	public void setLogo(byte[] logo){
    	this.logo = logo;
    }
	public String getFaxNumber(){
    	return faxNumber;
    }
	public void setFaxNumber(String faxNumber){
    	this.faxNumber = faxNumber;
    }
	
	
	
}
