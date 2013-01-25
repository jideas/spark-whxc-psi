package com.spark.order.util2;

import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.facade.OrderException;

/**
 * <p>���ݶ�����Ϣ��ȡ��ǰ����Action</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-3
 */
public interface IOrderButton2<ActionEnum> {
	/**
	 * <p>��ȡ����ͼ����������߸ö��󲻴���</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */
	@SuppressWarnings("serial")
	static class OrderButtonParamError extends OrderException{
		public OrderButtonParamError(){
			super("��ȡ����ͼ����������߸ö��󲻴���");
		}
	}
	/**
	 * ��õ�ǰ���ݿɲ���ͼ�꼯��
	 * @return OrderAction[]
	 */
	ActionEnum[] getOrderAction(final EntityFather entity) throws OrderButtonParamError;
}
