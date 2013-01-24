/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>更新租户配置信息资源</p>
 *


 *
 
 * @version 2012-6-8
 */

public class UpdateTenantResourceTask extends Task<UpdateTenantResourceTask.Method>{
	public enum Method{
		Put,
		Modify
	}
	
	private GUID id;
	
	public UpdateTenantResourceTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

	
	
}
