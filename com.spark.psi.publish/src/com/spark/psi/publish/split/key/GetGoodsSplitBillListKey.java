package com.spark.psi.publish.split.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;

public class GetGoodsSplitBillListKey extends LimitKey {

	public GetGoodsSplitBillListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private GoodsSplitStatus[] statuses;

	private long beginTime, endTime;

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
}
