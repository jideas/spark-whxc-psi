/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.account.intf.key.dealing
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.key.dealing;

import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.SortType;

/**
 * <p>
 * 客户/供应商余额列表Key
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GetBalanceListKey {

	/**
	 * 查询范围
	 */
	private QueryScope queryScope;
	/**
	 * 查询偏移（从0开始）
	 */
	private int offset;

	/**
	 * 查询数量
	 */
	private int count;

	/**
	 * 是否查询总数
	 */
	private boolean queryTotal;

	/**
	 * 排序方式
	 */
	private SortType sortType;

	/**
	 * 搜索文本
	 */
	private String searchText;
	/**
	 * 客户 or 供应商
	 */
	private PartnerType partnerType;

	public GetBalanceListKey(PartnerType partnerType) {
		this.partnerType = partnerType;
	}

	public QueryScope getQueryScope() {
		return queryScope;
	}

	public void setQueryScope(QueryScope queryScope) {
		this.queryScope = queryScope;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public void setPartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType() {
		return partnerType;
	}

	private SortField sortField = SortField.None;

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {

		/**
		 * 不排序
		 */
		None(""), 
		/** 客户名称 */
		PartnerName(""), 
		/** 余额 */
		Balance("amount");
		
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
