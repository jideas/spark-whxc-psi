/**
 * 
 */
package com.spark.psi.report.dao.task;

import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class ReportGoodsPurchaseTask extends BaseTask {
	private GUID goodsItemId;

	private double ordAmount;
	private double ordCount;
	private double outstoAmount;
	private double receiptAmount;
	private double rtnAmount;
	private GUID deptGuid;
	private GUID orderPerson;
	private long createDate; 
	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
	/**
	 * @param goodsItemId the goodsItemId to set
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}
	/**
	 * @return the ordAmount
	 */
	public double getOrdAmount() {
		return ordAmount;
	}
	/**
	 * @param ordAmount the ordAmount to set
	 */
	public void setOrdAmount(double ordAmount) {
		this.ordAmount = ordAmount;
	}
	/**
	 * @return the ordCount
	 */
	public double getOrdCount() {
		return ordCount;
	}
	/**
	 * @param ordCount the ordCount to set
	 */
	public void setOrdCount(double ordCount) {
		this.ordCount = ordCount;
	}
	/**
	 * @return the outstoAmount
	 */
	public double getOutstoAmount() {
		return outstoAmount;
	}
	/**
	 * @param outstoAmount the outstoAmount to set
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
	 * @param receiptAmount the receiptAmount to set
	 */
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	/**
	 * @return the rtnAmount
	 */
	public double getRtnAmount() {
		return rtnAmount;
	}
	/**
	 * @param rtnAmount the rtnAmount to set
	 */
	public void setRtnAmount(double rtnAmount) {
		this.rtnAmount = rtnAmount;
	}
	/**
	 * @return the deptGuid
	 */
	public GUID getDeptGuid() {
		return deptGuid;
	}
	/**
	 * @param deptGuid the deptGuid to set
	 */
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}
	/**
	 * @return the orderPerson
	 */
	public GUID getOrderPerson() {
		return orderPerson;
	}
	/**
	 * @param orderPerson the orderPerson to set
	 */
	public void setOrderPerson(GUID orderPerson) {
		this.orderPerson = orderPerson;
	}
	/**
	 * @return the createDate
	 */
	public long getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
