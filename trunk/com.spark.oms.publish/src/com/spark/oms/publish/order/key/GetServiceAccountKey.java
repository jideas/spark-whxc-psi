package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取租户的产品系列租金账户余额
 * @author mengyongfeng
 *
 */
public class GetServiceAccountKey {
	/**
	 * 租户ID
	 */
	private GUID tenantsGUID;
	
	/**
	 * 产品系列
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
