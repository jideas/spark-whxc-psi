package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������ǰ��������
 * @author mengyongfeng
 *
 */
public class PublishOrderServiceConfigTask extends SimpleTask {
	/**
	 * �⻧ID
	 */
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	
	

}
