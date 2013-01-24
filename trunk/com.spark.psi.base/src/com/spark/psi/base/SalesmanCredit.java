package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ա����<br>
 * ��ѯ������ʹ��Ա��ID��ѯSalesmanCredit����
 */
public interface SalesmanCredit {

	/**
	 * �����ÿͻ����ö������
	 * @return the customerCreditLimit
	 */
	public double getCustomerCreditLimit();

	/**
	 * �����ÿͻ���������
	 * @return the customerCreditDayLimit
	 */
	public int getCustomerCreditDayLimit();

	/**
	 * �����������ö��
	 * @return the availableTotalCreditLimit
	 */
	public double getAvailableTotalCreditLimit();

	/**
	 * �����ÿͻ�
	 * @return the customerCountUsed
	 */
	public int getCustomerCountUsed();

	/**
	 * �����ÿͻ����ö��
	 * @return the customerCreditUsed
	 */
	public double getCustomerCreditUsed();

	/**
	 * ������������
	 * @return the orderApprovalLimit
	 */
	public double getOrderApprovalLimit();
	
	public GUID getId();

}
