package com.spark.onlineorder.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;

public class OnlineOrderListEntityImpl extends OnlineOrderListEntity {
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public OnlineOrderListEntityImpl(List<OnlineOrderItem> dataList,int totalCount)
	{
		super(dataList, totalCount);
	}

}
