package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;


/**
 * ��ѯ��Ӧ���������key
 * 
 */
public class GetSupplierBalanceListKey extends LimitKey {

	private SortField sortField;
	
	/**
	 * ���캯��
	 */
	public GetSupplierBalanceListKey() {
		super(0, 0, true); // ����ҳ
	}
	
	
	public SortField getSortField() {
		return sortField;
	}


	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}


	/**
	 * �����ֶ�
	 */
	public static enum SortField {

		/**
		 * ������
		 */
		None(""), 
		/** ���� */
		PartnerName(""), 
		/** ��� */
		Balance("");
		
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
