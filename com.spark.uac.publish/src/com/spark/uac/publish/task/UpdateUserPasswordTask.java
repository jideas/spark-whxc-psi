/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改用户密码</p>
 *


 *
 
 * @version 2012-4-11
 */

public class UpdateUserPasswordTask extends SimpleTask{

	private GUID userId;

	private String newPwd,oldPwd;
	
	public UpdateUserPasswordTask(GUID userId, String newPwd,String oldPwd){
		this.userId = userId;
		this.newPwd = newPwd;
		this.oldPwd = oldPwd;
	}

	public GUID getUserId(){
    	return userId;
    }

	public String getNewPwd(){
    	return newPwd;
    }

	public String getOldPwd(){
    	return oldPwd;
    }

	
	
}
