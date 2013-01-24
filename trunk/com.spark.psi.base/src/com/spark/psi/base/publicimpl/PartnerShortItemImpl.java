package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.partner.entity.CustomerShortItem;
import com.spark.psi.publish.base.partner.entity.SupplierShortItem;

public class PartnerShortItemImpl implements CustomerShortItem,SupplierShortItem{
	
	private GUID id;
	
	private String partnerNo;

	private String name;// 客户供应商全称

	private String shortName;// 客户供应商简称

	private String fax;// 传真

	private double creditAmount; //信用额度
	
	protected EnumEntity province;// 省份

	protected EnumEntity city;// 城市

	protected EnumEntity town;// 区（县）
	
	protected String address;	

	public String getAddress(){
    	return address;
    }

	public String getPartnerNo() {
		return partnerNo;
	}

	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public EnumEntity getTown() {
		return town;
	}

	public void setTown(EnumEntity town) {
		this.town = town;
	}

	public void setAddress(String address){
    	this.address = address;
    }

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

	public String getShortName(){
    	return shortName;
    }

	public void setShortName(String shortName){
    	this.shortName = shortName;
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

	public double getCreditAmount(){
    	return creditAmount;
    }

	public void setCreditAmount(double creditAmount){
    	this.creditAmount = creditAmount;
    }

	
}
