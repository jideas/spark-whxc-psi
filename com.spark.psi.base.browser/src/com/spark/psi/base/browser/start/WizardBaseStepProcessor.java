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
 * <p>向导配置步骤基础界面</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-6
 */

public abstract class WizardBaseStepProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	/**
	 * 控件ID
	 */
	public final static String ID_Label_Wizard = "Label_Wizard";
	public final static String ID_Button_Prev = "Button_Prev";
	public final static String ID_Button_Operate = "Button_Operate";
	public final static String ID_Button_Next = "Button_Next";

	/**
	 * 组件
	 */
	protected Button operateButton;
	protected Button nextButton;

	/**
	 * 页面初始化
	 * 
	 * @param situation
	 */
	@Override
	public void process(Situation context){
		final WizardStepNode node = (WizardStepNode)this.getArgument();
		//返回置向导
		Label backWizard = this.createControl(ID_Label_Wizard, Label.class);
		backWizard.addClientEventHandler(JWT.CLIENT_EVENT_CLICK, "PSIWizard.handleBackAction");
		backWizard.addClientNotifyListener(new ClientNotifyListener(){
			public void notified(ClientNotifyEvent event){
				processBackWizardAction((Widget)event.getSource(), node);
			}
		});
		//上一页不为空
		Button prevButton = this.createControl(ID_Button_Prev, Button.class);
		if(prevButton != null){
			// 上一步
			prevButton.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "PSIWizard.handlePrevAction");
			prevButton.addClientNotifyListener(new ClientNotifyListener(){
				public void notified(ClientNotifyEvent event){
					processPrevButtonAction((Widget)event.getSource(), node);
				}
			});
		}
		//操作按钮
		operateButton = this.createControl(ID_Button_Operate, Button.class);
		if(operateButton != null){
			operateButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e){
					// 如果执行成功
					if(operateExecute()){
						//更改状态
						resetDataChangedstatus();
						//更新当前页的系统初始化参数为true
						updateSysParamValue(node.getKey());
						//
						List<String> list = WizardUtil.getPageNameList();
						if(list.indexOf(node.getPageName()) == 0){ //第一个
							updateSysParamValue(SysParamKey.HAS_INIT_WELCOME);
						}
						else if(list.indexOf(node.getPageName()) == list.size() - 1){ //最后一个
							//关闭窗口
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				}
			});
		}
		//下一页不为空
		nextButton = this.createControl(ID_Button_Next, Button.class);
		if(nextButton != null){
			// 下一步
			nextButton.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "PSIWizard.handleNextAction");
			nextButton.addClientNotifyListener(new ClientNotifyListener(){
				public void notified(ClientNotifyEvent event){
					processNextButtonAction((Widget)event.getSource(), node);
				}
			});
		}

	}

	/**
	 * 操作执行
	 */
	protected boolean operateExecute(){
		return true;
	}

	/**
	 * 下一步执行
	 */
	protected boolean nextExecute(){
		return true;
	}

	/**
	 * 更新系统参数值
	 */
	protected void updateSysParamValue(SysParamKey key){
		//租户系统参数，已经初始化参数信息为true
		SaveOrUpdateTenantsSysParamTask initCompanyTask = new SaveOrUpdateTenantsSysParamTask(key, Boolean.TRUE);
		getContext().handle(initCompanyTask);
	}

	/**
	 * 关闭未保存提示信息
	 */
	public String getPromptMessage(){
		return null;
	}

	/**
	 * 保存并关闭逻辑处理
	 */
	public boolean processData(){
		return false;
	}

	/**
	 * 返回配置向导，客户端判断数据是否变化并提示之后进行的处理
	 * 
	 * @param widget
	 */
	@SuppressWarnings("deprecation")
	private void processBackWizardAction(Widget widget, WizardStepNode node){
		JSONObject actionData = widget.getClientObject("actionData");
		try{
			//数据已发生改变　且　保存失败
			if(actionData.getBoolean("processCurrentPage")){ //数据已发生改变
				if(operateExecute()){ //保存成功
					//更新当前页的系统初始化参数为true
					updateSysParamValue(node.getKey());
				}
				else{ //保存失败
					return;
				}
			}

			//向上发送消息打开配置向导界面
			PageControllerInstance controllerInstance = new PageControllerInstance("ConfigurationWizardPage");
			getContext().bubbleMessage(new StepPageMessage("配置向导", controllerInstance));
		}
		catch(JSONException ex){
		}
	}

	/**
	 * 上一步按钮按下后，客户端判断数据是否变化并提示之后进行的处理
	 * 
	 * @param widget
	 */
	@SuppressWarnings("deprecation")
	private void processPrevButtonAction(Widget widget, WizardStepNode node){
		JSONObject actionData = widget.getClientObject("actionData");
		try{
			//数据已发生改变　且　保存失败
			if(actionData.getBoolean("processCurrentPage")){ //数据已发生改变
				if(operateExecute()){ //保存成功
					//更新当前页的系统初始化参数为true
					updateSysParamValue(node.getKey());
				}
				else{ //保存失败
					return;
				}
			}

			//返回到上一页
			String pageName = node.getPrevPageName();
			PageControllerInstance controllerInstance =
			        new PageControllerInstance(pageName, WizardUtil.getPageNameMap().get(pageName));
			getContext().bubbleMessage(new StepPageMessage("配置向导", controllerInstance));
		}
		catch(JSONException ex){
		}
	}

	/**
	 * 下一步按钮按下后，客户端判断数据是否变化并提示之后进行的处理
	 * 
	 * @param widget
	 */
	@SuppressWarnings("deprecation")
	private void processNextButtonAction(Widget widget, WizardStepNode node){
		JSONObject actionData = widget.getClientObject("actionData");
		try{
			//数据已发生改变　且　保存失败
			if(actionData.getBoolean("processCurrentPage") && !operateExecute()){
				return;
			}
			else if(!actionData.getBoolean("processCurrentPage")){
				//
				postProcess(getContext());
				if(!nextExecute()){ //数据未发生改变　且　下一步失败
					return;
				}
			}

			//第一个
			if(WizardUtil.getPageNameList().indexOf(node.getPageName()) == 0){
				updateSysParamValue(SysParamKey.HAS_INIT_WELCOME);
			}
			//更新当前页的系统初始化参数为true
			updateSysParamValue(node.getKey());
			//跳到一下页
			String pageName = node.getNextPageName();
			PageControllerInstance controllerInstance =
			        new PageControllerInstance(pageName, WizardUtil.getPageNameMap().get(pageName));
			getContext().bubbleMessage(new StepPageMessage("配置向导", controllerInstance));
		}
		catch(JSONException ex){
		}
	}
}
