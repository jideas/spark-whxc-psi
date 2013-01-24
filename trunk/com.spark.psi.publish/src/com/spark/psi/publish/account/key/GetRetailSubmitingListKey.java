/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.finance.invoice.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.publish.account.key;

import com.spark.psi.publish.LimitKey;


/**
 * 零售应交款记录列表查询KEY
 */
public class GetRetailSubmitingListKey extends LimitKey {

	/**
	 * 排序字段
	 */
	private SortField sortField;

	/**
	 * 构造函数
	 */
	public GetRetailSubmitingListKey(int offset,int count,boolean queryTotal) {
		super(offset, count, queryTotal); // 不分页
	}

	/**
	 * @return the sortField
	 */
	public SortField getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set
	 */
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * 排序字段
	 */
	public static enum SortField {

		None(""), //
		SalesName("saleEmpName"), //
		BeginDate("receiptDate"), //
		Amount("shouldMoney"),
		CardRecordCount("shouldCardCount"),
		CardRecordAmount("shouldCardMoney"),
		; //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
