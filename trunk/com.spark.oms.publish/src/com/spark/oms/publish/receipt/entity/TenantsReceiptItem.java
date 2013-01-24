package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * �⻧����ϼ����ݣ��տ�ȷ��
 * @author mengyongfeng
 *
 */
public class TenantsReceiptItem {
	
	//Ӧ�պϼ�
	private double totalPayAmount;
	
	//�⻧Ӧ����
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
