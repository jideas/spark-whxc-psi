package com.spark.psi.base.browser.start;

import com.spark.portal.browser.ResponseHandler;

/**
 * <p>�û��Ѿ���ʹ����Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-4
 */

public class ValidateUserUsedDownMessage{
	/**�û�ID*/
	private String userId;

	/**�ص�*/
	private ResponseHandler responseHandler;

	/** 
	 *���췽��
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
