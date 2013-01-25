/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.promotion.intf
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-30     modi 
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.promotion.intf
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-30     modi 
 * ============================================================*/

package com.spark.order.promotion.intf;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */

public class PromotionTask2 extends Task<PromotionTask2.Method> implements ITaskResult{

	public com.spark.order.promotion.intf.Promotion2 entity;
	public GUID recid;

	/**
	 * <p>TODO 类描述</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */

	public enum Method {
		ADD, MODIFY, DELETE,
	}

	public boolean isSucceed() {
		return lenght == 1;
	}
	public int lenght;
	public int lenght() {
		return lenght;
	}

}
