package com.spark.oms.publish.product.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class ProductIsUseTask extends SimpleTask {

	private GUID recid;
	private boolean Status;

	public ProductIsUseTask(GUID recid) {
		super();
		this.recid = recid;
		Status = false;
	}

	public GUID getRecid() {
		return recid;
	}

	public void setRecid(GUID recid) {
		this.recid = recid;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}