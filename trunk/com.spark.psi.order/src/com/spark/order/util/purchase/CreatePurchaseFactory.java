package com.spark.order.util.purchase;

import com.jiuqi.dna.core.Context;

/**
 * <p>采购订单创建工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-23
 */
public final class CreatePurchaseFactory {
	private CreatePurchaseFactory(){
		
	}
	public enum CreateEnum{
		/**
		 * 普通订单(包括直供)
		 */
		Plan,
//		/**
//		 * 直供订单
//		 */
//		Direct,
		/**
		 * 零星采购
		 */
		Odd_Lot
	}
	/**
	 * 获得采购创建实例化对象
	 * @return ICreatePurchase
	 */
	public static ICreatePurchase getCreatePurchase(Context context, CreateEnum createEnum){
		switch (createEnum) {
		case Plan:
			return new PlanPurchaseImpl(context);
		case Odd_Lot:
			return new OddLotPurchaseImpl(context);
		default:
			return null;
		}
	}
}
