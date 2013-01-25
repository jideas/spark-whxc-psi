package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 创建开票记录
 */
public class InvoiceTask extends SimpleTask {

	/**
	 * 客户ID
	 */
	private GUID customerId;

	/**
	 * 发票类型
	 */
	private String invoiceTypeCode;

	/**
	 * 开票金额
	 */
	private double amount;

	/**
	 * 开票人
	 */
	private String drawer;

	/**
	 * 发票号码
	 */
	private String invoiceNumber;

	/**
	 * 开票日期
	 */
	private long invoiceDate;

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}

	/**
	 * @param invoiceType
	 *            the invoiceType to set
	 */
	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param drawer
	 *            the drawer to set
	 */
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	/**
	 * @param invoiceDate
	 *            the invoiceDate to set
	 */
	public void setInvoiceDate(long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the customerId
	 */
	public GUID getCustomerId() {
		return customerId;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the drawer
	 */
	public String getDrawer() {
		return drawer;
	}

	/**
	 * @return the invoiceDate
	 */
	public long getInvoiceDate() {
		return invoiceDate;
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

}
