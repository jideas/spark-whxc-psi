/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-6    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-6    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>清空所有部门的职能</p>
 *


 *
 
 * @version 2012-4-6
 */

public class RemoveAllDepartmentAuthTask extends SimpleTask{
		
	private GUID tenantId;
	
	public RemoveAllDepartmentAuthTask(GUID tenantId){
	    this.tenantId = tenantId;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
		
}
