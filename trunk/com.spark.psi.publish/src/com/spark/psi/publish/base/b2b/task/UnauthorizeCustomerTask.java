package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ȩ�ͻ�
 */
public class UnauthorizeCustomerTask extends SimpleTask {

	/**
	 * �ͻ�ID�б�
	 */
	private GUID[] customerIds;

	/**
	 * ���캯��
	 * 
	 * @param customerIds
	 */
	public UnauthorizeCustomerTask(GUID[] customerIds) {
		super();
		this.customerIds = customerIds;
	}

	/**
	 * @return the customerIds
	 */
	public GUID[] getCustomerIds() {
		return customerIds;
	}

	/**
	 * @param customerIds
	 *            the customerIds to set
	 */
	public void setCustomerIds(GUID[] customerIds) {
		this.customerIds = customerIds;
	}

}
