package com.spark.order.util.process;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.psi.publish.OrderAction;

/**
 * <p>前台操作处理接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-14
 */
public interface IOrderAction {
	/**
	 * 执行操作 
	 * @param orderAction
	 * @return boolean 失败返回false
	 */
	boolean action(final GUID orderId, final OrderAction orderAction) throws Throwable;
	/**
	 * 执行操作 
	 * @param orderAction
	 * @return boolean 失败返回false
	 */
	boolean action(final GUID orderId, final OrderAction orderAction, boolean ignoredWarning) throws Throwable;
	/**
	 * 外部已有订单，放入订单，不在重新查询
	 * @param order void
	 */
	void setOrderInfo(OrderInfo order);
	/**
	 * 传入操作时的相关原因字符串 
	 * @param cause void
	 */
	void setCause(String cause);
	/**
	 * 设置预计出库日期（销售订单某种情况下特用）
	 * @param l void
	 */
	void setPlanOutDate(final Long l);
	/**
	 * 获得验证结果
	 * @return IServiceCheck
	 */
	IServiceCheck[] getServiceCheck();
	/**
	 * 获得订单更新后状态
	 * @return StatusEnum
	 */
	StatusEnum getNewstatus();
}
