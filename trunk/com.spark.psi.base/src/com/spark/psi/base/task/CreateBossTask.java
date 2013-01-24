/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.organization.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.organization.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>通过租户配置文件创建员工</p>
 *


 *
 
 * @version 2012-3-27
 */

public class CreateBossTask extends SimpleTask{

	/**
	 * 租户ID
	 */
	private GUID tenantId;
	
	private String config;
	
	public CreateBossTask(GUID tenantId,String config){
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
