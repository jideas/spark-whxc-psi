package com.spark.oms.publish;

import java.util.List;

/**
 * 存储结果列表，及设置条数
 * @param <T>
 */

public class ListEntity <T>{
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	//数目
	private int count = 0;
	//总金额
	private double totalAmount = 0;
	
	//list 结果集
	private List<T> list;

}
