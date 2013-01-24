package com.spark.oms.publish.order.key;

import com.spark.oms.publish.base.key.LimitKey.SortType;

/**
 * ��ȡ������ֹ����
 * GetAppStopOrderService &List<OrderServiceInfo>
 */
public class GetAppStopOrderServiceKey {
	
	/**
	 * OrderServiceSuspendStatusEnum ȡֵ
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
		
		suspendApplayDate("SuspendApplayDate"),//��������
		tenantsName("TenantsName"),//�⻧����
		ProductSerial("ProductSerial"),//��Ʒϵ��
		productName("ProductName"),//��Ʒ����
		productItemName("ProductItemName"),//���
		BillingCycle("BillingCycle"),//�Ʒ�����
		saleManager("SaleManager");//�ͻ�����

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
}
