package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;

/**
 * ���ۡ��ɹ������б�����ѯKey
 
 *
 */
public abstract class GetOrderListKey extends LimitKey {

	public enum OrderType{
		/** ���� */
		Order,
		/** �˻� */
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
		/** �������� */
		DeliveryDate("t.deliveryDate"),
		/** ������� */
		OrderNumber("t.billsNo"),
		/** �ͻ���Ӧ������ */
		PartnerShortName("t.partnerNamePY"),
		/** �������� */
		OrderType("t.billType"),
		/** ������� */
		Amount("t.totalAmount"),
		/** �Ƶ��� */
		Creator("t.creator"),
		/** �Ƶ����� */
		CreateDate("t.createDate"),
		/** ����״̬ */
		OrderStatus("t.status"),
		/** ������ */
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
