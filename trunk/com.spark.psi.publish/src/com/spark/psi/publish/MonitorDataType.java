package com.spark.psi.publish;

/**
 * 监控数据类型（目前主要用于销售目标监控）
 * 
 */
public enum MonitorDataType {

	SalesAmount("01"), ReceiptAmount("02");

	private MonitorDataType(String code) {
		this.code = code;
	}

	private String code;

	public String getCode() {
		return this.code;
	}

}
