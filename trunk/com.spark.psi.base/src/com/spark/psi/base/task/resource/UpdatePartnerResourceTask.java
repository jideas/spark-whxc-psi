/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-28    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-28    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>维护客户供应商资源</p>
 *


 *
 
 * @version 2012-3-28
 */

public class UpdatePartnerResourceTask extends Task<UpdatePartnerResourceTask.Method>{
	
	public enum Method{
		Put,
		Modify,
		Remove
	}

	
	public GUID id;
	
	public UpdatePartnerResourceTask(GUID id){
		this.id = id;
	}

	
}
