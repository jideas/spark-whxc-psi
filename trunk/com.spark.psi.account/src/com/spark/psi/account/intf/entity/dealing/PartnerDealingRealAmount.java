package com.spark.psi.account.intf.entity.dealing;


/**
 * �ͻ���Ӧ��ʵ��ʵ�����
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
