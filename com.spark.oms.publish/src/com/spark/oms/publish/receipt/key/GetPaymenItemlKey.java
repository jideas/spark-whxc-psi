package com.spark.oms.publish.receipt.key;


/**
 * 运营收支记录详情列表
 * 注意：开始日期，结束日期均不为空
 * 
 * PaymentItemInfo & GetPaymenItemlKey
 */
public class GetPaymenItemlKey {
	
	/**
	 * 开始日期
	 */
	private long startDate;
	
	/**
	 * 结束日期
	 */
	private long endDate;

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

	

}
