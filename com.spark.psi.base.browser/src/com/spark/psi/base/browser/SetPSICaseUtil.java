package com.spark.psi.base.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;

/**
 * 设置退货或终止原因Util
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-14
 */
public abstract class SetPSICaseUtil {
	public static final String StopCause = "中止原因";
	public static final String ReturnCause = "退回原因";
	/**
	 * 设置退货或终止原因
	 * @param context,上下文
	 * @param title,请使用常量SetReturnOrStopCauseUtil.StopCause/ReturnCause
	 */
	public SetPSICaseUtil(Situation context, String title){
		initPage(context, title, false);
	}
	
	/**
	 * 设置退货或终止原因
	 * @param context,上下文
	 * @param title,请使用常量SetReturnOrStopCauseUtil.StopCause/ReturnCause
	 * @param isCanNull 原因是否可以为空。默认false不能为空
	 */
	public SetPSICaseUtil(Situation context, String title, boolean isCanNull){
		initPage(context, title, isCanNull);
	}
	
	/**
	 * 执行
	 * @param cause void
	 */
	protected abstract void action(String cause);
	
	private void initPage(Situation context, String title, boolean isCanNull) {
		PageControllerInstance pci = new PageControllerInstance("采购原因","",isCanNull?null:title);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(600, 260);
		MsgRequest request = new MsgRequest(pci, title, windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				action(String.valueOf(returnValue));
			}
		});
		context.bubbleMessage(request);
	}
}
