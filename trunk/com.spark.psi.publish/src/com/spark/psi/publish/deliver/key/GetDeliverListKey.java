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
		/** 订单编号*/
		SheetNo("t.billsNo"),
		/** 发货日期 */
		CreateDate("t.createDate"),
		/** 配送日期 */
		DeliverDate("t.DeliverDate"),
		/** 站点 */
		StationName("t.stationName"),
		/** 不排序 */
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
