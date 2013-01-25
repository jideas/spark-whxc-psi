package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.DealingsWay;

public interface PaymentInfo {

	public GUID getId();
	public String getPaymentNo();
	public GUID getPartnerId();
	public String getPartnerName();
	public PaymentType getPaymentType();
	public String getDenyReason();
	public long getPayDate();
	public PaymentStatus getStatus();
	public double getAmount();
	public double getPaidAmount();
	public String getRemark();
	public GUID getApprovePerson();
	public String getApprovePersonName();
	public long getApproveDate();
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();;
	public PaymentInfoItem[] getItems();
	public PaymentLog[] getLogs();
	public DealingsWay getDealingsWay();
}
