package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 调整供应商往来（应付余额）
 */
public class AdjustSupplierBalanceTask extends SimpleTask {

	/**
	 * 客户ID
	 */
	private GUID supplierId;

	/**
	 * 调整金额（正值标识增加应付，负值表示减少应付）
	 */
	private double adjustAmount;

	/**
	 * 原因
	 */
	private String reason;

	/**
	 * 构造函数
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
	 * 获取客户ID
	 * 
	 * @return
	 */
	public GUID getSupplierId() {
		return this.supplierId;
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
