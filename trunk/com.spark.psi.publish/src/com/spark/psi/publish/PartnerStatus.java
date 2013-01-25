package com.spark.psi.publish;

public enum PartnerStatus{
	/**
	 * ��ʽ�ͻ�
	 */
	Official("02","��ʽ�ͻ�"),
	/**
	 * Ǳ�ڿͻ�
	 */
	Potential("01","Ǳ�ڿͻ�");
	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
