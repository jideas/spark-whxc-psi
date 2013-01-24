/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.config.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-20    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.config.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-20    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改用户个人设置</p>
 *


 *
 
 * @version 2012-4-20
 */

public class UpdateUserConfigTask extends SimpleTask{
	
	private GUID id;
	
	private GUID logo;
	
	private String landLineNumber;
	
	private String mail;
	
	private String pwd;
	
	private String oldPwd;

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID getLogo(){
    	return logo;
    }

	public void setLogo(GUID logo){
    	this.logo = logo;
    }

	public String getLandLineNumber(){
    	return landLineNumber;
    }

	public void setLandLineNumber(String landLineNumber){
    	this.landLineNumber = landLineNumber;
    }

	public String getMail(){
    	return mail;
    }

	public void setMail(String mail){
    	this.mail = mail;
    }

	public String getPwd(){
    	return pwd;
    }

	public void setPwd(String pwd){
    	this.pwd = pwd;
    }

	public String getOldPwd(){
    	return oldPwd;
    }

	public void setOldPwd(String oldPwd){
    	this.oldPwd = oldPwd;
    }
	
	
}
