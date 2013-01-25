package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 撤消授权客户
 */
public class UnauthorizeCustomerTask extends SimpleTask {

	/**
	 * 客户ID列表
	 */
	private GUID[] customerIds;

	/**
	 * 构造函数
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
