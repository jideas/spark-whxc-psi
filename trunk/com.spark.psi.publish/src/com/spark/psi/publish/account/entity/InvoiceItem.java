package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʊ��¼�б���<br>
 * ��ѯ������ListEntry<InvoiceItem>+GetInvoiceListKey
 * 
 */
public interface InvoiceItem {

	/**
	 * ��ƱID
	 * @return the id
	 */
	public GUID getId();
	/**
	 * ��Ʊ����
	 * @return the date
	 */
	public long getDate();

	/**
	 * �ͻ�����
	 * @return the customerName
	 */
	public String getCustomerName();
	
	public String getCustomerFullName();

	/**
	 * ��Ʊ��������
	 * @return the invoiceTypeName
	 */
	public String getInvoiceTypeName();

	/**
	 * ��Ʊ���
	 * @return the amount
	 */
	public double getAmount();

	/**
	 * ��Ʊ��
	 * @return the drawer
	 */
	public String getDrawer();

	/**
	 * ��¼��
	 * @return the recorder
	 */
	public String getRecorder();

	/**
	 * ��Ʊ��
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber();
	/**
	 * �ͻ�ID
	 * @return the customerId
	 */
	public GUID getCustomerId();
	/**
	 * ��Ʊ���ʹ���
	 * @return the invoiceTypeCode
	 */
	public String getInvoiceTypeCode();
	
	public boolean isInvalided();
	
	public String getInvalidReason();//	����ԭ��
	public String getInvalidPerson();//	������
	public long getInvalidDate();//	��������
}
