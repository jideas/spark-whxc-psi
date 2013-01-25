package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 调整客户往来（应收余额）
 */
public class AdjustCustomerBalanceTask extends SimpleTask {

	/**
	 * 客户ID
	 */
	private GUID customerId;

	/**
	 * 调整金额（正值标识增加应收，负值表示减少应收）
	 */
	private double adjustAmount;

	/**
	 * 原因
	 */
	private String reason;

	/**
	 * 构造函数
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
	 * 获取客户ID
	 * 
	 * @return
	 */
	public GUID getCustomerId() {
		return this.customerId;
	}

	/**
	 * 获取调整金额
	 * 
	 * @return
	 */
	public double getAdjustAmount() {
		return adjustAmount;
	}

	/**
	 * 获取调整原因
	 * 
	 * @return
	 */
	public String getReason() {
		return this.reason;
	}
}
