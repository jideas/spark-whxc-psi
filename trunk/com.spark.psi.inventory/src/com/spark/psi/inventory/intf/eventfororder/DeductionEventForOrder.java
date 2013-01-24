/**
 * 
 */
package com.spark.psi.inventory.intf.eventfororder;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * 订单专用往来变化+抵减事件，用于订单维护信用额度值
 *
 */
public class DeductionEventForOrder extends Event {

	/**
	 * 往来条目id
	 */
	private GUID dealingItemId;
	
	/**
	 * 对应客户供应商id
	 */
	private PartnerType partnerType;
	
	/**
	 * 是否产生递减
	 */
	private boolean deductioned;
	/**
	 * 抵减出库单金额
	 */
	private double outAmount;
	/**
	 * 抵减入库单金额
	 */
	private double inAmount;
	

	/**
	 * 
	 * @param dealingItemId 往来明细ID
	 * @param partnerType 客户OR供应商
	 * @param deductioned 是否抵减
	 * @param outAmount 抵减出库单金额
	 * @param inAmount 抵减入库单金额
	 */
	public DeductionEventForOrder(GUID dealingItemId, PartnerType partnerType,
			boolean deductioned, double outAmount,double inAmount) {
		super();
		this.dealingItemId = dealingItemId;
		this.partnerType = partnerType;
		this.deductioned = deductioned;
		this.outAmount = outAmount;
		this.inAmount = inAmount;
	}
	
	public GUID getDealingItemId() {
		return dealingItemId;
	}
	
	public PartnerType getPartnerType() {
		return partnerType;
	}
	
	public boolean isDeductioned() {
		return deductioned;
	}
	public double getOutAmount() {
		return outAmount;
	}

	public double getInAmount() {
		return inAmount;
	}
	
}
