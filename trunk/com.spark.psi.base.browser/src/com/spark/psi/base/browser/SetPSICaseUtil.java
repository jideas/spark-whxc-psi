package com.spark.psi.base.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;

/**
 * �����˻�����ֹԭ��Util
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-14
 */
public abstract class SetPSICaseUtil {
	public static final String StopCause = "��ֹԭ��";
	public static final String ReturnCause = "�˻�ԭ��";
	/**
	 * �����˻�����ֹԭ��
	 * @param context,������
	 * @param title,��ʹ�ó���SetReturnOrStopCauseUtil.StopCause/ReturnCause
	 */
	public SetPSICaseUtil(Situation context, String title){
		initPage(context, title, false);
	}
	
	/**
	 * �����˻�����ֹԭ��
	 * @param context,������
	 * @param title,��ʹ�ó���SetReturnOrStopCauseUtil.StopCause/ReturnCause
	 * @param isCanNull ԭ���Ƿ����Ϊ�ա�Ĭ��false����Ϊ��
	 */
	public SetPSICaseUtil(Situation context, String title, boolean isCanNull){
		initPage(context, title, isCanNull);
	}
	
	/**
	 * ִ��
	 * @param cause void
	 */
	protected abstract void action(String cause);
	
	private void initPage(Situation context, String title, boolean isCanNull) {
		PageControllerInstance pci = new PageControllerInstance("�ɹ�ԭ��","",isCanNull?null:title);
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
