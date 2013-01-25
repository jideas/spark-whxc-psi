package com.spark.order;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>列表显示父类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-28
 */
public abstract class ListEntityFather<T> {
	private List<T> list = new ArrayList<T>();
	private int currentCount;
	/**
	 * 单据数量
	 */
	private int totalCount;
	/**
	 * 采购金额
	 */
	private double orderAmount;
	/**
	 * 退货金额
	 */
	private double returnAmount;
//	public ListEntityFather(){
//		//无参构造
//	}
	public ListEntityFather(List<T> list,int totalCount){
		this.list = list;
		this.currentCount = list.size();
		this.totalCount = totalCount;
	}
	protected abstract void setTotalCount(int totalCount);
	protected abstract void setOrderAmount(double orderAmount);
	protected abstract void setReturnAmount(double returnAmount);
	protected abstract void setList(List<T> list);
	public List<T> getList() {
		return list;
	}
	protected void setListParent(List<T> list) {
		this.list = list;
		this.currentCount = list.size();
	}
	public int getTotalCount() {
		return totalCount;
	}
	protected void setTotalCountParent(int totalCount) {
		this.totalCount = totalCount;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	protected void setOrderAmountParent(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getReturnAmount() {
		return returnAmount;
	}
	protected void setReturnParent(double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public int getCurrentCount() {
		return currentCount;
	}
}
