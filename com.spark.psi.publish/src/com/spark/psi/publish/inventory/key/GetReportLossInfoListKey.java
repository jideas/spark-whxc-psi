package com.spark.psi.publish.inventory.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;

public class GetReportLossInfoListKey extends LimitKey {

	/**
	 * ²éÑ¯Ê±ÆÚ
	 */
	private QueryTerm queryTerm;
	
//	private ReportLossStatus[] status;
	
	private SortField sortField;
	
	private String searchKey;
	private ViewStatus viewStatus;
	
	public enum ViewStatus {
		submitting, approvling, finished
	}
	
	public GetReportLossInfoListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}

	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}

//	public ReportLossStatus[] getStatus() {
//		return status;
//	}
//
//	public void setStatus(ReportLossStatus... status) {
//		this.status = status;
//	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public ViewStatus getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(ViewStatus viewStatus) {
		this.viewStatus = viewStatus;
	}



	/**
	 * ÅÅÐò×Ö¶Î
	 */
	public static enum SortField {
		None("createDate"), //
		sheetCode("sheetNo"), //
		storeName("storeName"), //
		createDate("createDate"),
		status("status"), //
		submittingDate("applyDate"), //
		reporterName("creator"), //
		finishDate("approvalDate"); //

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
