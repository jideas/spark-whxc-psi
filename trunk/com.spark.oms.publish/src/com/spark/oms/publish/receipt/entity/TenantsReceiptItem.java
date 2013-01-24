package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户交款合计数据：收款确认
 * @author mengyongfeng
 *
 */
public class TenantsReceiptItem {
	
	//应收合计
	private double totalPayAmount;
	
	//租户应交款
	private List<TenantsReceiptInfo>  tenantsReceiptList = new ArrayList<TenantsReceiptInfo>();
	
	public double getTotalPayAmount() {
		return totalPayAmount;
	}
	public void setTotalPayAmount(double totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}
	public List<TenantsReceiptInfo> getTenantsReceiptList() {
		return tenantsReceiptList;
	}
	public void setTenantsReceiptList(List<TenantsReceiptInfo> tenantsReceiptList) {
		this.tenantsReceiptList = tenantsReceiptList;
	}
	public void add(TenantsReceiptInfo tenantsReceiptInfo) {
		tenantsReceiptList.add(tenantsReceiptInfo);
	}
	
	
	

}
