package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;

public interface ReceiptInfo {
	public GUID getId();
	public String getReceiptsNo();
	public String getPartnerName();
	public GUID getPartnerId();
	public DealingsWay getReceiptMode();
	public long getReceiptDate();
	public ReceiptStatus getStatus();
	public double getAmount();
	public double getReceiptedAmount();
	public String getRemark();
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	public ReceiptType getReceiptType();
	public ReceiptInfoItem[] getItems();
	public ReceiptLog[] getLogs();
}
