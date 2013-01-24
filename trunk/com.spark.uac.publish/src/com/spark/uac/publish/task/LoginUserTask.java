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

import java.net.URL;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.entity.UserInfo;

/**
 * <p>登录系统</p>
 *


 *
 
 * @version 2012-4-11
 */

public class LoginUserTask extends SimpleTask{
	
	public enum ErrType{
		TenantName,
		MobileNo,
		Password,
		Activition
	}
	
	private ProductSerialsEnum productSerialsEnum;
	
	private ErrType errType;

	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 密码
	 */
	private String pwd;
		
	/**
	 * 错误消息
	 */
	private String msg;
	
	/**
	 * 是否成功
	 */
	private boolean succeed;
	
	private UserInfo[] user;
	
	private URL url;
	
	public LoginUserTask(
			String mobile,
			String pwd
		){
		this.mobile = mobile;
		this.pwd = pwd;
	}
	
	public LoginUserTask(			
			String mobile,
			String pwd,
			ProductSerialsEnum productSerialsEnum
	){
		this.mobile = mobile;
		this.pwd = pwd;
		this.productSerialsEnum = productSerialsEnum;
	}

	public ProductSerialsEnum getProductSerialsEnum(){
    	return productSerialsEnum;
    }

	public String getMobile(){
    	return mobile;
    }


	public String getPwd(){
    	return pwd;
    }



	public String getMsg(){
    	return msg;
    }


	public void setMsg(String msg){
    	this.msg = msg;
    }


	public boolean isSucceed(){
    	return succeed;
    }


	public void setSucceed(boolean succeed){
    	this.succeed = succeed;
    }

	public UserInfo[] getUser(){
    	return user;
    }


	public void setUser(UserInfo... user){
    	this.user = user;
    }


	public ErrType getErrType(){
    	return errType;
    }


	public void setErrType(ErrType errType){
    	this.errType = errType;
    }


	public URL getUrl(){
    	return url;
    }


	public void setUrl(URL url){
    	this.url = url;
    }
	
	
	
}	
