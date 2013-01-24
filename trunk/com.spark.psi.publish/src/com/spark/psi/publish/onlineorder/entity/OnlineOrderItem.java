package com.spark.psi.publish.onlineorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 网上订单列表<BR>
 * 查询方法：<BR>
 * OlineOrderListEntity+GetOnlineOrderListKey;
 */
public interface OnlineOrderItem {

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
	public GUID getConsignorId();
	public String getConsignor();
	public long getConsignedDate();
	public GUID getDeliverPersonId();
	public String getDeliverPerson();
	public long getDeliverDate();
	public GUID getArrivedConfirmId();
	public String getArrivedConfirm();
	public long getArrivedConfirmDate();
	public String getNoVerificationReason();
	public OnlineOrderInfoItem[] getItems();
	public boolean isReturnFlag();
	public boolean isToDoor();
}
