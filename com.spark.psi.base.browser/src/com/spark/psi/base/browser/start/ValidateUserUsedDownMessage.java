package com.spark.psi.base.browser.start;

import com.spark.portal.browser.ResponseHandler;

/**
 * <p>用户已经被使用消息</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-4
 */

public class ValidateUserUsedDownMessage{
	/**用户ID*/
	private String userId;

	/**回调*/
	private ResponseHandler responseHandler;

	/** 
	 *构造方法
	 *@param userId
	 */
	public ValidateUserUsedDownMessage(String userId){
		super();
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public ResponseHandler getResponseHandler(){
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler responseHandler){
		this.responseHandler = responseHandler;
	}
}
