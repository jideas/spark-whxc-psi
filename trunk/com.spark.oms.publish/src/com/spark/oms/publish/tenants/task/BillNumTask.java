package com.spark.oms.publish.tenants.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class BillNumTask extends SimpleTask {

	//ฑ๊สถ
	private GUID RECID;
	
	private String billCode;
	private String isAutoGen;
	private String prefix;
	private int scale;
	private String firstSerial;
	private String yearFlag;
	private String monthFlag;
	private String dayFlag;
	private GUID tenantsRECID;
	private GUID serviceRECID;
	private String productSerial;
	
	

	public String getProductSerial() {
		return productSerial;
	}
	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}
	public GUID getRECID() {
		return RECID;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getIsAutoGen() {
		return isAutoGen;
	}
	public void setIsAutoGen(String isAutoGen) {
		this.isAutoGen = isAutoGen;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getFirstSerial() {
		return firstSerial;
	}
	public void setFirstSerial(String firstSerial) {
		this.firstSerial = firstSerial;
	}
	public String getYearFlag() {
		return yearFlag;
	}
	public void setYearFlag(String yearFlag) {
		this.yearFlag = yearFlag;
	}
	public String getMonthFlag() {
		return monthFlag;
	}
	public void setMonthFlag(String monthFlag) {
		this.monthFlag = monthFlag;
	}
	public String getDayFlag() {
		return dayFlag;
	}
	public void setDayFlag(String dayFlag) {
		this.dayFlag = dayFlag;
	}
	public GUID getTenantsRECID() {
		return tenantsRECID;
	}
	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}
	public GUID getServiceRECID() {
		return serviceRECID;
	}
	public void setServiceRECID(GUID serviceRECID) {
		this.serviceRECID = serviceRECID;
	}
}