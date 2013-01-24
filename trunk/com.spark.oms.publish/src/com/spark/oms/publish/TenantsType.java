package com.spark.oms.publish;

public enum TenantsType {
	FormalTenants("01","��ʽ�⻧"),
	LossedTenants("02","��ʧ�⻧"),
	LossedPayMoneyTenants("0201","�˿��⻧");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
