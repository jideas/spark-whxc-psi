package com.spark.psi.publish.order.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * 
 * <p>查询客户的销售交易记录</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 周利均
 * @version 2012-2-22
 */
public class GetSalesOrderByCustomerKey extends LimitKey{
	
	private final GUID id;
	
	/**
	 * 查询指定客户的未完成的交易
	 * @param id 客户id
	 */
	public GetSalesOrderByCustomerKey(final GUID id){
		super(0,100,false);
		this.id = id;
	}
	
	/**
	 * 查询指定客户的已完成的交易记录
	 * @param id 客户id
	 * @param isCompleted 是否只查询已完成的交易 默认为false
	 */
	public GetSalesOrderByCustomerKey(final GUID id,boolean isCompleted){
		super(0,100,false);
		this.id = id;
		this.isCompleted = isCompleted;
	}

	public GUID getId(){
    	return id;
    }
	
	private QueryTerm queryTerm;
	
	private SortField sortField;

	private boolean isCompleted = false;
	
	public static enum SortField{
		/** 订单编号 */
		OrderNumber("t.billsNo"),
		/** 订单类型 */
		OrderType("t.type"),
		/** 订单金额 */
		Amount("t.totalAmount"),
		/** 制单人 */
		CreatePerson("t.createPerson"),
		/** 制单日期 */
		CreateDate("t.createDate"),
		/** 处理状态 */
		OrderStatus("t.status"),
		/** 不排序 */
		None("t.payDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	/**
	 * @return the sortField
	 */
	public SortField getSortField() {
		return sortField;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
}
