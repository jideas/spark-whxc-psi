package com.spark.oms.publish.order.key;

import com.spark.oms.publish.base.key.LimitKey.SortType;

/**
 * 获取申请终止服务
 * GetAppStopOrderService &List<OrderServiceInfo>
 */
public class GetAppStopOrderServiceKey {
	
	/**
	 * OrderServiceSuspendStatusEnum 取值
	 */
	private String  status;
	private String serachkey;
	private SortField sortField;
	protected SortType sortType;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public static enum SortField {
		
		suspendApplayDate("SuspendApplayDate"),//申请日期
		tenantsName("TenantsName"),//租户名称
		ProductSerial("ProductSerial"),//产品系列
		productName("ProductName"),//产品名称
		productItemName("ProductItemName"),//规格
		BillingCycle("BillingCycle"),//计费周期
		saleManager("SaleManager");//客户经理

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
}
