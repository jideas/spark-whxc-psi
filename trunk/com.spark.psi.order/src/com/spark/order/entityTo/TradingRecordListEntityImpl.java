package com.spark.order.entityTo;

import java.util.List;

import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.TradingRecordListEntity;

public class TradingRecordListEntityImpl extends TradingRecordListEntity {
//	protected double totalAmount; //�����ܽ��
//	
//	protected double orderAmount;  //�������
//	
//	protected double returnAmount;  //�˻����
//	
//	protected int returnCount;  //�˻�����
//	protected int orderCount;	//��������

	public TradingRecordListEntityImpl(List<OrderItem> dataList, int totalCount) {
		super(dataList, totalCount);
	}

	/**
	 * @return the totalAmount
	 */
	@Override
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the orderAmount
	 */
	@Override
	public double getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the returnAmount
	 */
	@Override
	public double getReturnAmount() {
		return returnAmount;
	}

	/**
	 * @param returnAmount the returnAmount to set
	 */
	public void setReturnAmount(double returnAmount) {
		this.returnAmount = returnAmount;
	}

	/**
	 * @return the returnCount
	 */
	@Override
	public int getReturnCount() {
		return returnCount;
	}

	/**
	 * @param returnCount the returnCount to set
	 */
	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}
