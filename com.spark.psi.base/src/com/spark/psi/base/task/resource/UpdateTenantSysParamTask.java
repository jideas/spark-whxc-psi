/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.base.internal.entity.TenantSysParam;

/**
 * <p>更新租户系统配置</p>
 *


 *
 
 * @version 2012-4-11
 */

public class UpdateTenantSysParamTask extends SimpleTask{
		
	private TenantSysParam sysParam;
	
	public UpdateTenantSysParamTask(TenantSysParam sysParam){
		this.sysParam = sysParam;
	}

	public TenantSysParam getSysParam(){
    	return sysParam;
    }
	
}
