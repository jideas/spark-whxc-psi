package com.spark.oms.publish.receipt.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ȷ���տ�
 * @author mengyongfeng
 *
 */
public class AdjustReceiptStatusTask extends SimpleTask {
	//����Id
	private GUID id;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	
	
}
