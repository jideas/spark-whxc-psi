package com.spark.oms.publish.receipt.key;


/**
 * ��ȡ��Ӫ��֧��¼��
 * GetPaymentRateItemKey
 */
public class GetPaymentRateItemKey {
	/**
	 * ��ʼ����
	 */
	private long startDate;
	
	/**
	 * ��������
	 */
	private long endDate;
	
	/**
	 * ��Ŀ���
	 */
	private String projectType;

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	
}
