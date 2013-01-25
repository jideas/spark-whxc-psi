package com.spark.uac.publish;

public enum ProductSerialsEnum {
	PSI("LS_PSI","进销存产品系列"),
	MMC("LS_MMC","服装版产品系列"),
	SMS("PS_SMS","通信类");
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private ProductSerialsEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static ProductSerialsEnum getProductSeriesSeries(String code){
		if(PSI.code.equals(code)){
			return PSI;
		}else if(SMS.code.equals(code)){
			return SMS;
		}else if(MMC.code.equals(code)){
			return MMC;
		}else{
			return null;
		}
	}
}
