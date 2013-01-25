package com.spark.b2c.publish.JointVenture.key;

import com.spark.psi.publish.JointSettlementStatus;
import com.spark.psi.publish.LimitKey;

public class GetJointSettlementListKey extends LimitKey {

	public GetJointSettlementListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private JointSettlementStatus[] status; 
	private SortField sortField;
	public JointSettlementStatus[] getStatus() {
		return status;
	}
	public void setStatus(JointSettlementStatus... status) {
		this.status = status;
	}
	
	public SortField getSortField() {
		return sortField;
	}
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public static enum SortField{
		/** �������*/
		sheetNo("t.sheetNo"),
		/** ��Ӧ������ */
		supplierName("t.shortName"),
		/** ���۽�� */
		saleAmount("t.salesAmount"),
		/** ��ɽ�� */
		percentageAmount("t.percentageAmount"),
		/** Ӧ����� */
		Amount("t.amount"),
		/** �Ѹ���� */
		PaidAmount("t.paidAmount"),
		/** ��ʼ���� */
		beginDate("t.beginDate"),
		/** �������� */
		endDate("t.endDate"),
		/**
		 * ����״̬
		 */
		Status("t.status"),
		/**
		 * ��
		 */
		None("t.createDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
		
	}
}
