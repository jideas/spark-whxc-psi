package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ���۽ɿ�
 */

public class SaleReceiptItem {
	//δ����ϼ�
	private double unpayment;
	
	//������Ա�ɿ��б�
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
