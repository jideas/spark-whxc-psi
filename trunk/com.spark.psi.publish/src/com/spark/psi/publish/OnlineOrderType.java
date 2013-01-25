package com.spark.psi.publish;

/**
 * 
 * <p>网上订单类型</p>
 *
 */
public enum OnlineOrderType{
	
	Common("普通订单","0"), 
	Booking("预订订单","1");
	
	final String name;
	private String code;
	
	public String getCode() {
		return code;
	}

	public String getName(){
    	return name;
    }

	private OnlineOrderType(String name,String code){
		this.name = name;
		this.code = code;
	}
	
	public static OnlineOrderType getType(String code)
	{
		if(Common.code.equals(code))
		{
			return Common;
		}
		else if(Booking.code.equals(code))
		{
			return Booking;
		}
		return null;
	}
}
