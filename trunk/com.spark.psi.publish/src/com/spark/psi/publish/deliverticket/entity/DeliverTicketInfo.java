package com.spark.psi.publish.deliverticket.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;

/**
 * 
 * 发货单详情
 *
 */
public interface DeliverTicketInfo {

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
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	public String getAddress();
	public DeliverTicketType getDeliverTicketType();
	public DeliverTicketInfoItem[] getItems();
}
