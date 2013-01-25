package com.spark.psi.publish;

public enum PartnerStatus{
	/**
	 * 正式客户
	 */
	Official("02","正式客户"),
	/**
	 * 潜在客户
	 */
	Potential("01","潜在客户");
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private PartnerStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public static PartnerStatus PartnerStatusByCode(String code){
		for(PartnerStatus status : PartnerStatus.values()){
	        if(status.getCode().equals(code)){
	        	return status;
	        }
        }
		return null;
	}
	
}
