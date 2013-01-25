package com.spark.psi.publish;

/**
 * 
 * <p>订单类型</p>
 *


 *
 
 
 */
public enum OrderType{
	
	PLAIN("普通订单"), 
	ON_LINE("网上订单"), 
	PLAIN_DIRECT("普通订单(直供)"), 
	ON_LINE_DIRECT( "网上订单(直供)"), 
	SALES_RETURN("销售退货"),
	Purchase_Return("采购退货"),
	Purchase_SPORADIC("零星采购"),
	Retail_Order("商品零售"),
	Retail_Return("零售退货");
	
	final String name;
	
	public String getName(){
    	return name;
    }

	private OrderType(String name){
		this.name = name;
	}
	
	public boolean isIn(OrderType...types){
		for(OrderType type : types){
			if(this == type){
				return true;
			}
		}
		return false;
	}
	
}
