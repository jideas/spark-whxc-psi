package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>��ѯ������������б�</p>
 *


 *
 
 * @version 2012-3-16
 */
public class GetSalesDistributeOrderListKey extends LimitKey{

	
	
	public GetSalesDistributeOrderListKey()
    {
	    super(0, 100, true);
    }
	
	private SortField sortField;	


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
		/** ��ַ */
		Address("collate_gbk(t.address)"),
		/** ������ */
		None("t.payDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
		
	}
	


}
