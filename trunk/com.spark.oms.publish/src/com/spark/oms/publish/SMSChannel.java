package com.spark.oms.publish;

public enum SMSChannel {
	
	XuanWu("01","����");

	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	/**
	 * Ip
	 * @return
	 */
	private String ip;
	
	/**
	 * �˿�
	 * @return
	 */
	private String port;
	
	/**
	 * �˺�
	 * @return
	 */
	private String account;
	
	/**
	 * ����
	 * @return
	 */
	private String ps;
	
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private SMSChannel(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public static SMSChannel getSMSChannelByCode(String code) {
		for (SMSChannel channel : SMSChannel.values()) {
			if (channel.getCode().equals(code)) {
				return channel;
			}
		}
		throw new IllegalArgumentException(code + "����һ����ȷ��ͨ��ö��");
	}
}