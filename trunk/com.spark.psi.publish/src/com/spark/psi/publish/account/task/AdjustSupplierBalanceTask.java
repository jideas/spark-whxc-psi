package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ӧ��������Ӧ����
 */
public class AdjustSupplierBalanceTask extends SimpleTask {

	/**
	 * �ͻ�ID
	 */
	private GUID supplierId;

	/**
	 * ��������ֵ��ʶ����Ӧ������ֵ��ʾ����Ӧ����
	 */
	private double adjustAmount;

	/**
	 * ԭ��
	 */
	private String reason;

	/**
	 * ���캯��
	 * 
	 * @param supplierId
	 * @param adjustAmount
	 */
	public AdjustSupplierBalanceTask(GUID supplierId, double adjustAmount,
			String reason) {
		this.supplierId = supplierId;
		this.adjustAmount = adjustAmount;
		this.reason = reason;
	}

	/**
	 * ��ȡ�ͻ�ID
	 * 
	 * @return
	 */
	public GUID getSupplierId() {
		return this.supplierId;
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
