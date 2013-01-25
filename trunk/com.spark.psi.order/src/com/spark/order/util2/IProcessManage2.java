package com.spark.order.util2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.EntityFather;

/**
 * <p>流程管理</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public interface IProcessManage2<status> {
	/**
	 * 下一步流程，无下一步流程返回null
	 * @param status订单当前状态
	 * @return status
	 */
	status next(status status);
	/**
	 * 上一步流程，无上一步流程返回null
	 * @param status订单当前状态
	 * @return status
	 */
	status prov(status status);
	/**
	 * 获得订单变化后的部门，如果无变化返回null
	 * @return GUID
	 */
	GUID getOrderDepartment();
	/**
	 * 获得订单变化后的审核部门，如果无变化返回null
	 * @return GUID
	 */
	GUID getOrderExamDept();
	/**
	 * 外部已有订单，放入订单，不在重新查询
	 * @param order void
	 */
	void setEntity(EntityFather entity);
}
