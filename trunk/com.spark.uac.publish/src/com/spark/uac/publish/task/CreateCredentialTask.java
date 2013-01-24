/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.publish.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-29    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.publish.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-29    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * <p>生成一个验证码密钥</p>
 *


 *
 
 * @version 2012-5-29
 */

public class CreateCredentialTask extends SimpleTask{
	
	private String name;
	
	private String credential;
	
	public CreateCredentialTask(String name){
	    this.name = name;
    }

	public String getName(){
    	return name;
    }

	public String getCredential(){
    	return credential;
    }

	public void setCredential(String credential){
    	this.credential = credential;
    }
	
	
	
	
}
