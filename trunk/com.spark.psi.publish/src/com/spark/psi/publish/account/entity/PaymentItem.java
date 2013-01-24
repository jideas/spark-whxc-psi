package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;

/**
 * �����¼�б���<br>
 * ��ѯ������PaymentListEntity+GetPaymentListKey
 * 
 */
public interface PaymentItem {

	public GUID getId();
	public String getPaymentNo();
	public GUID getPartnerId();
	public String getPartnerName();
	public PaymentType getPaymentType();
	public String getDenyReason();
	public long getPayDate();
	public String getStatus();
	public double getAmount();
	public double getPaidAmount();
	public String getRemark();
	public GUID getApprovePerson();
	public String getApprovePersonName();
	public long getApproveDate();
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
}
