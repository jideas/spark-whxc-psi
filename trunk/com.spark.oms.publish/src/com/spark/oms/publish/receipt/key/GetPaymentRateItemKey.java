package com.spark.oms.publish.receipt.key;


/**
 * 获取运营收支记录表
 * GetPaymentRateItemKey
 */
public class GetPaymentRateItemKey {
	/**
	 * 开始日期
	 */
	private long startDate;
	
	/**
	 * 结束日期
	 */
	private long endDate;
	
	/**
	 * 项目类别
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
