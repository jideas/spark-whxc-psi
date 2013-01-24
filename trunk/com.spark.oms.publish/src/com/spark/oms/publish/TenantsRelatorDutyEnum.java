package com.spark.oms.publish;

public enum TenantsRelatorDutyEnum {
	
	Boss("01","总经理"),
	Assistor("02","助理"),
	leader("03","领导"),
	worker("04","工作人员");
	
	String code,name;
	
	TenantsRelatorDutyEnum(String code,String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static TenantsRelatorDutyEnum getEntityEnum(String code){
		
//		if(code.equals(Boss.getCode())){
//			return Boss;
//		}else if(code.equals(Assistor.getCode())){
//			return Assistor;
//		}
//		return null;
		
		for(TenantsRelatorDutyEnum duty:TenantsRelatorDutyEnum.values()){
			if(duty.getCode().equals(code)){
				return duty;
			}
		}
		
		return null;
	}

}
