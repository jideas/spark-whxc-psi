/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.publish.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-19    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.publish.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-19    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改手机号码</p>
 *


 *
 
 * @version 2012-4-19
 */

public class ChangeUserMobileNoTask extends SimpleTask{
	
	private GUID userId;
	
	private String mobileNumber;
	
	public ChangeUserMobileNoTask(GUID userId,String mobileNumber){
	    this.userId = userId;
	    this.mobileNumber = mobileNumber;
    }

	public GUID getUserId(){
    	return userId;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }
	
	
}
