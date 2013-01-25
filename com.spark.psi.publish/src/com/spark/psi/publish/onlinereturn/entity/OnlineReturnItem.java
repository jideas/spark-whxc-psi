package com.spark.psi.publish.onlinereturn.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.constant.OnlineReturnStatus;

public interface OnlineReturnItem {
	public GUID getId();

	public String getBillsNo();

	public String getOnlineBillsNo();

	public String getCustomer();

	public String getCreator();

	public double getAmount();

	public long getCreateDate();

	public String getFinishPerson();

	public long getFinishedDate();

	public String getStationName();

	public OnlineReturnStatus getStatus();
}
