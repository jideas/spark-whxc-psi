package com.spark.oms.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

//--------�ӿ�task---- ���¸տ�ͨ�����ϵͳ

public class EnableServiceRunTask extends SimpleTask{
	/**
	 * �⻧Id
	 */
	private GUID tenantsId;
	
	/**
	 * �����ʾ����Ʒϵ��
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
