package com.spark.oms.publish.receipt.key;


/**
 * ��Ӫ��֧��¼�����б�
 * ע�⣺��ʼ���ڣ��������ھ���Ϊ��
 * 
 * PaymentItemInfo & GetPaymenItemlKey
 */
public class GetPaymenItemlKey {
	
	/**
	 * ��ʼ����
	 */
	private long startDate;
	
	/**
	 * ��������
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
