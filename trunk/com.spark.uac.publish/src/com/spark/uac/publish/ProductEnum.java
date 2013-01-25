package com.spark.uac.publish;

/**
 * ��Ʒϵ��:�����棬��ó�棬���۰�
 */
public enum ProductEnum {
	FullEdition("LS_PSI001","������"),
	BusinessEdition("LS_PSI002","��ó��"),
	RetailEdition("LS_PSI003","������"),
	MessageEdition("PS_SMS001","����"),
	LS_MMCFullEdition("LS_MMC001","��װ������"),
	LS_MMCSaleEdition("LS_MMC002","��װ����"),
	LS_MMCPCEdition("LS_MMC002","��װPC��");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
