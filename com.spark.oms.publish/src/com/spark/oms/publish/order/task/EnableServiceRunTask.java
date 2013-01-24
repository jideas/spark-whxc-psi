package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

//--------接口task---- 更新刚开通服务的系统

public class EnableServiceRunTask extends SimpleTask{
	/**
	 * 租户Id
	 */
	private GUID tenantsId;
	
	/**
	 * 服务表示：产品系列
	 */
	private String productSerial;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}
	
	
	

}
