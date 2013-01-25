package com.spark.psi.publish.deliverticket.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;

/**
 * 发货单列表<BR>
 * 查询方法：<BR>
 * GetDeliverTicketListKey+DeliverTicketListEntity;
 *
 */
public interface DeliverTicketItem {
	
	public GUID getId();
	public String getSheetNo();
	public GUID getOnlineOrderId();
	public String getOnlineOrderNo();
	public GUID getMemberId();
	public String getMemberRealName();
	public String getMobilePhone();
	public GUID getStationId();
	public String getStationName();
	public String getRemark();
	public double getDisAmount();
	public double getTotalAmount();
	public String getCreator();
	public long getCreateDate();
	public String getAddress();
	public DeliverTicketType getDeliverTicketType();
}
