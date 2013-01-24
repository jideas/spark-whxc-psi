package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class ApprovalRecordOrmEntity {
	
	
	private GUID id;
	private GUID tenantId;
	private GUID userId;
	private long examDate;
	private String billsNumber;
	private String busType;
	private String busTypeName;
	private double amount;
	private String createPerson;
	private long createDate;
	private String status;
	
	
	
	public GUID getTenantId(){
    	return tenantId;
    }
	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }
	public GUID getId(){
    	return id;
    }
	public void setId(GUID id){
    	this.id = id;
    }
	public GUID getUserId(){
    	return userId;
    }
	public void setUserId(GUID userId){
    	this.userId = userId;
    }
	public long getExamDate(){
    	return examDate;
    }
	public void setExamDate(long examDate){
    	this.examDate = examDate;
    }


	
	public String getBillsNumber(){
    	return billsNumber;
    }
	public void setBillsNumber(String billsNumber){
    	this.billsNumber = billsNumber;
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
	public String getCreatePerson(){
    	return createPerson;
    }
	public void setCreatePerson(String createPerson){
    	this.createPerson = createPerson;
    }
	public long getCreateDate(){
    	return createDate;
    }
	public void setCreateDate(long createDate){
    	this.createDate = createDate;
    }
	public String getStatus(){
    	return status;
    }
	public void setStatus(String status){
    	this.status = status;
    }

	
}
