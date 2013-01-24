package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.entity.RoleInfo;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask;
import com.spark.psi.publish.base.sms.task.SendMsgTask;
import com.spark.psi.publish.base.start.task.SaveOrUpdateTenantsSysParamTask;

/**
 * <p>欢迎界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-26
 */

public class WelcomeProcessor extends BaseFormPageProcessor{

	/**
	 * 控件ID
	 */
	public final static String ID_Button_Self = "Button_Self";
	public final static String ID_Button_Assistant = "Button_Assistant";
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Text_Mobile = "Text_Mobile";
	public final static String ID_Button_Continue = "Button_Continue";

	/**
	 * 控件
	 */
	private Button selfRadioButton;
	private Button assistantRadioButton;
	private Text nameText;
	private Text mobileText;
	
	/**
	 * 页面初始化
	 */
	@Override
    public void process(Situation context){
		//初始化组件
		initUIComponent();
		//添加验证器
		addValidator();
    }
	
	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context) {
		super.postProcess(context);
		//默认选择自已完成
		selfRadioButton.setSelection(true);
	}
	
	/**
	 * 初始化界面组件
	 */
	private void initUIComponent(){
		//自已完成
		selfRadioButton = this.createControl(ID_Button_Self, Button.class);
		selfRadioButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				//关闭助理信息
				enableAssistantInfo(false);
			}
		});
		//助理完成
		assistantRadioButton = this.createControl(ID_Button_Assistant, Button.class);
		assistantRadioButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				//开启助理信息
				enableAssistantInfo(true);
			}
		});
		//姓名
		nameText = this.createControl(ID_Text_Name, Text.class);
		//手机
		mobileText = this.createControl(ID_Text_Mobile, Text.class);
		//继续
		Button continueButton = this.createControl(ID_Button_Continue, Button.class);
		continueButton.addActionListener(new ContinueButtonListener());
	}
	
	/**
	 * 界面添加验证器
	 */
	private void addValidator(){
		registerNotEmptyValidator(nameText, "助理姓名");
		registerNotEmptyValidator(mobileText, "手机号码");
		//检测手机号是否为11位
		registerInputValidator(new TextInputValidator(mobileText, "只能输入11位数字"){
			
			@Override
			protected boolean validateText(String text){
				//
				String mobile = StringHelper.toTrimStr(mobileText.getText());
				return mobile.length() == 11;
			}
			
		});
		//检测手机号是否已被使用
		registerInputValidator(new TextInputValidator(mobileText, "手机号重复"){
			
			@Override
			protected boolean validateText(String text){
				//
				String mobile = StringHelper.toTrimStr(mobileText.getText());
				//检查数据库员工
				GetEmployeeListKey key = new GetEmployeeListKey();
				key.setQueryAll(false);
				ListEntity<EmployeeItem> result = getContext().find(ListEntity.class, key);
				if(CheckIsNull.isNotEmpty(result.getItemList())){
					for(EmployeeItem item : result.getItemList()){
						if(item.getMobileNo().equals(mobile)){
							return false;
						}
					}
				}
				return true;
			}
			
		});
	}
	
	/**
	 * 是否开启助理信息
	 */
	private void enableAssistantInfo(boolean enabled){
		nameText.setText("");
		nameText.setEnabled(enabled);
		mobileText.setText("");
		mobileText.setEnabled(enabled);
	}
	
	/**
	 * 继续按钮侦听器
	 */
	private class ContinueButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
	        if(selfRadioButton.getSelection()){//选择总经理
	        	getContext().bubbleMessage(new MsgResponse(true));
	        }else{//创建助理
	        	final UpdateEmployeeInfoTask.Item item  = saveAssistant();
	        	if(item != null){
	        		//置系统参数已初始化当前界面
	        		SaveOrUpdateTenantsSysParamTask task = new SaveOrUpdateTenantsSysParamTask(SysParamKey.HAS_INIT_WELCOME, true);
	        		getContext().handle(task);
	        		//发送短信
	        		TenantInfo tenantInfo = getContext().get(TenantInfo.class);
					String messageInfo =
					        "已为您开通企业管理系统的助理账号，企业名称为" + tenantInfo.getTitle()
					                + "，请凭本人手机号码登录www.yuerduo.com，获取密码并登录之后，可以开始系统配置工作。";
					getContext().handle(
					        new SendMsgTask(item.getMobileNo(), messageInfo, getContext()
					                .find(LoginInfo.class).getTenantId()));
		        	//提示
	        		hint("您指派的助理"+item.getName()+"(手机号："+item.getMobileNo()+")已经创建成功。", new Runnable(){
						
						public void run(){
							getContext().bubbleMessage(new MsgResponse(true, true));
						}
					});
	        	}
	        }
	        
        }
		
	}
	
	/**
	 * 创建助理
	 */
	private UpdateEmployeeInfoTask.Item saveAssistant(){
		//验证数据
		if(!validateInput()){
			return null;
		}
		List<UpdateEmployeeInfoTask.Item> items = new ArrayList<UpdateEmployeeInfoTask.Item>();
		UpdateEmployeeInfoTask.Item item = new UpdateEmployeeInfoTask.Item();
		item.setCreate(true);
		item.setId(getContext().newRECID());
		item.setName(StringHelper.toTrimStr(nameText.getText()));
		item.setMobileNo(StringHelper.toTrimStr(mobileText.getText()));
		item.setDepartmentId(getContext().get(LoginInfo.class).getTenantId());
		item.setRoles(RoleInfo.Assistant_Role);
		item.setPosition("助理");
		items.add(item);
		getContext().handle(new UpdateEmployeeInfoTask(items.toArray(new UpdateEmployeeInfoTask.Item[items.size()])));
		return item;
	}
}
