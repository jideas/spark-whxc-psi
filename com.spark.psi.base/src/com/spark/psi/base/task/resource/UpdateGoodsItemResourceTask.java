/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>商品条目资源维护Task</p>
 *


 *
 
 * @version 2012-3-27
 */

public class UpdateGoodsItemResourceTask extends Task<UpdateGoodsItemResourceTask.Method>{
	
	public enum Method{
		Put,
		Modify,
		Remove
	}
	
	public GUID id;

	public UpdateGoodsItemResourceTask(final GUID id){
		this.id = id;
	}
	
}
