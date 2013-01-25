package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �����ͻ�������Ӧ����
 */
public class AdjustCustomerBalanceTask extends SimpleTask {

	/**
	 * �ͻ�ID
	 */
	private GUID customerId;

	/**
	 * ��������ֵ��ʶ����Ӧ�գ���ֵ��ʾ����Ӧ�գ�
	 */
	private double adjustAmount;

	/**
	 * ԭ��
	 */
	private String reason;

	/**
	 * ���캯��
	 * 
	 * @param customerId
	 * @param adjustAmount
	 */
	public AdjustCustomerBalanceTask(GUID customerId, double adjustAmount,
			String reason) {
		this.customerId = customerId;
		this.adjustAmount = adjustAmount;
		this.reason = reason;
	}

	/**
	 * ��ȡ�ͻ�ID
	 * 
	 * @return
	 */
	public GUID getCustomerId() {
		return this.customerId;
	}

	/**
	 * ��ȡ�������
	 * 
	 * @return
	 */
	public double getAdjustAmount() {
		return adjustAmount;
	}

	/**
	 * ��ȡ����ԭ��
	 * 
	 * @return
	 */
	public String getReason() {
		return this.reason;
	}
}
