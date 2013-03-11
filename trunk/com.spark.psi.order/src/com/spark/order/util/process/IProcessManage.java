package com.spark.order.util.process;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.StatusEnum;

/**
 * <p>���̹���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public interface IProcessManage {
	/**
	 * ��һ�����̣�����һ�����̷���null
	 * @param info
	 * @return StatusEnum
	 */
	StatusEnum next(GUID orderId);
	/**
	 * ��һ�����̣�����һ�����̷���null
	 * @param info
	 * @return StatusEnum
	 */
	StatusEnum prov(GUID orderId);
	/**
	 * ��ö����仯��Ĳ��ţ�����ޱ仯����null
	 * @return GUID
	 */
	GUID getOrderDepartment(); 
	/**
	 * �ⲿ���ж��������붩�����������²�ѯ
	 * @param order void
	 */
	void setOrderInfo(OrderInfo order);
}
