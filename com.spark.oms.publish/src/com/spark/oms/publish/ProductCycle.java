package com.spark.oms.publish;

public enum ProductCycle {
	Month("01","月付"),
	Quarter("02","季付"),
	HalfYear("03","半年付"),
	Year("04","年付");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private ProductCycle(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static ProductCycle getProductCycle(String code){
		if(Month.code.equals(code)){
			return Month;
		}else if(Quarter.code.equals(code)){
			return Quarter;
		}else if(HalfYear.code.equals(code)){
			return HalfYear;
		}else if(Year.code.equals(code)){
			return Year;
		}else{
			return null;
		}
	}
}
