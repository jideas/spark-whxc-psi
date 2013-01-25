package com.spark.psi.order.browser.sales;

import java.util.Date;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;

/**
 * <p>����Ԥ�Ƴ�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-19
 */
public abstract class SetPlanOutDate {
	private final Situation context;
	private final boolean isHaveReturnButton;

	/**
	 * Ĭ��û�в��ذ�ť
	 * @param context
	 */
	public SetPlanOutDate(Situation context) {
		this.context = context;
		isHaveReturnButton = false;
		initPage();
	}
	
	/**
	 * @param context
	 * @param isHaveReturnButton
	 */
	public SetPlanOutDate(Situation context, boolean isHaveReturnButton) {
		super();
		this.context = context;
		this.isHaveReturnButton = isHaveReturnButton;
		initPage();
	}



	private void initPage() {
		PageControllerInstance pci = new PageControllerInstance(new PageController(PlanOutDateProcessor.class, PlanOutDateRender.class), isHaveReturnButton?isHaveReturnButton:null);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(360, 160);
		MsgRequest request = new MsgRequest(pci, "����Ԥ�Ƴ�������", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				if(null == returnValue){
					deny();
				}
				else{
					setPlanOutDate((Date)returnValue);
				}
			}
		});
		context.bubbleMessage(request);
	}
	
	/**
	 * ִ�в���
	 *  void
	 */
	protected abstract void deny();
	/**
	 * ����Ԥ�Ƴ�������
	 * @param date void
	 */
	protected abstract void setPlanOutDate(Date date);
}
