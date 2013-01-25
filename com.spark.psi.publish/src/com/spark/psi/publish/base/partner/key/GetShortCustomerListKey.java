package com.spark.psi.publish.base.partner.key;

import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>查询供应商列表（只获取简要信息）</p>
 * 速度比较快


 *
 
 * @version 2012-4-10
 */
public class GetShortCustomerListKey extends LimitKey{

	public GetShortCustomerListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }	

	public GetShortCustomerListKey(){
	    super(0, 20, false);
    }	

	public GetShortCustomerListKey(SearchType searchType){
	    super(0, 20, false);
	    this.searchType = searchType;
    }	
	
	private boolean hasRetail;
	
	public boolean isHasRetail() {
		return hasRetail;
	}

	public void setHasRetail(boolean hasRetail) {
		this.hasRetail = hasRetail;
	}

	private SortField sortField = SortField.None;
	
	private SearchType searchType = SearchType.All;
	
	
	public SearchType getSearchType(){
    	return searchType;
    }

	public void setSearchType(SearchType searchType){
    	this.searchType = searchType;
    }

	public SortField getSortField(){
    	return sortField;
    }



	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }

	/**
	 * 
	 * <p>数据过滤方式</p>
	 * 数据过滤方式
	
	
	 *
	 
	 * @version 2012-4-10
	 */
	public static enum SearchType{
		/**查询全部*/
		All,
		/**按权限过滤*/
		Auth
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {
		
		/**
		 * 不排序
		 */
		None(""), //
		/** 客户名称 */
		CustomerName(""),
		Address(""); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}

	
}
