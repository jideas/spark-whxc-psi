/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.sales.intf
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.sales.intf
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderTaskFather;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */

public class SalesOrderTask2 extends OrderTaskFather<SalesOrderTask2.Method> {

	public com.spark.order.sales2.SalesOrderInfo2 entity;
	public GUID recid;

	/**
	 * <p>TODO 类描述</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-5
	 */

	public enum Method {
		ADD, MODIFY, DELETE
	}

	@Override
	protected void setLenght(int lenght) {
		this.lenght = lenght;
	}

	@Override
	protected void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

}
