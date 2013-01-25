package com.spark.psi.publish.produceorder.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;

public class GetProduceOrderListKey extends LimitKey{

	private ProduceOrderStatus[] status;
	private QueryTerm queryTerm;
	private QueryScope queryScope;
	private SortField sortField;
	
	public ProduceOrderStatus[] getStatus() {
		return status;
	}

	public void setStatus(ProduceOrderStatus... status) {
		this.status = status;
	}

	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

	public QueryScope getQueryScope() {
		return queryScope;
	}

	public void setQueryScope(QueryScope queryScope) {
		this.queryScope = queryScope;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public GetProduceOrderListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	
	public enum SortField
	{
		/** �������*/
		BillsNo("t.billsNo"),
		/** �ƻ�������� */
		PlanDate("t.planDate"),
		/** �µ����� */
		CreateDate("t.createDate"),
		/** ��Ʒ���� */
		GoodsCount("t.goodsCount"),
		/** ������� */
		FinishDate("t.finishDate"),
		/** ������ */
		None("t.planDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
	}

}
