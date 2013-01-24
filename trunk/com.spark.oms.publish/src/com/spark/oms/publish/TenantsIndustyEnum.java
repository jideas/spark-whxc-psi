package com.spark.oms.publish;

public enum TenantsIndustyEnum {
	RelationshipList("01","关系名单"),
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
	
	private TenantsIndustyEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
}
