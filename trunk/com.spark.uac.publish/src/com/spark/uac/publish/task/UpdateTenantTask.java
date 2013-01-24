/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.publish.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.publish.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>编辑租户信息</p>
 *


 *
 
 * @version 2012-6-8
 */

public class UpdateTenantTask extends Task<UpdateTenantTask.Method>{
	
	public enum Method {
		Create,
		Update
	}
	
	private GUID id;
	private String config;
	
	public UpdateTenantTask(GUID id,String config){
		this.id = id;
		this.config = config;
    }

	public GUID getId(){
    	return id;
    }

	public String getConfig(){
    	return config;
    }
	
	
}
