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
import com.spark.psi.publish.Auth;

/**
 * <p>增加部门的职能</p>
 *


 *
 
 * @version 2012-4-6
 */
@Deprecated
public class AddDepartmentAuthTask extends SimpleTask{
	
	private GUID deptId;
	
	private Auth[] auths;
	
	public AddDepartmentAuthTask(GUID deptId,Auth... auths){
		this.deptId = deptId;
		this.auths = auths;
	}

	public GUID getDeptId(){
    	return deptId;
    }

	public Auth[] getAuths(){
    	return auths;
    }
	
	
	
}
