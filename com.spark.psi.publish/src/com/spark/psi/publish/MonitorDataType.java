package com.spark.psi.publish;

/**
 * ����������ͣ�Ŀǰ��Ҫ��������Ŀ���أ�
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
