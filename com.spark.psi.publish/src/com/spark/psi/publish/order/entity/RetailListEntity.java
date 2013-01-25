package com.spark.psi.publish.order.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class RetailListEntity extends ListEntity<RetailItem>{

	private double retailAmount; //零售金额
	
	private double returnAmount; //零售退货金额
	
	public RetailListEntity(List<RetailItem> dataList, int totalCount){
	    super(dataList, totalCount);
    }

	public double getRetailAmount(){
    	return retailAmount;
    }

	public double getReturnAmount(){
    	return returnAmount;
    }

	
	

}
