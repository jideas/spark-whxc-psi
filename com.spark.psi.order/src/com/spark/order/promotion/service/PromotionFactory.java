package com.spark.order.promotion.service;

import com.jiuqi.dna.core.Context;
import com.spark.order.promotion.intf.Promotion2;

/**
 * <p>促销单工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */
public final class PromotionFactory {
	private PromotionFactory(){
		
	}
	
	/**
	 * 获得促销操作图标工具类
	 * @param context
	 * @param promotion
	 * @return IPromotionButton
	 */
	public static IPromotionButton getPromotionButton(Context context, Promotion2 promotion){
		return new PromotionButtonImpl(context, promotion);
	}
}
