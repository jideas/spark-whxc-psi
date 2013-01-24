package com.spark.oms.publish.receipt.entity;

public class PaymentRateInfo {
	/**
	 * 收入合计
	 */
	private double totalIncome;
	
	/**
	 * 支出合计
	 */
	private double totalOutlay;
	
	/**
	 * 支付项目
	 */
	private String type;
	
	/**
	 * 所选项目类型收入金额
	 */
	private double projectIncome;
	
	/**
	 * 所选项目支出合计
	 */
	private double projectOutlay;

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalOutlay() {
		return totalOutlay;
	}

	public void setTotalOutlay(double totalOutlay) {
		this.totalOutlay = totalOutlay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getProjectIncome() {
		return projectIncome;
	}

	public void setProjectIncome(double projectIncome) {
		this.projectIncome = projectIncome;
	}

	public double getProjectOutlay() {
		return projectOutlay;
	}

	public void setProjectOutlay(double projectOutlay) {
		this.projectOutlay = projectOutlay;
	}
	
	
}
