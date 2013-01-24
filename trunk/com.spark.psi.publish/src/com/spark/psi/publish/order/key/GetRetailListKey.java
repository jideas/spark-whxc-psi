package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.RetailStatus;

/**
 * 
 * <p>��ѯ���۵��б�</p>
 * 
 *


 *
 
 * @version 2012-3-6
 */
public class GetRetailListKey extends LimitKey{

	public GetRetailListKey(){
	    super(0, -1, true);
    }
	

	private QueryTerm queryTerm;  
	
	private QueryScope queryScope;
	
	private SortField sortField;

	private RetailStatus retailstatus;	
	
	public RetailStatus getRetailStatus(){
    	return retailstatus;
    }




	public void setRetailStatus(RetailStatus retailstatus){
    	this.retailstatus = retailstatus;
    }




	public QueryTerm getQueryTerm(){
    	return queryTerm;
    }




	public void setQueryTerm(QueryTerm queryTerm){
    	this.queryTerm = queryTerm;
    }




	public QueryScope getQueryScope(){
    	return queryScope;
    }




	public void setQueryScope(QueryScope queryScope){
    	this.queryScope = queryScope;
    }




	public SortField getSortField(){
    	return sortField;
    }




	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }




	public static enum SortField{
//	case 0:
//		retailKey.setSortCloumName("payDate");
//		break;
//	case 1:
//		retailKey.setSortCloumName("billsNo");
//		break;
//	case 2:
//		retailKey.setSortCloumName("consignee");
//		break;
//	case 3:
//		retailKey.setSortCloumName("address");
//		break;
//	case 4:
//		retailKey.setSortCloumName("totalAmount");
//		break;
//	case 0:
//		retailKey.setSortCloumName("receiptOrPayDate");
//		break;
//	case 1:
//		retailKey.setSortCloumName("billsNo");
//		break;
//	case 2:
//		retailKey.setSortCloumName("retailType");
//		break;
//	case 3:
//		retailKey.setSortCloumName("totalAmount");
//		break;
//	case 4:
//		retailKey.setSortCloumName("payType");
//		break;
//	case 5:
//		retailKey.setSortCloumName("operPerson");
//		break;
//	case 6:
//		retailKey.setSortCloumName("createPerson");
//		break;
		
		/** ���ݱ�� */
		OrderNumber("t.number"),
		/** ���� */
		OrderType("t.type"),
		/** ��� */
		Amount("t.totalAmount"),
		/** �Ƶ��� */
		Creator("t.creator"),
		/** ������ */
		Operator("t.operater"),
		/** �������� */
		CreateDate("t.balanceDate"),
		/** �������� */
		DeliveryDate("t.deliveryDate"),
		/** ���㷽ʽ */
		DealingsWay("t.balanceType"),
		/** �ջ��� */
		Consignee("t.consignee"),
		/** �ջ���ַ */
		DeliveryAddress("t.address"),
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
