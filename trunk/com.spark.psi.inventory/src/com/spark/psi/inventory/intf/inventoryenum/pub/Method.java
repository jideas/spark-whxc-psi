/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.inventoryenum
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum.pub;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */

public enum Method {
	/** 新增 */
	INSERT,
	/** 删除 */
	DELETE,
	/** 修改  **/
	MODIFY,
	/**
	 * 修改状态
	 */
	MODIFYstatus, 
	/**
	 * 增加
	 */
	ADD,
	/** 减少 */
	REDUCE,
}
