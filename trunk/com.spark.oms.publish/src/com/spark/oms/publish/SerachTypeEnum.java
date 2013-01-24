package com.spark.oms.publish;

public enum SerachTypeEnum {
	Normarl(0,"查询"),
	special(1,"搜索"),
	advance(2,"高级");
	
	
	/**
	 * 代码
	 */
	private int code;

	/**
	 * 名称
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
