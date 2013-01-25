package com.spark.order.intf.type;

/**
 * <p>订单操作枚举</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-10-13
 */
public enum TaskEnum {
	/**
	 * 添加
	 */
	ADD,
	/**
	 * 修改
	 */
	MODIFY,
	/**
	 * 删除
	 */
	DELETE,
	/**
	 * 修改状态
	 */
	UPDATE,
	/**
	 * 根据主表GUID删除明细
	 */
	DELETE_LORD,
}
