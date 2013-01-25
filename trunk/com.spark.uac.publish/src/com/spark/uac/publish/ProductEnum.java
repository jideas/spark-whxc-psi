package com.spark.uac.publish;

/**
 * 产品系列:完整版，商贸版，零售版
 */
public enum ProductEnum {
	FullEdition("LS_PSI001","完整版"),
	BusinessEdition("LS_PSI002","商贸版"),
	RetailEdition("LS_PSI003","连锁版"),
	MessageEdition("PS_SMS001","短信"),
	LS_MMCFullEdition("LS_MMC001","服装导购版"),
	LS_MMCSaleEdition("LS_MMC002","服装库存版"),
	LS_MMCPCEdition("LS_MMC002","服装PC版");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private ProductEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static ProductEnum getProduct(String code){
		if(FullEdition.code.equals(code)){
			return FullEdition;
		}else if(BusinessEdition.code.equals(code)){
			return BusinessEdition;
		}else if(RetailEdition.code.equals(code)){
			return RetailEdition;
		}else if(MessageEdition.code.equals(code)){
			return MessageEdition;
		}else if(LS_MMCFullEdition.code.equals(code)){
			return LS_MMCFullEdition;
		}else if(LS_MMCSaleEdition.code.equals(code)){
			return LS_MMCSaleEdition;
		}else if(LS_MMCPCEdition.code.equals(code)){
			return LS_MMCPCEdition;
		}else{
			return null;
		}
	}
}
