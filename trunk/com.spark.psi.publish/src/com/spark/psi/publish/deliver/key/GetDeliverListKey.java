package com.spark.psi.publish.deliver.key;

import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.LimitKey;

public class GetDeliverListKey extends LimitKey{

	private DeliverStatus[] status;
	private SortField sortField;
	public GetDeliverListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	public void setStatus(DeliverStatus... status) {
		this.status = status;
	}
	public DeliverStatus[] getStatus() {
		return status;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
	public SortField getSortField() {
		return sortField;
	}

	public static enum SortField{
		/** �������*/
		SheetNo("t.billsNo"),
		/** �������� */
		CreateDate("t.createDate"),
		/** �������� */
		DeliverDate("t.DeliverDate"),
		/** վ�� */
		StationName("t.stationName"),
		/** ������ */
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
