package com.spark.order.util.button;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;

/**
 * <p>��ȡ��������ͼ��ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public interface IOrderButton {
	/**
	 * <p>��ȡ����ͼ�����id������߸ö��󲻴���</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */
	@SuppressWarnings("serial")
	class OrderButtonParamError extends Throwable{
		public OrderButtonParamError(){
			super("��ȡ����ͼ�����id������߸ö��󲻴���");
		}
	}
	/**
	 * ��õ�ǰ���ݿɲ���ͼ�꼯��
	 * @return OrderAction[]
	 */
	OrderAction[] getOrderAction(GUID id) throws OrderButtonParamError;
	/**
	 * ������Ƴɵ�ǰ�����״̬����
	 * @param orderAction void
	 */
	void setOrderStatuss(OrderStatus... statuss);
}
