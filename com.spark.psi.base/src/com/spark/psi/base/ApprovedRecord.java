package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

public class ApprovedRecord {
	
	private long approvedDate;
	private String billsNo;
	private String busType;
	private String busTypeName;
	private double amount;
	private String status;
	private String model;
	
	private GUID recid;
	
	public GUID getRecid(){
    	return recid;
    }
	public void setRecid(GUID recid){
    	this.recid = recid;
    }

	public long getApprovedDate(){
    	return approvedDate;
    }
	public void setApprovedDate(long approvedDate){
    	this.approvedDate = approvedDate;
    }
	public String getBillsNo(){
    	return billsNo;
    }
	public void setBillsNo(String billsNo){
    	this.billsNo = billsNo;
    }
	public String getBusType(){
    	return busType;
    }
	public void setBusType(String busType){
    	this.busType = busType;
    }
	public String getBusTypeName(){
    	return busTypeName;
    }
	public void setBusTypeName(String busTypeName){
    	this.busTypeName = busTypeName;
    }
	public double getAmount(){
    	return amount;
    }
	public void setAmount(double amount){
    	this.amount = amount;
    }
	public String getStatus(){
    	return status;
    }
	public void setStatus(String status){
    	this.status = status;
    }
	public String getModel(){
    	return model;
    }
	public void setModel(String model){
    	this.model = model;
    }
	
	
	

	
}
