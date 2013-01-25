package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ա���ö����Ϣ�б���<br>
 * ��ѯ������
 * (1):context.find(ListEntity<SalesmanCreditItem>,GetSalesManCreditListKey)
 * (2):context.find(SalesmanCreditItem,empid);
 * 
 */
public interface SalesmanCreditItem {

	/**
	 * ������Աid
	 * @return the salesmanId
	 */
	public GUID getSalesmanId();

	/**
	 * ������Ա����
	 * @return the salesmanName
	 */
	public String getSalesmanName();

	/**
	 * ������Ա����
	 * @return the departmentInfo
	 */
	public String getDepartmentInfo();

	/**
	 * ���������ö��
	 * @return the customerCreditLimit
	 */
	public double getCustomerCreditLimit();

	/**
	 * ��������������
	 * @return the customerCreditDayLimit
	 */
	public int getCustomerCreditDayLimit();

	/**
	 * �����õ��ۼ����ö��
	 * @return the availableTotalCreditLimit
	 */
	public double getAvailableTotalCreditLimit();

	/**
	 * �����õĿͻ�����
	 * @return the customerCountUsed
	 */
	public int getCustomerCountUsed();
	/**
	 * �����õ����ö���ۼ�ֵ
	 * @return the customerCreditUsed
	 */
	public double getCustomerCreditUsed();

	/**
	 * ��������޶�
	 * @return the orderApprovalLimit
	 */
	public double getOrderApprovalLimit();

}
