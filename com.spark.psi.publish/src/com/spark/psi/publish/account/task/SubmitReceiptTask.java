package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptType;

/**
 * �ύ�տ�Task
 */
public class SubmitReceiptTask extends SimpleTask {
	private GUID id;//	��ʶ

	public GUID getId() {
		return id;
	}

	public SubmitReceiptTask(GUID id) {
		super();
		this.id = id;
	}
	
	
}
