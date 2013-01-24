package com.spark.psi.base.publicimpl;

import com.spark.psi.publish.base.materials.entity.MaterialsTraderLogItem;

public class MaterialsTraderLogItemImpl implements MaterialsTraderLogItem{
	
	private String partnerName;
	
	private String property;
	private String materialsSpec;
	
	private String unit;
	
	private String status;
	
	private int count;
	
	private String totalTraderCount;
	
	private double totalTraderAmount;
	
	private String recentCount;
	
	private long recentDate;
	
	private double recentPrice;
	
	private double[] priceList;

	public String getPartnerName(){
    	return partnerName;
    }

	public void setPartnerName(String partnerName){
    	this.partnerName = partnerName;
    }

	public String getProperty(){
    	return property;
    }

	public void setProperty(String property){
    	this.property = property;
    }

	public String getUnit(){
    	return unit;
    }

	public void setUnit(String unit){
    	this.unit = unit;
    }

	public String getStatus(){
    	return status;
    }

	public void setStatus(String status){
    	this.status = status;
    }

	public int getCount(){
    	return count;
    }

	public void setCount(int count){
    	this.count = count;
    }

	public String getTotalTraderCount(){
    	return totalTraderCount;
    }

	public void setTotalTraderCount(String totalTraderCount){
    	this.totalTraderCount = totalTraderCount;
    }

	public double getTotalTraderAmount(){
    	return totalTraderAmount;
    }

	public void setTotalTraderAmount(double totalTraderAmount){
    	this.totalTraderAmount = totalTraderAmount;
    }

	public String getRecentCount(){
    	return recentCount;
    }

	public void setRecentCount(String recentCount){
    	this.recentCount = recentCount;
    }

	public long getRecentDate(){
    	return recentDate;
    }

	public void setRecentDate(long recentDate){
    	this.recentDate = recentDate;
    }

	public double getRecentPrice(){
    	return recentPrice;
    }

	public void setRecentPrice(double recentPrice){
    	this.recentPrice = recentPrice;
    }

	public double[] getPriceList(){
    	return priceList;
    }

	public void setPriceList(double[] priceList){
    	this.priceList = priceList;
    }

	public String getMaterialsSpec() {
		return materialsSpec;
	}

	public void setMaterialsSpec(String materialsSpec) {
		this.materialsSpec = materialsSpec;
	}
	
	
	
}
