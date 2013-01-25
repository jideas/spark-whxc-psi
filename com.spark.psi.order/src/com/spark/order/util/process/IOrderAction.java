package com.spark.order.util.process;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.psi.publish.OrderAction;

/**
 * <p>ǰ̨��������ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-14
 */
public interface IOrderAction {
	/**
	 * ִ�в��� 
	 * @param orderAction
	 * @return boolean ʧ�ܷ���false
	 */
	boolean action(final GUID orderId, final OrderAction orderAction) throws Throwable;
	/**
	 * ִ�в��� 
	 * @param orderAction
	 * @return boolean ʧ�ܷ���false
	 */
	boolean action(final GUID orderId, final OrderAction orderAction, boolean ignoredWarning) throws Throwable;
	/**
	 * �ⲿ���ж��������붩�����������²�ѯ
	 * @param order void
	 */
	void setOrderInfo(OrderInfo order);
	/**
	 * �������ʱ�����ԭ���ַ��� 
	 * @param cause void
	 */
	void setCause(String cause);
	/**
	 * ����Ԥ�Ƴ������ڣ����۶���ĳ����������ã�
	 * @param l void
	 */
	void setPlanOutDate(final Long l);
	/**
	 * �����֤���
	 * @return IServiceCheck
	 */
	IServiceCheck[] getServiceCheck();
	/**
	 * ��ö������º�״̬
	 * @return StatusEnum
	 */
	StatusEnum getNewstatus();
}
