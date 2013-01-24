/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.finance.invoice.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.publish.account.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

/**
 * 开票主列表查询KEY
 * 
 * @version 2011-11-28
 */

public class GetInvoiceListKey extends LimitKey {

	/**
	 * 
	 * @param offset
	 * @param count
	 * @param queryTotal
	 */
	public GetInvoiceListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	/**
	 * 客户ID
	 */
	private GUID customerId;

	/**
	 * 部门ID
	 */
	private GUID departmentId;

	/**
	 * 查询时期
	 */
	private QueryTerm queryTerm;

	/**
	 * 排序字段
	 */
	private SortField sortField;

	/**
	 * @return the customerId
	 */
	public GUID getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the departmentId
	 */
	public GUID getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(GUID departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the queryTerm
	 */
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	/**
	 * @param queryTerm
	 *            the queryTerm to set
	 */
	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
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

		None(""), InvoiceDate("invodate"), CustomerName("cusproname"), InvoiceTypeName("invoType"), Amount(
				"invoAmount"), Drawer("invoPersonName"), Recorder("createPerson"), InvoiceNumber("invoCode");

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
