package com.spark.psi.publish;

public enum DeliverTicketType {

	Common("01","∆’Õ®"),
	Reissue("01","≤π∑¢");
	
	private String code;
	private String name;
	private DeliverTicketType(String code,String name)
	{
		this.name = name;
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static DeliverTicketType getDeliverTicketType(String code)
	{
		if(Common.getCode().endsWith(code))
		{
			return Common;
		}
		else if(Reissue.getCode().endsWith(code))
		{
			return Reissue;
		}
		else
		{
			return null;
		}
	}
}
