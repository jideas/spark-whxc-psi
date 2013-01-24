package com.spark.oms.publish;
/**
 * 订单状态：未生效；生效；作废订单
 */
public enum OrderServerStatus {
	//变更、续期订单在交款前的状态
	UnVaildstatus("00","未生效"),
	Vaildstatus("01","生效"),
	Invalidstatus("99","作废");
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private OrderServerStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static OrderServerStatus getOrderStatus(String code){
		if(UnVaildstatus.code.equals(code)){
			return UnVaildstatus;
		}else if(Vaildstatus.code.equals(code)){
			return Vaildstatus;
		}else if(Invalidstatus.code.equals(code)){
			return Invalidstatus;
		}else{
			return null;
		}
	}
}
