package com.spark.order.promotion.intf;

import java.util.HashMap;
import java.util.Map;

import com.spark.order.util.StringSearchUtil;
import com.spark.psi.publish.PromotionStatus;

/**
 * <p>�������ڲ�ʹ�þ�̬������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
