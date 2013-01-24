/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.pri
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-5    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.pri
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-5    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.pri;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>清空级次表</p>
 *


 *
 
 * @version 2012-4-5
 */

public class DeleteLevelTreeTask extends SimpleTask{
		
	private GUID tenantId;
	
	/**
	 * 清空指定租户的
	 * @param tenantId
	 */
	public DeleteLevelTreeTask(GUID tenantId){
	    this.tenantId = tenantId;
    }
	
	/**
	 * 清空全部
	 */
	public DeleteLevelTreeTask(){
		
	}

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
}
