package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.spi.application.Session;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.WindowEvent;
import com.jiuqi.dna.ui.wt.events.WindowListener;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SMessageConfirmWindow;
import com.spark.portal.browser.FramePage;
import com.spark.portal.browser.FrameWindow;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>�����򵼴���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-26
 */

public class ConfigurationWizardWindow extends Window{

	/** 
	 *���췽��
	 */
	public ConfigurationWizardWindow(){
		super(JWT.CLOSE | JWT.MODAL);
		//
		this.setSize(1100, 540);
		this.setIcon(BaseImages.getImage("images/wizard/wizard_window_icon.png"));
		//
		regMessageListenter();
		//
		openPage("������", new PageControllerInstance("ConfigurationWizardPage", true));
	}

	/**
	 * ע����Ϣ���� 
	 */
	private void regMessageListenter(){
		//ע����´�����Ϣ����
		getContext().regMessageListener(MsgRequest.class, new MessageListener<MsgRequest>(){
			public void onMessage(Situation context, MsgRequest message, MessageTransmitter<MsgRequest> transmitter){
				transmitter.terminate();
				//���´���
				WindowStyle windowStyle = message.getWindowStyle();
				if(windowStyle == null){
					windowStyle = new WindowStyle(JWT.CLOSE);
				}
				FrameWindow window =
				        new FrameWindow(getHandle(), null, message.getFunctions(), message.getTitle(), windowStyle,
				                message.getResponseHandler(), message.getCancelHandler());
				if(message.getIcon() != null){
					window.setIcon(message.getIcon());
				}
			}
		});
		//ע�ỻҳ��Ϣ����
		getContext().regMessageListener(StepPageMessage.class, new MessageListener<StepPageMessage>(){

			public void onMessage(Situation context, StepPageMessage message,
			        MessageTransmitter<StepPageMessage> transmitter)
			{
				transmitter.terminate();
				openPage(message.getPageTitle(), message.getPageControllerInstance());
			}
		});
		//ע����Ӧ��Ϣ
		getContext().regMessageListener(MsgResponse.class, new MessageListener<MsgResponse>(){
			public void onMessage(Situation context, MsgResponse message, MessageTransmitter<MsgResponse> transmitter){
				transmitter.terminate();
				if(message.isFinished()){
					close();
				}
			}
		});
		//ע�����µ�¼��Ϣ
		getContext().regMessageListener(ReLoginMessage.class, new MessageListener<ReLoginMessage>(){

			public void onMessage(Situation context, ReLoginMessage message,
                    MessageTransmitter<ReLoginMessage> transmitter)
            {
				reLogin();
            }
		});
		//���ڹر��¼�����
		this.addWindowListener(new WindowListener(){

			public void windowClosing(WindowEvent e){
				new SMessageConfirmWindow("ȷ��Ҫ�˳�ϵͳ��", new Runnable(){

					public void run(){
						reLogin();
					}
				});
			}
		});
		
	}

	/**
	 * ��ʾ��ҳ
	 *
	 *@param title ���ڱ���
	 *@param name ��������
	 */
	private void openPage(String title, PageControllerInstance instance){
		this.setTitle(title);
		this.showPage(FramePage.NAME, new BaseFunction(instance, title));
	}
	
	/**
	 * ���µ�¼
	 */
	private void reLogin(){
		getDisplay().reset();
		((Session)getContext().getLogin()).dispose(100);
	}

}
