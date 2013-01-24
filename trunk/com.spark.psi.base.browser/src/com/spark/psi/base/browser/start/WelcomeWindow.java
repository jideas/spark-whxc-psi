package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;

/**
 * <p>��ӭ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-2
 */

public class WelcomeWindow extends Window{

	/**��Ӧ*/
	private final ResponseHandler responseHandler;
	
	/** 
     *���췽��
     */
    public WelcomeWindow(ResponseHandler responseHandler){
	    super(JWT.CLOSE | JWT.MODAL | JWT.NO_TRIM);
	    //
	    this.responseHandler = responseHandler;
	    //
	    this.setSize(614, 384);
	    //
	    Page page = this.showPage(ControllerPage.NAME,  new PageControllerInstance("WelcomePage"));
	    //
	    regMessageListener(page);
    }
    
    /**
     * ע����Ϣ����
     */
    private void regMessageListener(Page page){
    	//ע����Ӧ��Ϣ
    	page.getContext().regMessageListener(MsgResponse.class,
				new MessageListener<MsgResponse>() {
					public void onMessage(Situation context,
							MsgResponse message,
							MessageTransmitter<MsgResponse> transmitter) {
						transmitter.terminate();
						if (responseHandler != null) {
							responseHandler.handle(message.getReturnValue(),
									message.getReturnValue2(),
									message.getReturnValue3(),
									message.getReturnValue4());
						}
						if (message.isFinished()) {
							close();
						}
					}
				});
    }

}
