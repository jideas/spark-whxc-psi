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
 * <p>TODO 类描述</p>
 *


 *
 
 * @version 2012-3-27
 */

public class UpdateEmployeeResourceTask extends Task<UpdateEmployeeResourceTask.Method>{
	
	public enum Method{
		Put,
		Modify,
		Remove
	}
	
	public GUID id;
	
	public UpdateEmployeeResourceTask(GUID id){
		this.id = id;
	}
	
}
