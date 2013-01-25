/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.customer
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-9     jiuqi      
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.customer
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-9     jiuqi      
 * ============================================================*/

package com.spark.customer;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderTaskFather;

/**
 * <p>已用信用额度</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */

 final class AlreadyUseCreditTask extends OrderTaskFather<AlreadyUseCreditTask.Method>{
	private AlreadyUseCredit entity;//仅仅在创建时使用
	/**
	 * <p>已用信用额度枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 
	 * @version 2012-3-9
	 */

	public enum Method {
		ADD, SUB, Create
	}
	/**
	 * 客户id
	 */
	private GUID customerId;
	/**
	 * 变化金额
	 */
	private double changeAmount;
	public AlreadyUseCreditTask(AlreadyUseCredit entity) {
		super();
		this.entity = entity;
	}
	public AlreadyUseCredit getEntity() {
		return entity;
	}
	public GUID getCustomerId() {
		return customerId;
	}
	public double getChangeAmount() {
		return changeAmount;
	}
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}
	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}
	public AlreadyUseCreditTask(GUID customerId, double changeAmount) {
		super();
		this.customerId = customerId;
		this.changeAmount = changeAmount;
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
