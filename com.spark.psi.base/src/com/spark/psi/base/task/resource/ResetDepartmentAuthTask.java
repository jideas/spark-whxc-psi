/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>重置部门职能</p>
 *


 *
 
 * @version 2012-4-18
 */

public class ResetDepartmentAuthTask extends SimpleTask{
	
	/**
	 * 是否从资源里面取用户
	 */
	private boolean findByResource = true;
	
	private GUID tenantId;
	
	public ResetDepartmentAuthTask(boolean findByResource){
	    this.findByResource = findByResource;
    }
	
	public ResetDepartmentAuthTask(GUID tenantId){
	    this.tenantId = tenantId;
    }

	public boolean isFindByResource(){
    	return findByResource;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
	
}
