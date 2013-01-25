package com.spark.order.intf.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>查询返回对象</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public class PageEntity<T> {
	private List<T> list = new ArrayList<T>();
	private int count;
	/**
	 * 单据数量
	 */
	private int billsCount;
	/**
	 * 采购金额
	 */
	private double orderAmount;
	/**
	 * 退货金额
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
		//无参构造
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
