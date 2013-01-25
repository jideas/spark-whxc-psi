package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;

/**
 * 销售、采购订单列表界面查询Key
 
 *
 */
public abstract class GetOrderListKey extends LimitKey {

	public enum OrderType{
		/** 订单 */
		Order,
		/** 退货 */
		Return
	}
	
	public GetOrderListKey(int offset, int count, boolean queryTotal, OrderType... orderTypes){
	    super(offset, count, queryTotal);
	    this.orderTypes = orderTypes;
    }

	private QueryTerm queryTerm;  
	
	private QueryScope queryScope;
	
	private SortField sortField;
	
	private OrderStatus[] orderstatus;
	
	private OrderType[] orderTypes;
	
	
	
	public OrderType[] getOrderTypes(){
    	return orderTypes;
    }



	public OrderStatus[] getOrderStatus(){
    	return orderstatus;
    }



	public void setOrderStatus(OrderStatus... orderstatus){
    	this.orderstatus = orderstatus;
    }



	public QueryScope getQueryScope(){
    	return queryScope;
    }



	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    }



	public QueryTerm getQueryTerm(){
    	return queryTerm;
    }



	public void setQueryTerm(QueryTerm queryTerm){
    	this.queryTerm = queryTerm;
    }



	public SortField getSortField(){
    	return sortField;
    }



	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }



	public static enum SortField{
		/** 交货日期 */
		DeliveryDate("t.deliveryDate"),
		/** 订单编号 */
		OrderNumber("t.billsNo"),
		/** 客户供应商名称 */
		PartnerShortName("t.partnerNamePY"),
		/** 订单类型 */
		OrderType("t.billType"),
		/** 订单金额 */
		Amount("t.totalAmount"),
		/** 制单人 */
		Creator("t.creator"),
		/** 制单日期 */
		CreateDate("t.createDate"),
		/** 处理状态 */
		OrderStatus("t.status"),
		/** 不排序 */
		None("t.deliveryDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
		
	}
	
	

}
