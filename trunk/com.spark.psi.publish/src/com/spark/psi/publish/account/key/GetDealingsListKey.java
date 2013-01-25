package com.spark.psi.publish.account.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

/**
 * ��ѯָ����Ӧ�̻��߿ͻ���������ϸkey
 * 
 */
public class GetDealingsListKey extends LimitKey {

	/**
	 * �ͻ����߹�Ӧ��ID
	 */
	private GUID partnerId;

	/**
	 * ��ʼ����
	 */
	private long startDate;

	/**
	 * ��������
	 */
	private long endDate;

	/**
	 * ���캯��
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetDealingsListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * @return the startDate
	 */
	public long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the partnerId
	 */
	public GUID getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId
	 *            the partnerId to set
	 */
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

}
