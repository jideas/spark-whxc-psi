package com.spark.psi.publish.onlinereturn.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.constant.OnlineReturnStatus;

public interface OnlineReturnInfo {

	public GUID getRECID();

	public String getBillsNo();

	public GUID getMemberId();

	public String getMemberName();

	public GUID getOnlineBillsId();

	public String getOnlineBillsNo();

	public GUID getStationId();

	public String getStationName();

	public double getAmount();

	public OnlineReturnStatus getStatus();

	public String getReturnReason();

	public String getStopReason();

	public String getRejectReason();

	public GUID getCreatorId();

	public String getCreator();

	public long getCreateDate();

	public GUID getApprovorId();

	public String getApprovor();

	public long getApproveDate();

	public List<OnlineReturnDet> getItems();

	public GUID getFinishPerson();

	public String getFinishPersonName();

	public long getFinishedDate();

	public String getMemberPhone();

	public long getOnlineCreateDate();

	public double getSalesAmount();
	
	public int getVantages();
}
