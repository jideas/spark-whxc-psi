package com.spark.order.util.process;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.type.StatusEnum;

/**
 * <p>流程管理</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public interface IProcessManage {
	/**
	 * 下一步流程，无下一步流程返回null
	 * @param info
	 * @return StatusEnum
	 */
	StatusEnum next(GUID orderId);
	/**
	 * 上一步流程，无上一步流程返回null
	 * @param info
	 * @return StatusEnum
	 */
	StatusEnum prov(GUID orderId);
	/**
	 * 获得订单变化后的部门，如果无变化返回null
	 * @return GUID
	 */
	GUID getOrderDepartment(); 
	/**
	 * 外部已有订单，放入订单，不在重新查询
	 * @param order void
	 */
	void setOrderInfo(OrderInfo order);
}
