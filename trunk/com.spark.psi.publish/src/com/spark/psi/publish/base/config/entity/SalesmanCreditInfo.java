package com.spark.psi.publish.base.config.entity;

import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 
 * <p>������Ա��Ȩ</p>
 *


 *
 
 * @version 2012-5-16
 */
public interface SalesmanCreditInfo {

	/**
	 * ������Աid
	 * @return the salesmanId
	 */
	public EmployeeInfo getSalesman();


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