package com.spark.psi.publish.onlinereturn.key;

import com.spark.psi.publish.LimitKey;

public class GetOnlineReturnListKey extends LimitKey {

	private String realName;
	private String stationName;
	private long createDateBegin;
	private long createDateEnd;
	
	public enum OnlineReturnTab {
		Submiting, Approving, Processing, Finished
	}

	public GetOnlineReturnListKey(int offset, int count, boolean queryTotal, OnlineReturnTab tab) {
		super(offset, count, queryTotal);
		this.tab = tab;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public long getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(long createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public long getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(long createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	private OnlineReturnTab tab;

	public OnlineReturnTab getTab() {
		return tab;
	}
}
