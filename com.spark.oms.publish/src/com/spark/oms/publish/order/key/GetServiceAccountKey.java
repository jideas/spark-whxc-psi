package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ�⻧�Ĳ�Ʒϵ������˻����
 * @author mengyongfeng
 *
 */
public class GetServiceAccountKey {
	/**
	 * �⻧ID
	 */
	private GUID tenantsGUID;
	
	/**
	 * ��Ʒϵ��
	 */
	private String productSerials;

	public GUID getTenantsGUID() {
		return tenantsGUID;
	}

	public void setTenantsGUID(GUID tenantsGUID) {
		this.tenantsGUID = tenantsGUID;
	}

	public String getProductSerials() {
		return productSerials;
	}

	public void setProductSerials(String productSerials) {
		this.productSerials = productSerials;
	}

}
