package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 发票作废
 */
public class InvalidateInvoiceTask extends SimpleTask {

	/**
	 * 发票ID
	 */
	private GUID id;

	/**
	 * 作废原因
	 */
	private String reason;

	/**
	 * 构造函数
	 * 
	 * @param id
	 *            发票ID
	 */
	public InvalidateInvoiceTask(GUID id, String reason) {
		this.id = id;
		this.reason = reason;
	}

	/**
	 * 获取发票ID
	 * 
	 * @return
	 */
	public GUID getId() {
		return this.id;
	}

	/**
	 * 作废原因
	 * 
	 * @return
	 */
	public String getReason() {
		return this.reason;
	}
}
