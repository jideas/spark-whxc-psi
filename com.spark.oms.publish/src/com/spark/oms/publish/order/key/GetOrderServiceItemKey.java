package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductCategory;

/**
 * ��ȡ������ǰ�����б���ǰ������ʷ����
 * GetOrderServiceItemKey & orderInfo
 */
public class GetOrderServiceItemKey {
	
	public enum Service{CurrentService,HisoryService};
	
	/**
	 * �⻧id
	 */
	private GUID tenantId;
	public Service service;
	private ProductCategory productCategory = ProductCategory.Lease;
	
	
	
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public GUID getTenantId() {
		return tenantId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setTenantId(GUID tenantId) {
		this.tenantId = tenantId;
	}
}
