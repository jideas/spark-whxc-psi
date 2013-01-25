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
		/** 订单编号*/
		BillsNo("t.billsNo"),
		/** 计划完成日期 */
		PlanDate("t.planDate"),
		/** 下单日期 */
		CreateDate("t.createDate"),
		/** 商品数量 */
		GoodsCount("t.goodsCount"),
		/** 完成日期 */
		FinishDate("t.finishDate"),
		/** 不排序 */
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
