package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 发布当前服务配置
 * @author mengyongfeng
 *
 */
public class PublishOrderServiceConfigTask extends SimpleTask {
	/**
	 * 租户ID
	 */
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	
	

}
