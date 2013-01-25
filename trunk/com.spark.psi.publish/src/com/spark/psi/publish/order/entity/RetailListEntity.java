package com.spark.psi.publish.order.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;

public class RetailListEntity extends ListEntity<RetailItem>{

	private double retailAmount; //���۽��
	
	private double returnAmount; //�����˻����
	
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
