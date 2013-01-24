/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.config.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-13    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.config.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-13    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>租户启用</p>
 *


 *
 
 * @version 2012-3-13
 */

public class TenantServiceStartTask extends SimpleTask{
	
	private GUID tenantId;
	
	public TenantServiceStartTask(GUID tenantId){
		this.tenantId = tenantId;
	}

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
}
