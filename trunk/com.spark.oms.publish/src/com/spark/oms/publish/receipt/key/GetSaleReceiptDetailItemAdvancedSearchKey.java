package com.spark.oms.publish.receipt.key;

import com.jiuqi.dna.core.type.GUID;

public class GetSaleReceiptDetailItemAdvancedSearchKey {

	private GUID saleId;
	private long start;
	private long end;
	private String tenant;
	private String product;
	private String billingTypes;
	private double amountStart;
	private double amountEnd;

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getBillingTypes() {
		return billingTypes;
	}

	public void setBillingTypes(String billingTypes) {
		this.billingTypes = billingTypes;
	}

	public double getAmountStart() {
		return amountStart;
	}

	public void setAmountStart(double amountStart) {
		this.amountStart = amountStart;
	}

	public double getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(double amountEnd) {
		this.amountEnd = amountEnd;
	}

	public GUID getSaleId() {
		return saleId;
	}

	public void setSaleId(GUID saleId) {
		this.saleId = saleId;
	}
}
