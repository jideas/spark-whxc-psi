package com.spark.oms.publish.receipt.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 确认收款
 * @author mengyongfeng
 *
 */
public class AdjustReceiptStatusTask extends SimpleTask {
	//服务Id
	private GUID id;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	
	
}
