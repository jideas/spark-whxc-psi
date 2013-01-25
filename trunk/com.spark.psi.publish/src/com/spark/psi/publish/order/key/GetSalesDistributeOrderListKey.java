package com.spark.psi.publish.order.key;

import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>查询销售配货订单列表</p>
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
		/** 交货日期 */
		DeliveryDate("t.deliveryDate"),
		/** 订单编号 */
		OrderNumber("t.billsNo"),
		/** 客户供应商名称 */
		PartnerShortName("t.partnerNamePY"),
		/** 地址 */
		Address("collate_gbk(t.address)"),
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
	


}
