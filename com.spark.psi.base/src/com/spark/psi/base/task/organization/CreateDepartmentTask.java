/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.organization
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-6-11    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.organization
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-6-11    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.organization;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>创建租户部门</p>
 *  部门guid = GUID.MD5OF(租户名词+部门名词）
 *


 *
 
 * @version 2012-6-11
 */

public class CreateDepartmentTask extends SimpleTask{
	/**
	 * 租户ID
	 */
	private GUID tenantId;
	
	private String config;
	
	public CreateDepartmentTask(GUID tenantId,String config){
	    this.tenantId = tenantId;
	    this.config = config;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public String getConfig(){
    	return config;
    }
	

}
