package com.spark.psi.base.internal.entity;

import com.spark.psi.base.GoodsTraded;

public class MaterialsTradedImpl implements GoodsTraded{
	
	private int salesCount;
	
	private double recentSalesAmount;
	
	private int purcahseCount;
	
	private double recentPurchaseAmount;

	public int getSalesCount(){
    	return salesCount;
    }

	public void setSalesCount(int salesCount){
    	this.salesCount = salesCount;
    }

	public double getRecentSalesAmount(){
    	return recentSalesAmount;
    }

	public void setRecentSalesAmount(double recentSalesAmount){
    	this.recentSalesAmount = recentSalesAmount;
    }

	public int getPurcahseCount(){
    	return purcahseCount;
    }

	public void setPurcahseCount(int purcahseCount){
    	this.purcahseCount = purcahseCount;
    }

	public double getRecentPurchaseAmount(){
    	return recentPurchaseAmount;
    }

	public void setRecentPurchaseAmount(double recentPurchaseAmount){
    	this.recentPurchaseAmount = recentPurchaseAmount;
    }

	
}
