package com.spark.order.util2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.facade.OrderException;

/**
 * 前台操作处理接口（所有内部交互，封装在内（包含事件发送））
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
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
	 * 预计出库日期未填入异常
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author modi
	 * @version 2012-5-7
	 */
	@SuppressWarnings("serial")
	static class OrderActionPlanOutDateException extends
			OrderActionLoseException {

		public OrderActionPlanOutDateException() {
			super("预计出库日期错误！");
		}

		public OrderActionPlanOutDateException(String str) {
			super(str);
		}

	}

	/**
	 * 无可用仓库异常
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author modi
	 * @version 2012-5-7
	 */
	@SuppressWarnings("serial")
	static class OrderActionNotHaveStoreException extends
			OrderActionLoseException {

		public OrderActionNotHaveStoreException() {
			super("没有可用仓库");
		}

	}
	
	/**
	 * 促销商品修改已销售数量异常
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
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
			super("促销商品修改已销售数量异常");
		}
		
	}

	/**
	 * 执行操作
	 * 
	 * @param orderAction
	 * @return boolean 失败返回false
	 */
	boolean action(final GUID id, final ActionEnum orderAction)
			throws OrderActionLoseException, OrderActionPlanOutDateException,
			OrderActionNotHaveStoreException, OrderActionPromotionCountException;

	/**
	 * 
	 * @param id
	 * @param orderAction
	 * @param ignoredWarning
	 *            true忽略警告
	 * @return
	 * @throws Throwable boolean 失败返回false
	 */
	boolean action(final GUID id, final ActionEnum orderAction,
			boolean ignoredWarning) throws OrderActionLoseException,
			OrderActionPlanOutDateException, OrderActionNotHaveStoreException,
			OrderActionPromotionCountException;

	/**
	 * 外部已有订单，放入订单，不在重新查询
	 * 
	 * @param order
	 *            void
	 */
	void setEntity(EntityFather entity);

	/**
	 * 传入操作时的相关原因字符串
	 * 
	 * @param cause
	 *            void
	 */
	void setCause(String cause);

	/**
	 * 设置预计出库日期（销售订单某种情况下特用）
	 * 
	 * @param l
	 *            void
	 */
	void setPlanOutDate(final Long l);

	/**
	 * 获得订单更新后状态
	 * 
	 * @return StatusEnum
	 */
	status getNewstatus();
}
