package com.spark.oms.publish;

public enum NetworkType {
	
	IsMobile("01", "�ƶ�"), 
	IsUnicom("02", "��ͨ"), 
	IsTelecom("03", "����"), 
	IsNetCom("04", "��ͨ");
	
	private String code;
	private String name;

	/**
	 * @param code
	 * @param name
	 */
	private NetworkType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public static NetworkType getNetworkTypeByCode(String code) {
		for (NetworkType network : NetworkType.values()) {
			if (network.getCode().equals(code)) {
				return network;
			}
		}
		throw new IllegalArgumentException(code + "����һ����ȷ����������ö��");
	}
}