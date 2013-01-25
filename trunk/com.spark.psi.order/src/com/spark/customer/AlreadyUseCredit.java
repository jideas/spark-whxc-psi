package com.spark.customer;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>已用信用额度</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
public final class AlreadyUseCredit {
	private GUID id;//id
	private GUID customerId;//客户id
	private double aleardyUseCredit;//已用信用额度
	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}
	/**
	 * @return the customerId
	 */
	public GUID getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the aleardyUseCredit
	 */
	public double getAleardyUseCredit() {
		return aleardyUseCredit;
	}
	/**
	 * @param aleardyUseCredit the aleardyUseCredit to set
	 */
	public void setAleardyUseCredit(double aleardyUseCredit) {
		this.aleardyUseCredit = aleardyUseCredit;
	}
	
}
