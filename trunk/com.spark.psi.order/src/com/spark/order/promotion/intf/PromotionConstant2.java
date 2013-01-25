package com.spark.order.promotion.intf;

import java.util.HashMap;
import java.util.Map;

import com.spark.order.util.StringSearchUtil;
import com.spark.psi.publish.PromotionStatus;

/**
 * <p>促销单内部使用静态工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
final class PromotionConstant2 {
	private PromotionConstant2(){
		
	}
	public static Map<String, PromotionStatus2> statusMap = new HashMap<String, PromotionStatus2>();
	public static StringSearchUtil StatusSearchUtil = new StringSearchUtil();
	public static Map<PromotionStatus, PromotionStatus2> statusPubToMe = new HashMap<PromotionStatus, PromotionStatus2>();
}
