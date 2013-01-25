package com.spark.psi.publish.base.store.entity;

/**
 * ϵͳ�ֿ�ժҪ����<br>
 * ��ѯ������ֱ�Ӳ�ѯStoreSummaryData����
 * 
 */
public class StoreSummaryData {

	/**
	 * �ֿ�������
	 */
	private int totalCount;

	/**
	 * �ѽ��г�ʼ����Ʒ����Ʒ�б�Ĳֿ������
	 */
	private int initedCount;

	/**
	 * �����õĲֿ�������
	 */
	private int usingCount;

	/**
	 * ���캯��
	 * 
	 * @param directSupply
	 * @param totalCount
	 * @param initedCount
	 * @param usingCount
	 */
	public StoreSummaryData( int totalCount,
			int initedCount, int usingCount) {
		super(); 
		this.totalCount = totalCount;
		this.initedCount = initedCount;
		this.usingCount = usingCount;
	} 

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the initedCount
	 */
	public int getInitedCount() {
		return initedCount;
	}

	/**
	 * @return the usingCount
	 */
	public int getUsingCount() {
		return usingCount;
	}

}
