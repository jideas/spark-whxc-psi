package com.spark.order.intf.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>��ѯ���ض���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public class PageEntity<T> {
	private List<T> list = new ArrayList<T>();
	private int count;
	/**
	 * ��������
	 */
	private int billsCount;
	/**
	 * �ɹ����
	 */
	private double orderAmount;
	/**
	 * �˻����
	 */
	private double cancelAmount;
	
	
	public int getBillsCount() {
		return billsCount;
	}
	public void setBillsCount(int billsCount) {
		this.billsCount = billsCount;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getCancelAmount() {
		return cancelAmount;
	}
	public void setCancelAmount(double cancelAmount) {
		this.cancelAmount = cancelAmount;
	}
	public PageEntity(){
		//�޲ι���
	}
	public PageEntity(List<T> list,int cout){
		this.list = list;
		this.count = cout;
	}

	public List<T> getList(){
    	return list;
    }

	public void setList(List<T> list){
    	this.list = list;
    }

	public int getCount(){
    	return count;
    }

	public void setCount(int count){
    	this.count = count;
    }
}
