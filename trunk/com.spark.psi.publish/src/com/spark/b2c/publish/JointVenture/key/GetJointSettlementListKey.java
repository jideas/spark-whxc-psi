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
		/** 订单编号*/
		sheetNo("t.sheetNo"),
		/** 供应商名称 */
		supplierName("t.shortName"),
		/** 销售金额 */
		saleAmount("t.salesAmount"),
		/** 提成金额 */
		percentageAmount("t.percentageAmount"),
		/** 应付金额 */
		Amount("t.amount"),
		/** 已付金额 */
		PaidAmount("t.paidAmount"),
		/** 开始日期 */
		beginDate("t.beginDate"),
		/** 结束日期 */
		endDate("t.endDate"),
		/**
		 * 单据状态
		 */
		Status("t.status"),
		/**
		 * 无
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
