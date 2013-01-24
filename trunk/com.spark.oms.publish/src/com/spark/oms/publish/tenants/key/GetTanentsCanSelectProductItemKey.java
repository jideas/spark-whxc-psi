package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ�⻧����ѡ��Ĳ�Ʒ�����Ŀ
 * 
 *   ProductItemInfo & GetTanentsCanSelectProductItem
 */
public class GetTanentsCanSelectProductItemKey {
	//��Ʒ����
	private String productCode;
	
	//��ѡ��Ŀ(����ʹ��)
	private GUID itemGuid;
	
	//�⻧��Ϣ
	private GUID tenantsId;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public GUID getItemGuid() {
		return itemGuid;
	}

	public void setItemGuid(GUID itemGuid) {
		this.itemGuid = itemGuid;
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	
}
