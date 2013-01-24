package com.spark.oms.publish;

public enum TenantsSource {RelationshipList("01","��ϵ����"),
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
