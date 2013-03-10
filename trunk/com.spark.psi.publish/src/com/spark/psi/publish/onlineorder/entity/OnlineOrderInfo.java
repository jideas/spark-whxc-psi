package com.spark.psi.publish.onlineorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 网上订单详情<BR>
 * 查询方法：<BR>
 * ID+OnlineOrderInfo;
 *
 */
public interface OnlineOrderInfo {

	public GUID getConsignorId();
	public String getConsignor();
	public long getConsignedDate();
	public GUID getDeliverPersonId();
	public String getDeliverPerson();
	public long getDeliverDate();
	public GUID getArrivedConfirmId();
	public String getArrivedConfirm();
	public long getArrivedConfirmDate();
	public GUID getId();
	public String getBillsNo();
	public GUID getMemberId();
	public String getRealName();
	public String getConsignee();
	public String getConsigneeTel();
	public String getAddress();
	public long getDeliveryeDate();
	public String getRemark();
	public double getDisAmount();
	public double getTotalAmount();
	public String getType();
	public GUID getStationId();
	public String getStationName();
	public String getStatus();
	public long getCreateDate();
	public String getVerificationCode();
	public String getNoVerificationReason();
	public OnlineOrderInfoItem[] getItems();
	public boolean isReturnFlag();
	public boolean isToDoor();
	public double getDeliveryCost();
	public double getVantagesCost();
	public String getPayType();
}
