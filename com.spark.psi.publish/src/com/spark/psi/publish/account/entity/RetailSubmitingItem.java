package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 零售带交款数据<BR>
 * 查询方法：ListEntry<RetailSubmitingItem>+GetRetailSubmitingListKey
 * 
 */
public interface RetailSubmitingItem {

	/**
	 * ID
	 * @return the salesId
	 */
	public GUID getId();

	/**
	 * 销售名称
	 * @return the salesName
	 */
	public String getSalesName();

	/**
	 * 待交金额
	 * @return the amount
	 */
	public double getAmount();

	/**
	 * 时期范围
	 * @return the beginDate
	 */
	public long getBeginDate();

	/**
	 * 刷卡底单数量
	 * @return the cardRecordCount
	 */
	public int getCardRecordCount();

	/**
	 * 刷卡底单金额
	 * @return the cardRecordAmount
	 */
	public double getCardRecordAmount();

}
