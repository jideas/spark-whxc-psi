package com.spark.psi.base.browser.contact;

import java.util.Date;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.contact.key.FindMainPersonCountKey;
import com.spark.psi.publish.base.contact.task.UpdateContactInfoTask;

/**
 * 
 * <p>��ϵ�˱༭���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-4
 */
public class ContactEditProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Button_MainPerson = "Button_MainPerson";
	public final static String ID_ComboList_Sex = "ComboList_Sex";
	public final static String ID_Text_NickName = "Text_NickName";
	public final static String ID_Text_Mobile = "Text_Mobile";
	public final static String ID_Text_Phone = "Text_Phone";
	public final static String ID_Text_Email = "Text_Email";
	public final static String ID_Text_Company = "Text_Company";
	public final static String ID_Text_Department = "Text_department";
	public final static String ID_Text_Job = "Text_Job";
	public final static String ID_Text_QQ = "Text_QQ";
	public final static String ID_Text_Msn = "Text_Msn";
	public final static String ID_Text_Aliim = "Text_Aliim";
	public final static String ID_Date_Birthday = "Date_Birthday";
	public final static String ID_Text_Favorite = "Text_Favorite";
	public final static String ID_Text_Comments = "Text_Comments";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_SaveAdd = "Button_SaveAdd";
	
	/**
	 * �ؼ�
	 */
	private Text nameText;
	private Button mainPersonCbx;
	private LWComboList sexComboList;
	private Text nickNameText;
	private Text mobileText;
	private Text phoneText;
	private Text emailText;
	private Text companyText;
	private Label companyLabel;
	private Text departmentText;
	private Text jobText;
	private Text qqText;
	private Text msnText;
	private Text aliimText;
	private DatePicker birthdayDate;
	private Text favoriteText;
	private Text commentsText;
	private Button saveButton;
	
	
	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context) {
		//��ʼ�������
		initUIComponent();
		//�����֤��
		addValidator();
	}
	
	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context ������
	 */
	public void postProcess(Situation context) {
		sexComboList.getList().setSource(new EnumEntitySource(EnumType.Sex));
		sexComboList.getList().setInput("");
		sexComboList.setSelection("01");
		//��ʼ����ϵ��
		initContactBookInfo();
	}
	
	/**
	 * �ж��Ƿ񴴽�����ϵ��
	 */
	private boolean isParter(Object obj){
		return null != obj && obj instanceof ContactBookInfo && ((ContactBookInfo)obj).getType().equals(ContactType.Partner);
	}
	
	/**
	 * ��ʼ���������
	 */
	private void initUIComponent(){
		//����
		nameText = this.createControl(ID_Text_Name, Text.class, JWT.NONE);
		//����ϵ��
		if(isParter(this.getArgument())){
			mainPersonCbx = this.createControl(ID_Button_MainPerson, Button.class, JWT.NONE);
			mainPersonCbx.addMouseClickListener(new MainPersonSelectedListener());
		}
		//�Ա�
		sexComboList = this.createControl(ID_ComboList_Sex, LWComboList.class, JWT.NONE);
		//���
		nickNameText = this.createControl(ID_Text_NickName, Text.class, JWT.NONE);
		//�ֻ� 
		mobileText = this.createControl(ID_Text_Mobile, Text.class, JWT.NONE);
		//�̻�
		phoneText = this.createControl(ID_Text_Phone, Text.class, JWT.NONE);
		//Email
		emailText = this.createControl(ID_Text_Email, Text.class, JWT.NONE);
		if(null == this.getArgument()){
			//��λ
			companyText = this.createControl(ID_Text_Company, Text.class, JWT.NONE);
		}else{
			//��λ
			companyLabel = this.createControl(ID_Text_Company, Label.class, JWT.NONE);
		}
		//����
		departmentText = this.createControl(ID_Text_Department, Text.class, JWT.NONE);
		//ְλ
		jobText = this.createControl(ID_Text_Job, Text.class, JWT.NONE);
		//QQ
		qqText = this.createControl(ID_Text_QQ, Text.class, JWT.NONE);
		//MSN
		msnText = this.createControl(ID_Text_Msn, Text.class, JWT.NONE);
		//����
		aliimText = this.createControl(ID_Text_Aliim, Text.class, JWT.NONE);
		//����
		birthdayDate = this.createControl(ID_Date_Birthday, DatePicker.class, JWT.NONE);
		//����
		favoriteText = this.createControl(ID_Text_Favorite, Text.class, JWT.NONE);
		//��ע
		commentsText = this.createControl(ID_Text_Comments, Text.class, JWT.NONE);
		//���水ť
		saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new SaveButtonListener());
		//���沢����
		createSaveAddButton();
	}
	
	/**
	 * ���沢������ť
	 */
	private void createSaveAddButton(){
		if(null == this.getArgument()){ //����
			//���沢������ť
			Button saveAddButton = this.createControl(ID_Button_SaveAdd, Button.class, JWT.NONE);
			saveAddButton.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					//��֤����
					if(!validateInput()){
						return;
					}
					getContext().handle(buildUpdateContactInfoTask(), UpdateContactInfoTask.Method.Create);
					MsgResponse response = new MsgResponse(false);
					getContext().bubbleMessage(response);
					ContactEditProcessor.this.clearData();
				}
			});
		}
	}
	
	/**
	 * ��ʼ����ϵ��
	 */
	private void initContactBookInfo(){
		ContactBookInfo contactBookInfo = (ContactBookInfo)this.getArgument();
		if(null != contactBookInfo){
			//����
			nameText.setText(contactBookInfo.getName());
			//�Ƿ���������ϵ��
			if(isParter(this.getArgument())){
				mainPersonCbx.setSelection(contactBookInfo.isMain());
			}
			//�Ա�
			if(null != contactBookInfo.getSex()){
				sexComboList.setSelection(contactBookInfo.getSex().getCode());
			}
			//���
			nickNameText.setText(contactBookInfo.getNickName());
			//�ֻ�
			mobileText.setText(contactBookInfo.getMobileNo());
			//�̻�
			phoneText.setText(contactBookInfo.getLandLineNumber());
			//����
			emailText.setText(contactBookInfo.getEmail());
			//��λ
			companyLabel.setText(contactBookInfo.getCompany());
			companyLabel.setToolTipText(contactBookInfo.getCompany());
			//����
			departmentText.setText(contactBookInfo.getDepartment());
			//ְλ
			jobText.setText(contactBookInfo.getPosition());
			//QQ
			qqText.setText(contactBookInfo.getQqNumber());
			//MSN
			msnText.setText(contactBookInfo.getMsnNumber());
			//����
			aliimText.setText(contactBookInfo.getWwNumber());
			//����
			birthdayDate.setDate(contactBookInfo.getBirth() != 0 ? new Date(contactBookInfo.getBirth()) : null);
			//����
			favoriteText.setText(contactBookInfo.getHobbies());
			//��ע
			commentsText.setText(contactBookInfo.getRemark());
		}
	}
	
	/**
	 * ���������֤��
	 */
	private void addValidator(){
		//����
		registerNotEmptyValidator(nameText, "����");
		//�Ա�
		registerNotEmptyValidator(sexComboList, "�Ա�");
		//��λ
		if(null == this.getArgument()){
			registerNotEmptyValidator(companyText, "��λ");
		}
		//�ֻ��ű���Ϊ����
		registerInputValidator(new TextInputValidator(mobileText, "�ֻ���ʽ����"){

			@Override
            protected boolean validateText(String text){
				if(StringHelper.isEmpty(text)){
					return true;
				}
	            return text.matches(Reg.REGEXP_mob);
            }

		});
		//�̻�
		registerInputValidator(new TextInputValidator(phoneText, "�̻���ʽ����"){
			
			@Override
			protected boolean validateText(String text){
				if(StringHelper.isEmpty(text)){
					return true;
				}
				return text.matches(Reg.REGEXP_PHONE);
			}
			
		});
		//����������@�ַ�
		registerInputValidator(new TextInputValidator(emailText, "�����ʽ����"){
			
			@Override
			protected boolean validateText(String text){
				if(StringHelper.isEmpty(text)){
					return true;
				}
				return text.contains("@");
			}
			
		});
	}
	
	/**
	 * ����ϵ��ѡ��������
	 */
	private class MainPersonSelectedListener implements MouseClickListener{

		public void click(MouseEvent e){
			ContactBookInfo contactBookInfo = (ContactBookInfo)getArgument();
			//ѡ��Ϊ����ϵ��
	        if(mainPersonCbx.getSelection() && !contactBookInfo.isMain()){
	        	FindMainPersonCountKey key = new FindMainPersonCountKey(contactBookInfo.getPartnerId(), contactBookInfo.getId());
	        	Long count = getContext().get(Long.class, key);
	        	//�Ѵ�������ϵ��
	        	if(count > 0){
	        		mainPersonCbx.setSelection(false);
	        		confirm("�Ѵ�������ϵ�ˣ�ȷ�������", new Runnable(){
						
						public void run(){
							mainPersonCbx.setSelection(true);
						}
					});
	        	}
	        }
        }
	}
	
	/**
	 * ������ϵ��������
	 */
	private class SaveButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			saveContactPerson();
		}
	}
	
	/**
	 * ������ϵ��
	 */
	private boolean saveContactPerson(){
		//��֤����
		if(!validateInput()){
			return false;
		}
		UpdateContactInfoTask updateContactInfoTask = buildUpdateContactInfoTask();
		if(null == getArgument()){ //����
			getContext().handle(updateContactInfoTask, UpdateContactInfoTask.Method.Create);
		}else{//�༭
			getContext().handle(updateContactInfoTask, UpdateContactInfoTask.Method.UpdateBook);
		}
		hint("����ɹ���");
		MsgResponse response = new MsgResponse(true);
		getContext().bubbleMessage(response);
		return true;
	}
	
	/**
	 * ��װ��ϵ��
	 */
	private UpdateContactInfoTask buildUpdateContactInfoTask(){
		UpdateContactInfoTask updateContactInfoTask = new UpdateContactInfoTask();
		//������ϵ��
		updateContactInfoTask.setType(ContactType.Personal);
		//��ϵ��ID
		if(null == this.getArgument()){
			updateContactInfoTask.setId(getContext().newRECID());
		}else{
			updateContactInfoTask.setId(((ContactBookInfo)this.getArgument()).getId());
		}
		//����
		updateContactInfoTask.setName(StringUtils.trim(nameText.getText()));
		//�Ƿ�����ϵ��
		if(isParter(this.getArgument())){
			updateContactInfoTask.setMain(mainPersonCbx.getSelection());
		}
		//�Ա�
		updateContactInfoTask.setSexCode(StringUtils.trim(sexComboList.getText()));
		//���
		updateContactInfoTask.setNickName(StringUtils.trim(nickNameText.getText()));
		//�ֻ�
		updateContactInfoTask.setMobileNo(mobileText.getText());
		//�̻�
		updateContactInfoTask.setLandLineNumber(StringUtils.trim(phoneText.getText()));
		//����
		updateContactInfoTask.setEmail(StringUtils.trim(emailText.getText()));
		//��λ
		if(null != this.getArgument()){
			updateContactInfoTask.setCompanyName(StringUtils.trim(companyLabel.getText()));
		}else{
			updateContactInfoTask.setCompanyName(StringUtils.trim(companyText.getText()));
		}
		//����
		updateContactInfoTask.setDepartment(StringUtils.trim(departmentText.getText()));
		//ְλ
		updateContactInfoTask.setPosition(StringUtils.trim(jobText.getText()));
		//QQ
		updateContactInfoTask.setQqNumber(StringUtils.trim(qqText.getText()));
		//MSN
		updateContactInfoTask.setMsnNumber(StringUtils.trim(msnText.getText()));
		//����
		updateContactInfoTask.setWwNumber(StringUtils.trim(aliimText.getText()));
		//����
		updateContactInfoTask.setBirth(null != birthdayDate.getDate() ? birthdayDate.getDate().getTime() : 0);
		//����
		updateContactInfoTask.setHobbies(StringUtils.trim(favoriteText.getText()));
		//��ע
		updateContactInfoTask.setMemo(StringUtils.trim(commentsText.getText()));
		return updateContactInfoTask;
	}
	
	/**
	 * �����������
	 */
	private void clearData(){
		//����
		nameText.setText(null);
		//�Ա�
		sexComboList.setSelection("01");
		//���
		nickNameText.setText(null);
		//�ֻ�
		mobileText.setText(null);
		//�̻�
		phoneText.setText(null);
		//����
		emailText.setText(null);
		//��λ
		companyText.setText(null);
		//����
		departmentText.setText(null);
		//ְλ
		jobText.setText(null);
		//QQ
		qqText.setText(null);
		//MSN
		msnText.setText(null);
		//����
		aliimText.setText(null);
		//����
		birthdayDate.setDate(null);
		//����
		favoriteText.setText(null);
		//��ע
		commentsText.setText(null);
		//
		resetDataChangedstatus();
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
		if(saveContactPerson()){
			resetDataChangedstatus();
			return true;
		}
		else{
			return false;
		}
	}

}
