package com.spark.oms.publish;

public enum TenantsIndustyEnum {
	RelationshipList("01","��ϵ����"),
	Seek("02","��������"),
	Visit("03","İ���ݷ�"),
	ConferenceMarketing("04","����Ӫ��"),
	WebsiteRegistration("05","��վע��"),
	Introduce("06","�⻧����"),
	Advisory("07","�⻧��ѯ"),
	ReturnVisit("08","�⻧�ط�");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private TenantsIndustyEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
}
