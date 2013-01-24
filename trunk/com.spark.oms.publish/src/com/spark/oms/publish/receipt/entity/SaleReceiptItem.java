package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售缴款
 */

public class SaleReceiptItem {
	//未付款合计
	private double unpayment;
	
	//销售人员缴款列表
	private List<SaleReceiptInfo> saleInfoList = new ArrayList<SaleReceiptInfo>();

	public double getUnpayment() {
		return unpayment;
	}

	public void setUnpayment(double unpayment) {
		this.unpayment = unpayment;
	}

	public List<SaleReceiptInfo> getSaleInfoList() {
		return saleInfoList;
	}

	public void add(SaleReceiptInfo saleReceiptInfo){
		saleInfoList.add(saleReceiptInfo);
	}

}
