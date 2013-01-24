package com.spark.psi.base.internal.entity;

import com.spark.psi.base.PartnerTraded;

public class PartnerTradedImpl implements PartnerTraded{
	
	private int orderCount;
	
	private double orderAmount;
	
	private int returnCount;
	
	private double returnAmount;
	
	private double balanceAmount;

	public int getOrderCount(){
    	return orderCount;
    }

	public void setOrderCount(int orderCount){
    	this.orderCount = orderCount;
    }

	public double getOrderAmount(){
    	return orderAmount;
    }

	public void setOrderAmount(double orderAmount){
    	this.orderAmount = orderAmount;
    }

	public int getReturnCount(){
    	return returnCount;
    }

	public void setReturnCount(int returnCount){
    	this.returnCount = returnCount;
    }

	public double getReturnAmount(){
    	return returnAmount;
    }

	public void setReturnAmount(double returnAmount){
    	this.returnAmount = returnAmount;
    }

	public double getBalanceAmount(){
    	return balanceAmount;
    }

	public void setBalanceAmount(double balanceAmount){
    	this.balanceAmount = balanceAmount;
    }

	
}
