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

package com.spark.order.promotion.service;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>促销删除历史</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */

class PromotionDelTask2 extends Task<PromotionDelTask2.Method> {

	public com.spark.order.promotion.intf.Promotion2 entity;
	public GUID recid;

	/**
	 * <p>促销删除历史</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */

	public enum Method {
		ADD,
//		MODIFY, DELETE

	}

}
