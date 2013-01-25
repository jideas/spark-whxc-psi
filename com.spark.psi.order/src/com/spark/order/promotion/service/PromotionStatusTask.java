package com.spark.order.promotion.service;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderSimpleTaskFather;
import com.spark.order.promotion.intf.PromotionStatus2;

/**
 * <p>促销单状态改变事件</p> 
 * @version 2012-3-31
 */
public class PromotionStatusTask extends OrderSimpleTaskFather{
	private final GUID id;
	private final String oldstatus;//原状态
	private final PromotionStatus2 newstatus;//新状态
	private final String cause;//原因
	/**
	 * @param id
	 * @param oldstatus
	 * @param newstatus
	 */
	public PromotionStatusTask(GUID id, String oldstatus, PromotionStatus2 newstatus) {
		super();
		this.id = id;
		this.oldstatus = oldstatus;
		this.newstatus = newstatus;
		this.cause = null;
	}

	/**
	 * @param id
	 * @param oldstatus
	 * @param newstatus
	 * @param cause
	 * @param lenght
	 */
	public PromotionStatusTask(GUID id, String oldstatus,
			PromotionStatus2 newstatus, String cause) {
		super();
		this.id = id;
		this.oldstatus = oldstatus;
		this.newstatus = newstatus;
		this.cause = cause;
	}
	public String getCause() {
		return cause;
	}
	public GUID getId() {
		return id;
	}
	public String getOldstatus() {
		return oldstatus;
	}
	public PromotionStatus2 getNewstatus() {
		return newstatus;
	}
	@Override
	protected void setLenght(int lenght) {
		this.lenght = lenght;
	}

	@Override
	protected void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	
}
