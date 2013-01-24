package com.spark.oms.publish.receipt.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 客户退款操作
 * @author mengyongfeng
 *
 */
public class DoRefundActionTask extends Task<DoRefundActionTask.Method>{
	public enum Method{
		Apply,Confirm;
	}
	
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

}
