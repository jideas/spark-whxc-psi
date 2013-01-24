/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>维护仓库资源</p>
 *


 *
 
 * @version 2012-3-31
 */

public class UpdateStoreResourceTask extends Task<UpdateStoreResourceTask.Method>{
	
	public enum Method{
		Put,
		Modify,
		Remove
	}
	
	public GUID id;
	
	public UpdateStoreResourceTask(GUID id){
		this.id = id;
	}
	

}
