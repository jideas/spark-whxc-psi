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
 * <p>��ӭ���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-26
 */

public class WelcomeProcessor extends BaseFormPageProcessor{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_Button_Self = "Button_Self";
	public final static String ID_Button_Assistant = "Button_Assistant";
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Text_Mobile = "Text_Mobile";
	public final static String ID_Button_Continue = "Button_Continue";

	/**
	 * �ؼ�
	 */
	private Button selfRadioButton;
	private Button assistantRadioButton;
	private Text nameText;
	private Text mobileText;
	
	/**
	 * ҳ���ʼ��
	 */
	@Override
    public void process(Situation context){
		//��ʼ�����
		initUIComponent();
		//�����֤��
		addValidator();
    }
	
	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context) {
		super.postProcess(context);
		//Ĭ��ѡ���������
		selfRadioButton.setSelection(true);
	}
	
	/**
	 * ��ʼ���������
	 */
	private void initUIComponent(){
		//�������
		selfRadioButton = this.createControl(ID_Button_Self, Button.class);
		selfRadioButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				//�ر�������Ϣ
				enableAssistantInfo(false);
			}
		});
		//�������
		assistantRadioButton = this.createControl(ID_Button_Assistant, Button.class);
		assistantRadioButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				//����������Ϣ
				enableAssistantInfo(true);
			}
		});
		//����
		nameText = this.createControl(ID_Text_Name, Text.class);
		//�ֻ�
		mobileText = this.createControl(ID_Text_Mobile, Text.class);
		//����
		Button continueButton = this.createControl(ID_Button_Continue, Button.class);
		continueButton.addActionListener(new ContinueButtonListener());
	}
	
	/**
	 * ���������֤��
	 */
	private void addValidator(){
		registerNotEmptyValidator(nameText, "��������");
		registerNotEmptyValidator(mobileText, "�ֻ�����");
		//����ֻ����Ƿ�Ϊ11λ
		registerInputValidator(new TextInputValidator(mobileText, "ֻ������11λ����"){
			
			@Override
			protected boolean validateText(String text){
				//
				String mobile = StringHelper.toTrimStr(mobileText.getText());
				return mobile.length() == 11;
			}
			
		});
		//����ֻ����Ƿ��ѱ�ʹ��
		registerInputValidator(new TextInputValidator(mobileText, "�ֻ����ظ�"){
			
			@Override
			protected boolean validateText(String text){
				//
				String mobile = StringHelper.toTrimStr(mobileText.getText());
				//������ݿ�Ա��
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
	 * �Ƿ���������Ϣ
	 */
	private void enableAssistantInfo(boolean enabled){
		nameText.setText("");
		nameText.setEnabled(enabled);
		mobileText.setText("");
		mobileText.setEnabled(enabled);
	}
	
	/**
	 * ������ť������
	 */
	private class ContinueButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
	        if(selfRadioButton.getSelection()){//ѡ���ܾ���
	        	getContext().bubbleMessage(new MsgResponse(true));
	        }else{//��������
	        	final UpdateEmployeeInfoTask.Item item  = saveAssistant();
	        	if(item != null){
	        		//��ϵͳ�����ѳ�ʼ����ǰ����
	        		SaveOrUpdateTenantsSysParamTask task = new SaveOrUpdateTenantsSysParamTask(SysParamKey.HAS_INIT_WELCOME, true);
	        		getContext().handle(task);
	        		//���Ͷ���
	        		TenantInfo tenantInfo = getContext().get(TenantInfo.class);
					String messageInfo =
					        "��Ϊ����ͨ��ҵ����ϵͳ�������˺ţ���ҵ����Ϊ" + tenantInfo.getTitle()
					                + "����ƾ�����ֻ������¼www.yuerduo.com����ȡ���벢��¼֮�󣬿��Կ�ʼϵͳ���ù�����";
					getContext().handle(
					        new SendMsgTask(item.getMobileNo(), messageInfo, getContext()
					                .find(LoginInfo.class).getTenantId()));
		        	//��ʾ
	        		hint("��ָ�ɵ�����"+item.getName()+"(�ֻ��ţ�"+item.getMobileNo()+")�Ѿ������ɹ���", new Runnable(){
						
						public void run(){
							getContext().bubbleMessage(new MsgResponse(true, true));
						}
					});
	        	}
	        }
	        
        }
		
	}
	
	/**
	 * ��������
	 */
	private UpdateEmployeeInfoTask.Item saveAssistant(){
		//��֤����
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
		item.setPosition("����");
		items.add(item);
		getContext().handle(new UpdateEmployeeInfoTask(items.toArray(new UpdateEmployeeInfoTask.Item[items.size()])));
		return item;
	}
}
