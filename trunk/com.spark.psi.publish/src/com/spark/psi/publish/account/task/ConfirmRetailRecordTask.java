package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 零售交款
 * 
 */
public class ConfirmRetailRecordTask extends SimpleTask {

	/**
	 * ID
	 */
	private GUID id;

	/**
	 * @return the salesId
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 构造函数
	 * 
	 * @param id
	 *            销售ID
	 */
	public ConfirmRetailRecordTask(GUID id) {
		super();
		this.id = id;
	}

}
