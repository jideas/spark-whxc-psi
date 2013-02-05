package com.spark.psi.publish.split.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;

public class GetGoodsSplitBillListKey extends LimitKey {

	public GetGoodsSplitBillListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private GoodsSplitStatus[] statuses;

	private long beginTime, endTime;

	private SortField sortField = SortField.BillNumber;

	public void setStatus(GoodsSplitStatus... status) {
		statuses = status;
	}

	public GoodsSplitStatus[] getStatuses() {
		return statuses;
	}

	public void setStatuses(GoodsSplitStatus[] statusArray) {
		this.statuses = statusArray;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * ÅÅÐò×Ö¶Î
	 */
	public static enum SortField {
		None(""), //
		/**
		 * µ¥¾Ý±àºÅ
		 */
		BillNumber("t.billNo"), //

		Creator("collate_gbk(t.creator)"),

		CreateDate("t.createDate"),

		Approvaler("collate_gbk(t.approvalPerson)"),

		ApprovalDate("t.approvalDate"),

		Distributer("collate_gbk(t.distributPerson)"),

		DistributDate("t.distributDate"),

		FinishDate("t.finishDate"),

		Status("t.status"),
		;
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}

	}
}
