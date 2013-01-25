package com.spark.order.promotion.service;

import com.spark.psi.publish.PromotionAction;

/**
 * <p>促销单可操作按钮</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */
public interface IPromotionButton {
	/**
	 * 获得可操作图标数组
	 * @return PromotionAction[]
	 */
	PromotionAction[] getActions();
}
