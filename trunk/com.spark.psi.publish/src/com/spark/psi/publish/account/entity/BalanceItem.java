package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * �ͻ����߹�Ӧ�̵�Ӧ�ջ���Ӧ����Ϣ<BR>
 * ��ѯ������<br>
 * (1)��ѯ���пͻ���BalanceItem�б�BalanceListEntity+GetCustomerBalanceListKey<br>
 * (2)��ѯ���й�Ӧ�̵�BalanceItem�б�BalanceListEntity+GetSupplierBalanceListKey<br>
 * (3)��ѯ������ʼ���б�BalanceListEntity+GetInitBalanceItemListKey<br>
 */
public interface BalanceItem {

	public GUID getId();
	public GUID getPartnerId();
	public String getPartnerName();
	public String getNamePY();
	public String getPartnerShortName();
	public String getType();
	public double getAmount();
	public double getInitAmount();

}
