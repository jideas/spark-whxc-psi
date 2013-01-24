package com.spark.oms.publish.order.key;

public class GetOrderItemAdvancedSearchKey {
	
	private String dateRange;
	private String orderServiceRunstatus;
	private String orderServiceFeestatus;
	private String serachkey;	

	private long signedStartDate;
	private long signedEndDate;
	private String orderNum;
	private String tenant;
	private double orderAmountStart;
	private double orderAmountEnd;
	private String signedMan;
	private String orderServiceRunstatuss;
	private String orderServiceFeestatuss;
	
	private int tag = 0;//0∆’Õ®,1∏ﬂº∂

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getOrderServiceRunStatus() {
		return orderServiceRunstatus;
	}

	public void setOrderServiceRunStatus(String orderServiceRunstatus) {
		this.orderServiceRunstatus = orderServiceRunstatus;
	}

	public String getOrderServiceFeeStatus() {
		return orderServiceFeestatus;
	}

	public void setOrderServiceFeeStatus(String orderServiceFeestatus) {
		this.orderServiceFeestatus = orderServiceFeestatus;
	}

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}

	public long getSignedStartDate() {
		return signedStartDate;
	}

	public void setSignedStartDate(long signedStartDate) {
		this.signedStartDate = signedStartDate;
	}

	public long getSignedEndDate() {
		return signedEndDate;
	}

	public void setSignedEndDate(long signedEndDate) {
		this.signedEndDate = signedEndDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public double getOrderAmountStart() {
		return orderAmountStart;
	}

	public void setOrderAmountStart(double orderAmountStart) {
		this.orderAmountStart = orderAmountStart;
	}

	public double getOrderAmountEnd() {
		return orderAmountEnd;
	}

	public void setOrderAmountEnd(double orderAmountEnd) {
		this.orderAmountEnd = orderAmountEnd;
	}

	public String getSignedMan() {
		return signedMan;
	}

	public void setSignedMan(String signedMan) {
		this.signedMan = signedMan;
	}

	public String getOrderServiceRunStatuss() {
		return orderServiceRunstatuss;
	}

	public void setOrderServiceRunStatuss(String orderServiceRunstatuss) {
		this.orderServiceRunstatuss = orderServiceRunstatuss;
	}

	public String getOrderServiceFeeStatuss() {
		return orderServiceFeestatuss;
	}

	public void setOrderServiceFeeStatuss(String orderServiceFeestatuss) {
		this.orderServiceFeestatuss = orderServiceFeestatuss;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
}
