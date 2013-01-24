package com.spark.oms.publish;

/**
 *角色类型：销售专员、助理(人力资源)、销管专员、财务专员、产品专员、销售总监、事业部领导
 */
public enum RoleType {

	SalesCommissionerRole("JS001","销售专员"),
	HRAssistantRole("JS002","助理(人力资源)"),
	PinTubeCommissionerRole("JS003","销管专员"),
	FinanceSpecialistRole("JS004","财务专员"),
	ProductSpecialistRole("JS005","产品专员"),
	SalesDirectorRole("JS006","销售总监"),
	DivisionLeadersRole("JS007","事业部领导");
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private RoleType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static RoleType getRoleType(String code){
		if(SalesCommissionerRole.code.equals(code)){
			return SalesCommissionerRole;
		} else if(HRAssistantRole.code.equals(code)){
			return HRAssistantRole;
		} else if(PinTubeCommissionerRole.code.equals(code)){
			return PinTubeCommissionerRole;
		} else if(FinanceSpecialistRole.code.equals(code)){
			return FinanceSpecialistRole;
		} else if(ProductSpecialistRole.code.equals(code)){
			return ProductSpecialistRole;
		} else if(SalesDirectorRole.code.equals(code)){
			return SalesDirectorRole;
		} else if(DivisionLeadersRole.code.equals(code)){
			return DivisionLeadersRole;
		} else{
			return null;
		}
	}
}
