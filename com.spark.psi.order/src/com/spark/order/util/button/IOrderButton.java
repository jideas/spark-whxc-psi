package com.spark.order.util.button;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;

/**
 * <p>获取订单操作图标接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public interface IOrderButton {
	/**
	 * <p>获取操作图标对象id错误或者该对象不存在</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */
	@SuppressWarnings("serial")
	class OrderButtonParamError extends Throwable{
		public OrderButtonParamError(){
			super("获取操作图标对象id错误或者该对象不存在");
		}
	}
	/**
	 * 获得当前单据可操作图标集合
	 * @return OrderAction[]
	 */
	OrderAction[] getOrderAction(GUID id) throws OrderButtonParamError;
	/**
	 * 界面控制成当前传入的状态数组
	 * @param orderAction void
	 */
	void setOrderStatuss(OrderStatus... statuss);
}
