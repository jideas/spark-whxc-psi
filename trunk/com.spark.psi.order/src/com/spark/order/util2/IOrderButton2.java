package com.spark.order.util2;

import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.facade.OrderException;

/**
 * <p>根据订单信息获取当前操作Action</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-3
 */
public interface IOrderButton2<ActionEnum> {
	/**
	 * <p>获取操作图标对象错误或者该对象不存在</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */
	@SuppressWarnings("serial")
	static class OrderButtonParamError extends OrderException{
		public OrderButtonParamError(){
			super("获取操作图标对象错误或者该对象不存在");
		}
	}
	/**
	 * 获得当前单据可操作图标集合
	 * @return OrderAction[]
	 */
	ActionEnum[] getOrderAction(final EntityFather entity) throws OrderButtonParamError;
}
