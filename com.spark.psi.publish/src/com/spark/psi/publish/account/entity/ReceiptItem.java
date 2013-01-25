package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;

/**
 * 收款记录列表项<br>
 * 查询方法：ReceiptListEntity<ReceiptItem>+GetReceiptLogListKey
 * 
 */
public interface ReceiptItem {
	public GUID getId();
	public String getReceiptsNo();
	public String getPartnerName();
	public GUID getPartnerId();
	public DealingsWay getReceiptMode();
	public String getReason();
	public long getReceiptDate();
	public ReceiptStatus getStatus();
	public double getAmount();
	public double getReceiptedAmount();
	public String getRemark();
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	public ReceiptType getReceiptType();
	
}
