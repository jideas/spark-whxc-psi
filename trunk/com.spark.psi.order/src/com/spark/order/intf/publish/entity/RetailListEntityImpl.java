package com.spark.order.intf.publish.entity;

import java.util.List;

import com.spark.psi.publish.order.entity.RetailItem;
import com.spark.psi.publish.order.entity.RetailListEntity;

public class RetailListEntityImpl extends RetailListEntity{

	private double retailAmount; //零售金额
	
	private double returnAmount; //零售退货金额
	
	public RetailListEntityImpl(List<RetailItem> dataList, int totalCount){
	    super(dataList, totalCount);
    }

	@Override
	public double getRetailAmount(){
    	return retailAmount;
    }

	@Override
	public double getReturnAmount(){
    	return returnAmount;
    }

	/**
	 * @param retailAmount the retailAmount to set
	 */
	public void setRetailAmount(double retailAmount) {
		this.retailAmount = retailAmount;
	}

	/**
	 * @param returnAmount the returnAmount to set
	 */
	public void setReturnAmount(double returnAmount) {
		this.returnAmount = returnAmount;
	}

	
	

}
