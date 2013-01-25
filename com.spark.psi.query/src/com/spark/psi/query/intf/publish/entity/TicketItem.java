package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverTicketType;

/**
 * ��������ѯʵ��<BR>
 * ��ѯ������<BR>
 * TicketListEntity+GetTicketListKey;
 *
 */
public interface TicketItem {

	public GUID getTicketId();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getUnit();
	public double getPrice();
	public double getCount();
	public double getAmount();
	public String getSheetNo();
	public long getCreateDate();
	public DeliverTicketType getDeliverTicketType();
	public GUID getMemberId();
	public String getMemberRealName();
}
