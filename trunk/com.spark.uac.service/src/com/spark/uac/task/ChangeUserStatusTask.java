/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.UserStatus;

/**
 * <p>修改用户状态</p>
 *	未激活状态	
 *	待激活状态
 *	已激活状态


 *
 
 * @version 2012-4-12
 */

public class ChangeUserStatusTask extends SimpleTask{
	
	private UserStatus userstatus;
	
	private GUID userId;
	
	public ChangeUserStatusTask(GUID userId,UserStatus userstatus){
	    this.userId = userId;
	    this.userstatus = userstatus;
    }

	public UserStatus getUserStatus(){
    	return userstatus;
    }

	public GUID getUserId(){
    	return userId;
    }
	
	
	
}
