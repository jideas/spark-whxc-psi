package com.spark.psi.account.intf.entity.dealing;


/**
 * 客户供应商实收实付金额
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-14
 */
public class PartnerDealingRealAmount {
	private double orderAmount;
	private double returnAmount;
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(double returnAmount) {
		if(0 > returnAmount){
			this.returnAmount = -returnAmount;
		}
		else{
			this.returnAmount = returnAmount;
		}
	}
}
