package com.spark.uac.publish.task;
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


import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>创建登录用户</p>
 *


 *
 
 * @version 2012-4-11
 */

public class CreateUserTask extends SimpleTask{
	
	private String mobile;
	
	private GUID tenantId;
	
	private GUID userId;
	/**
	 * 是否有效
	 * 如果是配置向导创建用户，则用户有效性为false，需要等到启用系统时统一修改用户的有效性为true
	 */
	private boolean valid = true;
	
	public CreateUserTask(GUID tenantId,GUID userId,String mobile){
		this.mobile = mobile;
		this.tenantId = tenantId;
		this.userId = userId;
	}
	
	public CreateUserTask(GUID tenantId,GUID userId,String mobile,boolean valid){
		this.mobile = mobile;
		this.tenantId = tenantId;
		this.userId = userId;
		this.valid = valid;
	}
	
	
	

	public boolean isValid() {
		return valid;
	}

	public String getMobile(){
    	return mobile;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public GUID getUserId(){
    	return userId;
    }
	
	
	
	
	
}
