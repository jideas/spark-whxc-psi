package com.spark.oms.publish.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 短信通道配置类
 * @author Administrator
 *
 */
public class MessageChannelConfig {

	private GUID id;
	private String account;
	private String password;
	private int mobilePhoneMax;
	private int PHSMax;		
	private String connectType;
	private String serviceType;
	private String ip;
	private String port;
	private String longPort;
	private int massSMSMax;
	private int multipleSMSMax;
	private int priority;
	private GUID msgchannel;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMobilePhoneMax() {
		return mobilePhoneMax;
	}
	public void setMobilePhoneMax(int mobilePhoneMax) {
		this.mobilePhoneMax = mobilePhoneMax;
	}
	public int getPHSMax() {
		return PHSMax;
	}
	public void setPHSMax(int pHSMax) {
		PHSMax = pHSMax;
	}
	public String getConnectType() {
		return connectType;
	}
	public void setConnectType(String connectType) {
		this.connectType = connectType;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getLongPort() {
		return longPort;
	}
	public void setLongPort(String longPort) {
		this.longPort = longPort;
	}
	public int getMassSMSMax() {
		return massSMSMax;
	}
	public void setMassSMSMax(int massSMSMax) {
		this.massSMSMax = massSMSMax;
	}
	public int getMultipleSMSMax() {
		return multipleSMSMax;
	}
	public void setMultipleSMSMax(int multipleSMSMax) {
		this.multipleSMSMax = multipleSMSMax;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public GUID getMsgchannel() {
		return msgchannel;
	}
	public void setMsgchannel(GUID msgchannel) {
		this.msgchannel = msgchannel;
	}
}