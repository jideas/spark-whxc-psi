package com.spark.psi.publish;

/**
 * 
 * <p>���϶�������</p>
 *
 */
public enum OnlineOrderType{
	
	Common("��ͨ����","0"), 
	Booking("Ԥ������","1");
	
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
