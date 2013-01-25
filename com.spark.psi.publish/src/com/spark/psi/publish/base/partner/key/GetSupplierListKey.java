package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.SortType;


/***
 * 
 * <p>查询供应商列表</p>
 * 


 *
 
 
 */
public class GetSupplierListKey extends GetPartnerListKey{

	private boolean queryAll;
	
	public GetSupplierListKey(){
		super(0,20,false);
	    this.sortType = SortType.Desc;
	}
	
	public GetSupplierListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }
	
	

	
	private SortField sortField = SortField.None;
	
	

	public SortField getSortField(){
    	return sortField;
    }



	public boolean isQueryAll(){
    	return queryAll;
    }

	public void setQueryAll(boolean queryAll){
    	this.queryAll = queryAll;
    }

	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }



	/**
	 * 排序字段
	 */
	public static enum SortField {  
		/**
		 * 不排序
		 */
		None("createDate"), //
		Number("supplierNo"),
		SupplierType("supplierType"),
		TaxRate("taxRate"),
		SupplierNo("supplierNo"),
		/** 客户名称 */
		Name("namePY"), //
		/** 交易总金额 */
		TotalTradeAmount("totalPurchaseAmount"), //
		/** 交易总次数 */
		TotalTradeCount("totalPurchaseTimes"), //
		/** 最近交易日期 */
		RecentTradeDate("lastPurchaseDate"), //
		/** 应收应付金额 */
		BalanceAmount("balanceAmount"), //
		/** 联系人姓名*/
		ContactPerson("linkmanName"); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

	
}
