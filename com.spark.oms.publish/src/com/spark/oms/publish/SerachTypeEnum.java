package com.spark.oms.publish;

public enum SerachTypeEnum {
	Normarl(0,"��ѯ"),
	special(1,"����"),
	advance(2,"�߼�");
	
	
	/**
	 * ����
	 */
	private int code;

	/**
	 * ����
	 */
	private String name;
	
	private SerachTypeEnum(int code,String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static SerachTypeEnum getSerachTypeEnum(int code){
		for(SerachTypeEnum e : SerachTypeEnum.values()){
			if(e.getCode() == code){
				return e;
			}
		}
		return null;
	}
}
