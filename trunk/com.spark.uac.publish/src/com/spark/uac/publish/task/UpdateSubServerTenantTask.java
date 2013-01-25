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

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>编辑业务服务器租户信息</p>
 *


 *
 
 * @version 2012-6-8
 */

public class UpdateSubServerTenantTask extends SimpleTask{
	
	private GUID id;
	private String config;
	
	public UpdateSubServerTenantTask(GUID id,String config){
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
