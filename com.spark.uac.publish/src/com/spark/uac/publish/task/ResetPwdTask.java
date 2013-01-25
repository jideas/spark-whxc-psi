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
import com.spark.uac.publish.ProductSerialsEnum;

/**
 * <p>重置密码</p>
 *


 *
 
 * @version 2012-4-11
 */
public class ResetPwdTask extends SimpleTask{
	
	public enum ErrType{
		MobileNo,
		CompanyName,
		Name
	}
	
	private ProductSerialsEnum productSerialsEnum;
	
	private ErrType errType;
	
	/**
	 * 企业名称
	 */
	private String tenantName;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 手机号码
	 */
	private String mobileNumber;
	
	/**
	 * 返回激活码(可不返回)
	 */
	private String code;
	
	/**
	 * 错误消息
	 */
	private String msg;
	
	/**
	 * 操作是否成功
	 */
	private boolean succeed;
	
	public ResetPwdTask(String tenantName,String userName,String mobileNumber){
	    this.tenantName = tenantName;
	    this.userName = userName;
	    this.mobileNumber = mobileNumber;
    }
	
	public ResetPwdTask(String tenantName,String userName,String mobileNumber,ProductSerialsEnum productSerialsEnum){
	    this.tenantName = tenantName;
	    this.userName = userName;
	    this.mobileNumber = mobileNumber;
	    this.productSerialsEnum = productSerialsEnum;
    }

	public String getCode(){
    	return code;
    }

	public void setCode(String code){
    	this.code = code;
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

	public String getTenantName(){
    	return tenantName;
    }

	public String getUserName(){
    	return userName;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }

	public ErrType getErrType(){
    	return errType;
    }

	public void setErrType(ErrType errType){
    	this.errType = errType;
    }

	public ProductSerialsEnum getProductSerialsEnum(){
    	return productSerialsEnum;
    }

	
}
