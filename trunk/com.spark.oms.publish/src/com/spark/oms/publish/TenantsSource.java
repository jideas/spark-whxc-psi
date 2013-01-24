package com.spark.oms.publish;

public enum TenantsSource {RelationshipList("01","关系名单"),
	Seek("02","主动查找"),
	Visit("03","陌生拜访"),
	ConferenceMarketing("04","会议营销"),
	WebsiteRegistration("05","网站注册"),
	Introduce("06","租户介绍"),
	Advisory("07","租户咨询"),
	ReturnVisit("08","租户回访");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private TenantsSource(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static TenantsSource getTenantsSource(String code){
		if(RelationshipList.code.equals(code)){
			return RelationshipList;
		}else if(Seek.code.equals(code)){
			return Seek;
		}else if(Seek.code.equals(code)){
			return Seek;
		}else if(Visit.code.equals(code)){
			return Visit;
		}else if(ConferenceMarketing.code.equals(code)){
			return ConferenceMarketing;
		}else if(WebsiteRegistration.code.equals(code)){
			return WebsiteRegistration;
		}else if(Introduce.code.equals(code)){
			return Introduce;
		}else if(Advisory.code.equals(code)){
			return Advisory;
		}else if(ReturnVisit.code.equals(code)){
			return ReturnVisit;
		}else{
			return null;
		}
	}
}
