package com.spark.psi.base.browser.start;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Widget;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.start.task.SaveOrUpdateTenantsSysParamTask;

/**
 * <p>�����ò����������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-6
 */

public abstract class WizardBaseStepProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_Label_Wizard = "Label_Wizard";
	public final static String ID_Button_Prev = "Button_Prev";
	public final static String ID_Button_Operate = "Button_Operate";
	public final static String ID_Button_Next = "Button_Next";

	/**
	 * ���
	 */
	protected Button operateButton;
	protected Button nextButton;

	/**
	 * ҳ���ʼ��
	 * 
	 * @param situation
	 */
	@Override
	public void process(Situation context){
		final WizardStepNode node = (WizardStepNode)this.getArgument();
		//��������
		Label backWizard = this.createControl(ID_Label_Wizard, Label.class);
		backWizard.addClientEventHandler(JWT.CLIENT_EVENT_CLICK, "PSIWizard.handleBackAction");
		backWizard.addClientNotifyListener(new ClientNotifyListener(){
			public void notified(ClientNotifyEvent event){
				processBackWizardAction((Widget)event.getSource(), node);
			}
		});
		//��һҳ��Ϊ��
		Button prevButton = this.createControl(ID_Button_Prev, Button.class);
		if(prevButton != null){
			// ��һ��
			prevButton.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "PSIWizard.handlePrevAction");
			prevButton.addClientNotifyListener(new ClientNotifyListener(){
				public void notified(ClientNotifyEvent event){
					processPrevButtonAction((Widget)event.getSource(), node);
				}
			});
		}
		//������ť
		operateButton = this.createControl(ID_Button_Operate, Button.class);
		if(operateButton != null){
			operateButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e){
					// ���ִ�гɹ�
					if(operateExecute()){
						//����״̬
						resetDataChangedstatus();
						//���µ�ǰҳ��ϵͳ��ʼ������Ϊtrue
						updateSysParamValue(node.getKey());
						//
						List<String> list = WizardUtil.getPageNameList();
						if(list.indexOf(node.getPageName()) == 0){ //��һ��
							updateSysParamValue(SysParamKey.HAS_INIT_WELCOME);
						}
						else if(list.indexOf(node.getPageName()) == list.size() - 1){ //���һ��
							//�رմ���
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				}
			});
		}
		//��һҳ��Ϊ��
		nextButton = this.createControl(ID_Button_Next, Button.class);
		if(nextButton != null){
			// ��һ��
			nextButton.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "PSIWizard.handleNextAction");
			nextButton.addClientNotifyListener(new ClientNotifyListener(){
				public void notified(ClientNotifyEvent event){
					processNextButtonAction((Widget)event.getSource(), node);
				}
			});
		}

	}

	/**
	 * ����ִ��
	 */
	protected boolean operateExecute(){
		return true;
	}

	/**
	 * ��һ��ִ��
	 */
	protected boolean nextExecute(){
		return true;
	}

	/**
	 * ����ϵͳ����ֵ
	 */
	protected void updateSysParamValue(SysParamKey key){
		//�⻧ϵͳ�������Ѿ���ʼ��������ϢΪtrue
		SaveOrUpdateTenantsSysParamTask initCompanyTask = new SaveOrUpdateTenantsSysParamTask(key, Boolean.TRUE);
		getContext().handle(initCompanyTask);
	}

	/**
	 * �ر�δ������ʾ��Ϣ
	 */
	public String getPromptMessage(){
		return null;
	}

	/**
	 * ���沢�ر��߼�����
	 */
	public boolean processData(){
		return false;
	}

	/**
	 * ���������򵼣��ͻ����ж������Ƿ�仯����ʾ֮����еĴ���
	 * 
	 * @param widget
	 */
	@SuppressWarnings("deprecation")
	private void processBackWizardAction(Widget widget, WizardStepNode node){
		JSONObject actionData = widget.getClientObject("actionData");
		try{
			//�����ѷ����ı䡡�ҡ�����ʧ��
			if(actionData.getBoolean("processCurrentPage")){ //�����ѷ����ı�
				if(operateExecute()){ //����ɹ�
					//���µ�ǰҳ��ϵͳ��ʼ������Ϊtrue
					updateSysParamValue(node.getKey());
				}
				else{ //����ʧ��
					return;
				}
			}

			//���Ϸ�����Ϣ�������򵼽���
			PageControllerInstance controllerInstance = new PageControllerInstance("ConfigurationWizardPage");
			getContext().bubbleMessage(new StepPageMessage("������", controllerInstance));
		}
		catch(JSONException ex){
		}
	}

	/**
	 * ��һ����ť���º󣬿ͻ����ж������Ƿ�仯����ʾ֮����еĴ���
	 * 
	 * @param widget
	 */
	@SuppressWarnings("deprecation")
	private void processPrevButtonAction(Widget widget, WizardStepNode node){
		JSONObject actionData = widget.getClientObject("actionData");
		try{
			//�����ѷ����ı䡡�ҡ�����ʧ��
			if(actionData.getBoolean("processCurrentPage")){ //�����ѷ����ı�
				if(operateExecute()){ //����ɹ�
					//���µ�ǰҳ��ϵͳ��ʼ������Ϊtrue
					updateSysParamValue(node.getKey());
				}
				else{ //����ʧ��
					return;
				}
			}

			//���ص���һҳ
			String pageName = node.getPrevPageName();
			PageControllerInstance controllerInstance =
			        new PageControllerInstance(pageName, WizardUtil.getPageNameMap().get(pageName));
			getContext().bubbleMessage(new StepPageMessage("������", controllerInstance));
		}
		catch(JSONException ex){
		}
	}

	/**
	 * ��һ����ť���º󣬿ͻ����ж������Ƿ�仯����ʾ֮����еĴ���
	 * 
	 * @param widget
	 */
	@SuppressWarnings("deprecation")
	private void processNextButtonAction(Widget widget, WizardStepNode node){
		JSONObject actionData = widget.getClientObject("actionData");
		try{
			//�����ѷ����ı䡡�ҡ�����ʧ��
			if(actionData.getBoolean("processCurrentPage") && !operateExecute()){
				return;
			}
			else if(!actionData.getBoolean("processCurrentPage")){
				//
				postProcess(getContext());
				if(!nextExecute()){ //����δ�����ı䡡�ҡ���һ��ʧ��
					return;
				}
			}

			//��һ��
			if(WizardUtil.getPageNameList().indexOf(node.getPageName()) == 0){
				updateSysParamValue(SysParamKey.HAS_INIT_WELCOME);
			}
			//���µ�ǰҳ��ϵͳ��ʼ������Ϊtrue
			updateSysParamValue(node.getKey());
			//����һ��ҳ
			String pageName = node.getNextPageName();
			PageControllerInstance controllerInstance =
			        new PageControllerInstance(pageName, WizardUtil.getPageNameMap().get(pageName));
			getContext().bubbleMessage(new StepPageMessage("������", controllerInstance));
		}
		catch(JSONException ex){
		}
	}
}
