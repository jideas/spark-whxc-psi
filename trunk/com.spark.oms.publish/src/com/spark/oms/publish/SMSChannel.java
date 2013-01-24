package com.spark.oms.publish;

public enum SMSChannel {
	
	XuanWu("01","玄武");

	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * Ip
	 * @return
	 */
	private String ip;
	
	/**
	 * 端口
	 * @return
	 */
	private String port;
	
	/**
	 * 账号
	 * @return
	 */
	private String account;
	
	/**
	 * 密码
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
		throw new IllegalArgumentException(code + "不是一个正确的通道枚举");
	}
}