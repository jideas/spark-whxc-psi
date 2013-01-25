package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ���۽���
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
	 * ���캯��
	 * 
	 * @param id
	 *            ����ID
	 */
	public ConfirmRetailRecordTask(GUID id) {
		super();
		this.id = id;
	}

}
