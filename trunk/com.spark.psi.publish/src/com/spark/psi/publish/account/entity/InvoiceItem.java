package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 开票记录列表项<br>
 * 查询方法：ListEntry<InvoiceItem>+GetInvoiceListKey
 * 
 */
public interface InvoiceItem {

	/**
	 * 发票ID
	 * @return the id
	 */
	public GUID getId();
	/**
	 * 开票日期
	 * @return the date
	 */
	public long getDate();

	/**
	 * 客户名称
	 * @return the customerName
	 */
	public String getCustomerName();
	
	public String getCustomerFullName();

	/**
	 * 发票类型名称
	 * @return the invoiceTypeName
	 */
	public String getInvoiceTypeName();

	/**
	 * 开票金额
	 * @return the amount
	 */
	public double getAmount();

	/**
	 * 开票人
	 * @return the drawer
	 */
	public String getDrawer();

	/**
	 * 记录人
	 * @return the recorder
	 */
	public String getRecorder();

	/**
	 * 发票号
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber();
	/**
	 * 客户ID
	 * @return the customerId
	 */
	public GUID getCustomerId();
	/**
	 * 发票类型代码
	 * @return the invoiceTypeCode
	 */
	public String getInvoiceTypeCode();
	
	public boolean isInvalided();
	
	public String getInvalidReason();//	作废原因
	public String getInvalidPerson();//	作废人
	public long getInvalidDate();//	作废日期
}
