package com.spark.oms.publish.receipt.entity;

public class PaymentRateInfo {
	/**
	 * ����ϼ�
	 */
	private double totalIncome;
	
	/**
	 * ֧���ϼ�
	 */
	private double totalOutlay;
	
	/**
	 * ֧����Ŀ
	 */
	private String type;
	
	/**
	 * ��ѡ��Ŀ����������
	 */
	private double projectIncome;
	
	/**
	 * ��ѡ��Ŀ֧���ϼ�
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
