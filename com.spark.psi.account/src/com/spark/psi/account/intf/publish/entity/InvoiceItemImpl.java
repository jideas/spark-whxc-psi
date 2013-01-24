package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.InvoiceItem;

/**
 * ��Ʊ��¼�б���<br>
 * ��ѯ������ListEntry<InvoiceItem>+GetInvoiceListKey
 * 
 */
public class InvoiceItemImpl implements InvoiceItem{

	/**
	 * ��ƱID
	 */
	private GUID id;

	/**
	 * ��Ʊ����
	 */
	private long date;

	/**
	 * �ͻ�ID
	 */
	private GUID customerId;

	/**
	 * �ͻ�����
	 */
	private String customerName;

	private String customerFullName;
	
	/**
	 * ��Ʊ���ʹ���
	 */
	private String invoiceTypeCode;

	/**
	 * ��Ʊ��������
	 */
	private String invoiceTypeName;

	/**
	 * ��Ʊ���
	 */
	private double amount;

	/**
	 * ��Ʊ��
	 */
	private String drawer;

	/**
	 * ��¼��
	 */
	private String recorder;

	/**
	 * ��Ʊ��
	 */
	private String invoiceNumber;
	
	private boolean isInvalided;
	
	private String invalidReason;//	����ԭ��
	private String invalidPerson;//	������
	private long invalidDate;//	��������
	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	/**
	 * @return the invoiceTypeName
	 */
	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	/**
	 * @param invoiceTypeName
	 *            the invoiceTypeName to set
	 */
	public void setInvoiceType(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the drawer
	 */
	public String getDrawer() {
		return drawer;
	}

	/**
	 * @param drawer
	 *            the drawer to set
	 */
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	/**
	 * @return the recorder
	 */
	public String getRecorder() {
		return recorder;
	}

	/**
	 * @param recorder
	 *            the recorder to set
	 */
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the customerId
	 */
	public GUID getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the invoiceTypeCode
	 */
	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}

	/**
	 * @param invoiceTypeCode
	 *            the invoiceTypeCode to set
	 */
	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}

	/**
	 * @param invoiceTypeName
	 *            the invoiceTypeName to set
	 */
	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public boolean isInvalided() {
		return isInvalided;
	}

	public void setInvalided(boolean isInvalided) {
		this.isInvalided = isInvalided;
	}

	public String getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}

	public String getInvalidPerson() {
		return invalidPerson;
	}

	public void setInvalidPerson(String invalidPerson) {
		this.invalidPerson = invalidPerson;
	}

	public long getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(long invalidDate) {
		this.invalidDate = invalidDate;
	}

}
