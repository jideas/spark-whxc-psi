/**
 * 
 */
package com.spark.psi.report.dao.task;

import com.jiuqi.dna.core.type.GUID;

/**
 * ²É¹ºÊý¾Ý
 */
public class ReportPurchaseTask extends BaseTask {
	private GUID cusProGuid;
	private double ordAmount;
	private double outstoAmount;
	private double receiptAmount;
	private double deductionAmount;
	private double rtnAmount; 
	private long createDate; 

	/**
	 * @return the cusProGuid
	 */
	public GUID getCusProGuid() {
		return cusProGuid;
	}

	/**
	 * @param cusProGuid
	 *            the cusProGuid to set
	 */
	public void setCusProGuid(GUID cusProGuid) {
		this.cusProGuid = cusProGuid;
	}

	/**
	 * @return the ordAmount
	 */
	public double getOrdAmount() {
		return ordAmount;
	}

	/**
	 * @param ordAmount
	 *            the ordAmount to set
	 */
	public void setOrdAmount(double ordAmount) {
		this.ordAmount = ordAmount;
	}

	/**
	 * @return the outstoAmount
	 */
	public double getOutstoAmount() {
		return outstoAmount;
	}

	/**
	 * @param outstoAmount
	 *            the outstoAmount to set
	 */
	public void setOutstoAmount(double outstoAmount) {
		this.outstoAmount = outstoAmount;
	}

	/**
	 * @return the receiptAmount
	 */
	public double getReceiptAmount() {
		return receiptAmount;
	}

	/**
	 * @param receiptAmount
	 *            the receiptAmount to set
	 */
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	/**
	 * @return the deductionAmount
	 */
	public double getDeductionAmount() {
		return deductionAmount;
	}

	/**
	 * @param deductionAmount
	 *            the deductionAmount to set
	 */
	public void setDeductionAmount(double deductionAmount) {
		this.deductionAmount = deductionAmount;
	}

	/**
	 * @return the rtnAmount
	 */
	public double getRtnAmount() {
		return rtnAmount;
	}

	/**
	 * @param rtnAmount
	 *            the rtnAmount to set
	 */
	public void setRtnAmount(double rtnAmount) {
		this.rtnAmount = rtnAmount;
	}
 
	/**
	 * @return the createDate
	 */
	public long getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
