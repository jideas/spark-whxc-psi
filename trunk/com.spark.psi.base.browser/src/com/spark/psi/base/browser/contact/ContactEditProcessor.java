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
 * <p>联系人编辑界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-4
 */
public class ContactEditProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	/**
	 * 控件ID
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
	 * 控件
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
	 * 页面初始化
	 */
	@Override
	public void process(Situation context) {
		//初始界面组件
		initUIComponent();
		//添加验证器
		addValidator();
	}
	
	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context) {
		sexComboList.getList().setSource(new EnumEntitySource(EnumType.Sex));
		sexComboList.getList().setInput("");
		sexComboList.setSelection("01");
		//初始化联系人
		initContactBookInfo();
	}
	
	/**
	 * 判断是否创建主联系人
	 */
	private boolean isParter(Object obj){
		return null != obj && obj instanceof ContactBookInfo && ((ContactBookInfo)obj).getType().equals(ContactType.Partner);
	}
	
	/**
	 * 初始化界面组件
	 */
	private void initUIComponent(){
		//姓名
		nameText = this.createControl(ID_Text_Name, Text.class, JWT.NONE);
		//主联系人
		if(isParter(this.getArgument())){
			mainPersonCbx = this.createControl(ID_Button_MainPerson, Button.class, JWT.NONE);
			mainPersonCbx.addMouseClickListener(new MainPersonSelectedListener());
		}
		//性别
		sexComboList = this.createControl(ID_ComboList_Sex, LWComboList.class, JWT.NONE);
		//尊称
		nickNameText = this.createControl(ID_Text_NickName, Text.class, JWT.NONE);
		//手机 
		mobileText = this.createControl(ID_Text_Mobile, Text.class, JWT.NONE);
		//固话
		phoneText = this.createControl(ID_Text_Phone, Text.class, JWT.NONE);
		//Email
		emailText = this.createControl(ID_Text_Email, Text.class, JWT.NONE);
		if(null == this.getArgument()){
			//单位
			companyText = this.createControl(ID_Text_Company, Text.class, JWT.NONE);
		}else{
			//单位
			companyLabel = this.createControl(ID_Text_Company, Label.class, JWT.NONE);
		}
		//部门
		departmentText = this.createControl(ID_Text_Department, Text.class, JWT.NONE);
		//职位
		jobText = this.createControl(ID_Text_Job, Text.class, JWT.NONE);
		//QQ
		qqText = this.createControl(ID_Text_QQ, Text.class, JWT.NONE);
		//MSN
		msnText = this.createControl(ID_Text_Msn, Text.class, JWT.NONE);
		//旺旺
		aliimText = this.createControl(ID_Text_Aliim, Text.class, JWT.NONE);
		//生日
		birthdayDate = this.createControl(ID_Date_Birthday, DatePicker.class, JWT.NONE);
		//爱好
		favoriteText = this.createControl(ID_Text_Favorite, Text.class, JWT.NONE);
		//备注
		commentsText = this.createControl(ID_Text_Comments, Text.class, JWT.NONE);
		//保存按钮
		saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new SaveButtonListener());
		//保存并新增
		createSaveAddButton();
	}
	
	/**
	 * 保存并新增按钮
	 */
	private void createSaveAddButton(){
		if(null == this.getArgument()){ //新增
			//保存并新增按钮
			Button saveAddButton = this.createControl(ID_Button_SaveAdd, Button.class, JWT.NONE);
			saveAddButton.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					//验证数据
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
	 * 初始化联系人
	 */
	private void initContactBookInfo(){
		ContactBookInfo contactBookInfo = (ContactBookInfo)this.getArgument();
		if(null != contactBookInfo){
			//姓名
			nameText.setText(contactBookInfo.getName());
			//是否设置主联系人
			if(isParter(this.getArgument())){
				mainPersonCbx.setSelection(contactBookInfo.isMain());
			}
			//性别
			if(null != contactBookInfo.getSex()){
				sexComboList.setSelection(contactBookInfo.getSex().getCode());
			}
			//尊称
			nickNameText.setText(contactBookInfo.getNickName());
			//手机
			mobileText.setText(contactBookInfo.getMobileNo());
			//固话
			phoneText.setText(contactBookInfo.getLandLineNumber());
			//邮箱
			emailText.setText(contactBookInfo.getEmail());
			//单位
			companyLabel.setText(contactBookInfo.getCompany());
			companyLabel.setToolTipText(contactBookInfo.getCompany());
			//部门
			departmentText.setText(contactBookInfo.getDepartment());
			//职位
			jobText.setText(contactBookInfo.getPosition());
			//QQ
			qqText.setText(contactBookInfo.getQqNumber());
			//MSN
			msnText.setText(contactBookInfo.getMsnNumber());
			//旺旺
			aliimText.setText(contactBookInfo.getWwNumber());
			//生日
			birthdayDate.setDate(contactBookInfo.getBirth() != 0 ? new Date(contactBookInfo.getBirth()) : null);
			//爱好
			favoriteText.setText(contactBookInfo.getHobbies());
			//备注
			commentsText.setText(contactBookInfo.getRemark());
		}
	}
	
	/**
	 * 界面添加验证器
	 */
	private void addValidator(){
		//姓名
		registerNotEmptyValidator(nameText, "姓名");
		//性别
		registerNotEmptyValidator(sexComboList, "性别");
		//单位
		if(null == this.getArgument()){
			registerNotEmptyValidator(companyText, "单位");
		}
		//手机号必须为数字
		registerInputValidator(new TextInputValidator(mobileText, "手机格式错误！"){

			@Override
            protected boolean validateText(String text){
				if(StringHelper.isEmpty(text)){
					return true;
				}
	            return text.matches(Reg.REGEXP_mob);
            }

		});
		//固话
		registerInputValidator(new TextInputValidator(phoneText, "固话格式错误！"){
			
			@Override
			protected boolean validateText(String text){
				if(StringHelper.isEmpty(text)){
					return true;
				}
				return text.matches(Reg.REGEXP_PHONE);
			}
			
		});
		//邮箱必须包含@字符
		registerInputValidator(new TextInputValidator(emailText, "邮箱格式错误！"){
			
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
	 * 主联系人选择侦听器
	 */
	private class MainPersonSelectedListener implements MouseClickListener{

		public void click(MouseEvent e){
			ContactBookInfo contactBookInfo = (ContactBookInfo)getArgument();
			//选择为主联系人
	        if(mainPersonCbx.getSelection() && !contactBookInfo.isMain()){
	        	FindMainPersonCountKey key = new FindMainPersonCountKey(contactBookInfo.getPartnerId(), contactBookInfo.getId());
	        	Long count = getContext().get(Long.class, key);
	        	//已存在主联系人
	        	if(count > 0){
	        		mainPersonCbx.setSelection(false);
	        		confirm("已存在主联系人，确定变更？", new Runnable(){
						
						public void run(){
							mainPersonCbx.setSelection(true);
						}
					});
	        	}
	        }
        }
	}
	
	/**
	 * 保存联系人侦听器
	 */
	private class SaveButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			saveContactPerson();
		}
	}
	
	/**
	 * 保存联系人
	 */
	private boolean saveContactPerson(){
		//验证数据
		if(!validateInput()){
			return false;
		}
		UpdateContactInfoTask updateContactInfoTask = buildUpdateContactInfoTask();
		if(null == getArgument()){ //新增
			getContext().handle(updateContactInfoTask, UpdateContactInfoTask.Method.Create);
		}else{//编辑
			getContext().handle(updateContactInfoTask, UpdateContactInfoTask.Method.UpdateBook);
		}
		hint("保存成功！");
		MsgResponse response = new MsgResponse(true);
		getContext().bubbleMessage(response);
		return true;
	}
	
	/**
	 * 组装联系人
	 */
	private UpdateContactInfoTask buildUpdateContactInfoTask(){
		UpdateContactInfoTask updateContactInfoTask = new UpdateContactInfoTask();
		//个人联系人
		updateContactInfoTask.setType(ContactType.Personal);
		//联系人ID
		if(null == this.getArgument()){
			updateContactInfoTask.setId(getContext().newRECID());
		}else{
			updateContactInfoTask.setId(((ContactBookInfo)this.getArgument()).getId());
		}
		//姓名
		updateContactInfoTask.setName(StringUtils.trim(nameText.getText()));
		//是否主联系人
		if(isParter(this.getArgument())){
			updateContactInfoTask.setMain(mainPersonCbx.getSelection());
		}
		//性别
		updateContactInfoTask.setSexCode(StringUtils.trim(sexComboList.getText()));
		//尊称
		updateContactInfoTask.setNickName(StringUtils.trim(nickNameText.getText()));
		//手机
		updateContactInfoTask.setMobileNo(mobileText.getText());
		//固话
		updateContactInfoTask.setLandLineNumber(StringUtils.trim(phoneText.getText()));
		//邮箱
		updateContactInfoTask.setEmail(StringUtils.trim(emailText.getText()));
		//单位
		if(null != this.getArgument()){
			updateContactInfoTask.setCompanyName(StringUtils.trim(companyLabel.getText()));
		}else{
			updateContactInfoTask.setCompanyName(StringUtils.trim(companyText.getText()));
		}
		//部门
		updateContactInfoTask.setDepartment(StringUtils.trim(departmentText.getText()));
		//职位
		updateContactInfoTask.setPosition(StringUtils.trim(jobText.getText()));
		//QQ
		updateContactInfoTask.setQqNumber(StringUtils.trim(qqText.getText()));
		//MSN
		updateContactInfoTask.setMsnNumber(StringUtils.trim(msnText.getText()));
		//旺旺
		updateContactInfoTask.setWwNumber(StringUtils.trim(aliimText.getText()));
		//生日
		updateContactInfoTask.setBirth(null != birthdayDate.getDate() ? birthdayDate.getDate().getTime() : 0);
		//爱好
		updateContactInfoTask.setHobbies(StringUtils.trim(favoriteText.getText()));
		//备注
		updateContactInfoTask.setMemo(StringUtils.trim(commentsText.getText()));
		return updateContactInfoTask;
	}
	
	/**
	 * 清除界面数据
	 */
	private void clearData(){
		//姓名
		nameText.setText(null);
		//性别
		sexComboList.setSelection("01");
		//尊称
		nickNameText.setText(null);
		//手机
		mobileText.setText(null);
		//固话
		phoneText.setText(null);
		//邮箱
		emailText.setText(null);
		//单位
		companyText.setText(null);
		//部门
		departmentText.setText(null);
		//职位
		jobText.setText(null);
		//QQ
		qqText.setText(null);
		//MSN
		msnText.setText(null);
		//旺旺
		aliimText.setText(null);
		//生日
		birthdayDate.setDate(null);
		//爱好
		favoriteText.setText(null);
		//备注
		commentsText.setText(null);
		//
		resetDataChangedstatus();
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
		if(saveContactPerson()){
			resetDataChangedstatus();
			return true;
		}
		else{
			return false;
		}
	}

}
