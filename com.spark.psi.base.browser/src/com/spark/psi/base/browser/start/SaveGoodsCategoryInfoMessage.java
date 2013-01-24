package com.spark.psi.base.browser.start;

import com.spark.portal.browser.ResponseHandler;

/**
 * <p>保存商品分类信息</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-11
 */

public class SaveGoodsCategoryInfoMessage{

	/**回调*/
	private ResponseHandler responseHandler;

	public ResponseHandler getResponseHandler(){
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler responseHandler){
		this.responseHandler = responseHandler;
	}
}
