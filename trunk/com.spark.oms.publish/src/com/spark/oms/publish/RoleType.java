package com.spark.oms.publish;

/**
 *��ɫ���ͣ�����רԱ������(������Դ)������רԱ������רԱ����ƷרԱ�������ܼࡢ��ҵ���쵼
 */
public enum RoleType {

	SalesCommissionerRole("JS001","����רԱ"),
	HRAssistantRole("JS002","����(������Դ)"),
	PinTubeCommissionerRole("JS003","����רԱ"),
	FinanceSpecialistRole("JS004","����רԱ"),
	ProductSpecialistRole("JS005","��ƷרԱ"),
	SalesDirectorRole("JS006","�����ܼ�"),
	DivisionLeadersRole("JS007","��ҵ���쵼");
	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
