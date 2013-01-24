package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.DealingsWay;

/**
 * ������ϸ��Ŀ ��ѯ������<br>
 * (1)��ѯָ���ͻ����߹�Ӧ�̵�������ϸ�б�ListEntry<DealingsItem>+GetDealingsListKey<br>
 */
public interface BalanceInfoItem {

	public GUID getId();
	public GUID getPartnerId();
	public GUID getBillsId();
	public String getBillsNo();
	public DealingsType getBillsType();
	public DealingsWay getReceiptType();
	public double getPlanAmount();
	public double getRealAmount();
	public double getBalance();
	public long getCreateDate();
	public String getRemark();
	public GUID getAccountBillsId();
	public String getAccountBillsNo();
	public boolean isShowLink();
}
