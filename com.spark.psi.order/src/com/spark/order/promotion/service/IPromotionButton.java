package com.spark.order.promotion.service;

import com.spark.psi.publish.PromotionAction;

/**
 * <p>�������ɲ�����ť</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */
public interface IPromotionButton {
	/**
	 * ��ÿɲ���ͼ������
	 * @return PromotionAction[]
	 */
	PromotionAction[] getActions();
}
