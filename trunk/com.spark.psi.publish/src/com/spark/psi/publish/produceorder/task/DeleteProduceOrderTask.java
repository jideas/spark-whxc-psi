package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
/**
 * ɾ����������
 *
 */
public class DeleteProduceOrderTask extends SimpleTask {

	private GUID id;

	public GUID getId() {
		return id;
	}

	public DeleteProduceOrderTask(GUID id) {
		super();
		this.id = id;
	}
	
}
