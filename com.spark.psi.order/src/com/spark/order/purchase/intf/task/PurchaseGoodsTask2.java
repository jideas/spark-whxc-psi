/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.purchase.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-1     modi 
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.purchase.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-1     modi 
 * ============================================================*/

package com.spark.order.purchase.intf.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */

public class PurchaseGoodsTask2 extends Task<PurchaseGoodsTask2.Method> {

	public com.spark.order.purchase.intf.PurchaseGoods2 entity;
	public GUID recid;

	/**
	 * @param recid
	 */
	public PurchaseGoodsTask2(GUID recid) {
		super();
		this.recid = recid;
	}
	

	/**
	 * 
	 */
	public PurchaseGoodsTask2() {
		super();
	}


	/**
	 * <p>TODO 类描述</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-1
	 */

	public enum Method {
		ADD, MODIFY, DELETE

	}

}
