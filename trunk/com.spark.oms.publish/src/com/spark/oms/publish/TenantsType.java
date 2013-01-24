package com.spark.oms.publish;

public enum TenantsType {
	FormalTenants("01","正式租户"),
	LossedTenants("02","流失租户"),
	LossedPayMoneyTenants("0201","退款租户");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private TenantsType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static TenantsType getTenantsType(String code){
		if(FormalTenants.code.equals(code)){
			return FormalTenants;
		}else if(LossedTenants.code.equals(code)){
			return LossedTenants;
		}else{
			return null;
		}
	}
}
