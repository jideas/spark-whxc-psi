package com.spark.psi.publish.base.partner.key;

/**
 * 得到最新供应商编号
 */
public class GetNewSupplierNoKey {
	public GetNewSupplierNoKey(String supplierType) {
		this.supplierType = supplierType;
	}

	private String supplierType;

	public String getSupplierType() {
		return supplierType;
	}
}
