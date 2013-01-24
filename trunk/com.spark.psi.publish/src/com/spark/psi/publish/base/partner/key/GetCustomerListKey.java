package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.SortType;

/**
 * 
 * <p>查询客户列表</p>
 *


 *
 
 
 */
public final class GetCustomerListKey extends GetPartnerListKey{

	public GetCustomerListKey(PartnerStatus PartnerStatus){
	    super(0, 0, false);
	    this.PartnerStatus = PartnerStatus;
	    this.sortType = SortType.Desc;
    }
	
	/**
	 * 查询所有客户，不通过权限过滤
	 * @param PartnerStatus
	 * @param isShowAll
	 */
	public GetCustomerListKey(PartnerStatus PartnerStatus,boolean isShowAll){
	    this(PartnerStatus);
	    this.isShowAll = isShowAll;
    }
	
	private boolean isShowAll = false;
	
	/**
	 * 客户状态
	 */
	private PartnerStatus PartnerStatus;
	
	
	private SortField sortField = SortField.None;
	
	
	public PartnerStatus getPartnerStatus(){
    	return PartnerStatus;
    }

	public void setPartnerStatus(PartnerStatus PartnerStatus){
    	this.PartnerStatus = PartnerStatus;
    }
	

	public SortField getSortField(){
    	return sortField;
    }



	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }



	public boolean isShowAll(){
    	return isShowAll;
    }



	public void setShowAll(boolean isShowAll){
    	this.isShowAll = isShowAll;
    }



	/**
	 * 排序字段
	 */
	public static enum SortField {
		
		/**
		 * 不排序
		 */
		None("createDate"), //
		/** 客户名称 */
		Name("namePY"), //
		/**
		 * 客户编号
		 */
		CustomerNo("customerNo"),
		/** 交易总金额 */
		TotalTradeAmount("totalSalesAmount"), //
		/** 交易总次数 */
		TotalTradeCount("totalSalesTimes"), //
		/** 最近交易日期 */
		RecentTradeDate("lastSalesDate"), //
		/** 应收应付金额 */
		BalanceAmount(""), //
		/** 信用额度*/
		CreditAmount("creditAmount"),
		/** 账期天数 */
		CreditDay("accountPeriod"),
		/** 联系人姓名*/
		ContactPerson("linkmanNamePy"),
		/** 销售员工姓名*/
		SalesName("businessPerson"),
		Province("province"), City("city"), Address("collate_gbk(t.address)"); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}


	public static void main(String[] args){
	    System.out.println(SortField.valueOf("None").getFieldName());
    }
	
}
