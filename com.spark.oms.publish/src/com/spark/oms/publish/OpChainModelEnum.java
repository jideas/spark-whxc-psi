package com.spark.oms.publish;

public enum OpChainModelEnum {
	Single("01","单店"),
	Chain("02","连锁");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private OpChainModelEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OpChainModelEnum getOpChainModel(String code){
		if(Single.code.equals(code)){
			return Single;
		}else if(Chain.code.equals(code)){
			return Chain;
		}else{
			return null;
		}
	}
}
