package com.spark.uac.publish;

public enum ProductSerialsEnum {
	PSI("LS_PSI","�������Ʒϵ��"),
	MMC("LS_MMC","��װ���Ʒϵ��"),
	SMS("PS_SMS","ͨ����");
	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
