package com.spark.uac.browser;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.SendMessageType;
import com.spark.portal.browser.SFocusInfoWindow;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.publish.phonemessage.task.PhoneMessageSendTask;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.task.ResetPwdTask;
import com.spark.uac.publish.task.ResetPwdTask.ErrType;

public class GetPasswordWindow extends SMenuWindow{

	private Text mobileNumberText; 
	private Text userTitleText; 

	private Control owner;
	/**
	 * 
	 */
	private SFocusInfoWindow focusInfoWindow;

	public GetPasswordWindow(Control owner, String mobileNumber,
	        String tenantName)
	{
		super(null, owner, Direction.Up, ActiveMode.Click);
		this.owner = owner;
		this.bindTargetControl(owner);
		this.addClientEventHandler("beforeShow",
		        "UACLogin.handleGetPwdWindowShow");
		// this.setTitle("��ȡ����");
		// this.setSize(320, 160);

		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginTop = gl.marginBottom = 20;
		gl.marginLeft = gl.marginRight = 20;
		gl.verticalSpacing = 15;
		contentArea.setLayout(gl); 

		Label label = new Label(contentArea);
		label.setText("�������ֻ����룺");

		mobileNumberText = new Text(contentArea, JWT.APPEARANCE3);
		mobileNumberText.setID("mobileText");
		mobileNumberText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
		        "UACLogin.handleEnterKey");
		mobileNumberText.setText(mobileNumber);

//		label = new Label(contentArea);
//		label.setText("��������ҵ���ƣ�");
//
//		tenantTitleText = new Text(contentArea, JWT.APPEARANCE3);
//		tenantTitleText.setID("tenantText");
//		tenantTitleText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
//		        "UACLogin.handleEnterKey");
//		tenantTitleText.setText(tenantName == null ? "" : tenantName);

		label = new Label(contentArea);
		label.setText("����������������");

		userTitleText = new Text(contentArea, JWT.APPEARANCE3);
		userTitleText.setID("userTitleText");
		userTitleText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
		        "UACLogin.handleEnterKey");

		final Button button = new Button(contentArea, JWT.APPEARANCE3);
		button.setText(" ��ȡ���� ");
		GridData gd =
		        new GridData(GridData.GRAB_HORIZONTAL
		                | GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalSpan = 2;
		gd.heightHint = 28;
		gd.widthHint=120;
		button.setLayoutData(gd);
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				handleGetPassword(button);
			}
		});
		contentArea.addClientNotifyListener(new ClientNotifyListener(){
			public void notified(ClientNotifyEvent e){
				handleGetPassword(button);
			}
		});
	}

	private void handleGetPassword(Button button){
		String mobileNumber = mobileNumberText.getText();
		if(mobileNumber == null || mobileNumber.trim().length() == 0){
			markInputErrorstatus(mobileNumberText, "�������ֻ�����");
			return;
		}
		else{
			markInputErrorstatus(mobileNumberText, null);
		}
		String tenantTitle = "7�������"; 
		String userTitle = userTitleText.getText();
		if(userTitle == null || userTitle.trim().length() == 0){
			markInputErrorstatus(userTitleText, "��������������");
			return;
		}
		else{
			markInputErrorstatus(userTitleText, null);
		}
		ResetPwdTask task =
		        new ResetPwdTask(tenantTitle, userTitle, mobileNumber,ProductSerialsEnum.PSI);
		contentArea.getContext().handle(task); 
		if(task.getErrType() == ErrType.MobileNo){
			markInputErrorstatus(mobileNumberText, task.getMsg());
		}
		else{
			markInputErrorstatus(mobileNumberText, null);
		}
		if(task.getErrType() == ErrType.Name){
			markInputErrorstatus(userTitleText, task.getMsg());
		}
		else{
			markInputErrorstatus(userTitleText, null);
		}
		if(task.isSucceed()){
			PhoneMessageSendTask smsTask = new PhoneMessageSendTask();
			smsTask.setPhoneNo(mobileNumber);
			smsTask.setSendType(SendMessageType.UserActive);
			smsTask.setMessage("�𾴵Ĺ˿ͣ�7�������ERP�����������������ʱ����Ϊ��" + task.getCode() + "������ҳ������д��ʱ���벢�������롣");
			contentArea.getContext().handle(smsTask); 
			
			button.setText("�����ѷ��͡�����");
			button.setEnabled(false);
		}
	}

	private void markInputErrorstatus(Text text, String hint){
		if(focusInfoWindow == null){
			focusInfoWindow = new SFocusInfoWindow(this.owner);
		}
		text.removeMode(JWT.MODE_ERROR);  //���ڿͻ��˿����Ƴ�ģʽ��״̬��ͬ��
		if(StringUtils.isEmpty(hint)){
			focusInfoWindow.unbindTargetControl(text);
		}
		else{
			focusInfoWindow.bindTargetControl(text, hint);
			text.applyMode(JWT.MODE_ERROR);
			text.forceFocus();
		}
	}
}
