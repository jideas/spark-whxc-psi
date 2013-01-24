package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.RetailSubmitingItem;

/**
 * 零售带交款数据<BR>
 * 查询方法：ListEntry<RetailSubmitingItem>+GetRetailSubmitingListKey
 * 
 */
public class RetailSubmitingItemImpl implements RetailSubmitingItem{

	/**
	 * 销售ID
	 */
	private GUID id;

	/**
	 * 销售名称
	 */
	private String salesName;

	/**
	 * 待交金额
	 */
	private double amount;

	/**
	 * 时期范围
	 */
	private long beginDate;

	/**
	 * 刷卡底单数量
	 */
	private int cardRecordCount;

	/**
	 * 刷卡底单金额
	 */
	private double cardRecordAmount;

	/**
	 * @return the salesId
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the salesId to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the salesName
	 */
	public String getSalesName() {
		return salesName;
	}

	/**
	 * @param salesName
	 *            the salesName to set
	 */
	public void setSalesName(String salesName) {
		this.salesName = salesName;
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
	 * @return the beginDate
	 */
	public long getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the cardRecordCount
	 */
	public int getCardRecordCount() {
		return cardRecordCount;
	}

	/**
	 * @param cardRecordCount
	 *            the cardRecordCount to set
	 */
	public void setCardRecordCount(int cardRecordCount) {
		this.cardRecordCount = cardRecordCount;
	}

	/**
	 * @return the cardRecordAmount
	 */
	public double getCardRecordAmount() {
		return cardRecordAmount;
	}

	/**
	 * @param cardRecordAmount
	 *            the cardRecordAmount to set
	 */
	public void setCardRecordAmount(double cardRecordAmount) {
		this.cardRecordAmount = cardRecordAmount;
	}

}
