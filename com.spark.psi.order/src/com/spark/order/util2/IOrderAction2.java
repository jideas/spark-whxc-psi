package com.spark.order.util2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.facade.OrderException;

/**
 * ǰ̨��������ӿڣ������ڲ���������װ���ڣ������¼����ͣ���
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-14
 */
public interface IOrderAction2<ActionEnum, status> {
	@SuppressWarnings("serial")
	static class OrderActionLoseException extends OrderException {
		public OrderActionLoseException(String str) {
			super(str);
		}
	}

	/**
	 * Ԥ�Ƴ�������δ�����쳣
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * Company: ����
	 * </p>
	 * 
	 * @author modi
	 * @version 2012-5-7
	 */
	@SuppressWarnings("serial")
	static class OrderActionPlanOutDateException extends
			OrderActionLoseException {

		public OrderActionPlanOutDateException() {
			super("Ԥ�Ƴ������ڴ���");
		}

		public OrderActionPlanOutDateException(String str) {
			super(str);
		}

	}

	/**
	 * �޿��òֿ��쳣
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * Company: ����
	 * </p>
	 * 
	 * @author modi
	 * @version 2012-5-7
	 */
	@SuppressWarnings("serial")
	static class OrderActionNotHaveStoreException extends
			OrderActionLoseException {

		public OrderActionNotHaveStoreException() {
			super("û�п��òֿ�");
		}

	}
	
	/**
	 * ������Ʒ�޸������������쳣
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-5-9
	 */
	@SuppressWarnings("serial")
	static class OrderActionPromotionCountException extends OrderActionLoseException{

		public OrderActionPromotionCountException(String str) {
			super(str);
		}
		
		public OrderActionPromotionCountException() {
			super("������Ʒ�޸������������쳣");
		}
		
	}

	/**
	 * ִ�в���
	 * 
	 * @param orderAction
	 * @return boolean ʧ�ܷ���false
	 */
	boolean action(final GUID id, final ActionEnum orderAction)
			throws OrderActionLoseException, OrderActionPlanOutDateException,
			OrderActionNotHaveStoreException, OrderActionPromotionCountException;

	/**
	 * 
	 * @param id
	 * @param orderAction
	 * @param ignoredWarning
	 *            true���Ծ���
	 * @return
	 * @throws Throwable boolean ʧ�ܷ���false
	 */
	boolean action(final GUID id, final ActionEnum orderAction,
			boolean ignoredWarning) throws OrderActionLoseException,
			OrderActionPlanOutDateException, OrderActionNotHaveStoreException,
			OrderActionPromotionCountException;

	/**
	 * �ⲿ���ж��������붩�����������²�ѯ
	 * 
	 * @param order
	 *            void
	 */
	void setEntity(EntityFather entity);

	/**
	 * �������ʱ�����ԭ���ַ���
	 * 
	 * @param cause
	 *            void
	 */
	void setCause(String cause);

	/**
	 * ����Ԥ�Ƴ������ڣ����۶���ĳ����������ã�
	 * 
	 * @param l
	 *            void
	 */
	void setPlanOutDate(final Long l);

	/**
	 * ��ö������º�״̬
	 * 
	 * @return StatusEnum
	 */
	status getNewstatus();
}
