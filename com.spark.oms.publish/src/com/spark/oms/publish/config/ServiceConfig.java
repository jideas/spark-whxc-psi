package com.spark.oms.publish.config;


public class ServiceConfig {
	
	/**
	 * 产品系列
	 */
	private String productSerial;
	
	/**
	 * 产品代码
	 */
	private String productCode;
	
	/**
	 * 用户数
	 */
	private int userNum;
	
	/**
	 * 服务开始日期 
	 */
	private long serverStartDate;
	
	/**
	 * 服务结束日期
	 */
	private long serverEndDate;
	
	/**
	 * 系统名称
	 */
	private  String sysName;
	
	private String  serverHostAddr;
	
	private String serverHostId;
	
	
	private ParamConfig paramConfig;
	
	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public long getServerStartDate() {
		return serverStartDate;
	}

	public void setServerStartDate(long serverStartDate) {
		this.serverStartDate = serverStartDate;
	}

	public long getServerEndDate() {
		return serverEndDate;
	}

	public void setServerEndDate(long serverEndDate) {
		this.serverEndDate = serverEndDate;
	}

	public ParamConfig getParamConfig() {
		return paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getServerHostAddr() {
		return serverHostAddr;
	}

	public void setServerHostAddr(String serverHostAddr) {
		this.serverHostAddr = serverHostAddr;
	}

	public String getServerHostId() {
		return serverHostId;
	}

	public void setServerHostId(String serverHostId) {
		this.serverHostId = serverHostId;
	}
	
}
