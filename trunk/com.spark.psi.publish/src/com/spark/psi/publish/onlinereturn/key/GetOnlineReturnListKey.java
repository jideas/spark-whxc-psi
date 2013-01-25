package com.spark.psi.publish.onlinereturn.key;

import com.spark.psi.publish.LimitKey;

public class GetOnlineReturnListKey extends LimitKey {

	public enum OnlineReturnTab {
		Submiting, Approving, Processing, Finished
	}

	public GetOnlineReturnListKey(int offset, int count, boolean queryTotal, OnlineReturnTab tab) {
		super(offset, count, queryTotal);
		this.tab = tab;
	}

	private OnlineReturnTab tab;

	public OnlineReturnTab getTab() {
		return tab;
	}
}
