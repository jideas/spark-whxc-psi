package com.spark.psi.base.browser.start;

import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.portal.browser.WindowStyle.Location;
import com.spark.psi.base.browser.config.AuthConfigProcessor;
import com.spark.psi.base.browser.config.AuthConfigRender;
import com.spark.psi.base.browser.config.DepartmentConfigProcessor;
import com.spark.psi.base.browser.config.DepartmentConfigRender;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;

/**
 * <p>新增或编辑用户处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-25
 */

public class EditUserProcessor extends BaseFormPageProcessor{

	/**
	 * 控件ID
	 */
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Text_Mobile = "Text_Mobile";
	public final static String ID_Text_IdentityNumber = "Text_IdentityNumber";
	public final static String ID_Text_Email = "Text_Email";
	public final static String ID_Text_Job = "Text_Job";
	public final static String ID_Text_Department = "Text_Department";
	public final static String ID_Text_RolesInfo = "Text_RolesInfo";
	public final static String ID_Button_Save = "Button_Save";

	/**
	 * 控件
	 */
	private Text nameText;
	private Text mobileText;
	private Text identityNumberText;
	private Text emailText;
	private Text jobText;
	private Text departmentText;
	private Text rolesInfoText;
	//
	private TempUserInfo tempUserInfo;
	private SEditTable userTable;

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
	public void postProcess(Situation context){
		super.postProcess(context);
		//
		if(this.getArgument() != null){
			tempUserInfo = (TempUserInfo)this.getArgument();
			//初始化
			initUser(tempUserInfo);
		}
		else{
			tempUserInfo = new TempUserInfo();
			tempUserInfo.setId(getContext().newRECID());
			//为0表未选择任何角色
			rolesInfoText.setData(0);
		}
		//
		userTable = (SEditTable)this.getArgument2();
	}

	/**
	 * 初始化用户
	 */
	public void initUser(TempUserInfo tempUserInfo){
		nameText.setText(tempUserInfo.getName());
		mobileText.setText(tempUserInfo.getMobile());
		identityNumberText.setText(tempUserInfo.getIdentityNumber());
		emailText.setText(tempUserInfo.getEmail());
		jobText.setText(tempUserInfo.getJob());
		departmentText.setText(tempUserInfo.getDepartmentName());
		departmentText.setData(tempUserInfo.getDepartmentId());
		rolesInfoText.setText(tempUserInfo.getRolesName());
		rolesInfoText.setData(tempUserInfo.getRoles());
	}

	/**
	 * 初始化界面组件
	 */
	private void initUIComponent(){
		//姓名
		nameText = this.createControl(ID_Text_Name, Text.class);
		//手机
		mobileText = this.createControl(ID_Text_Mobile, Text.class);
		//身份证
		identityNumberText = this.createControl(ID_Text_IdentityNumber, Text.class);
		//邮箱
		emailText = this.createControl(ID_Text_Email, Text.class);
		//岗位
		jobText = this.createControl(ID_Text_Job, Text.class);
		//部门
		departmentText = this.createControl(ID_Text_Department, Text.class);
		departmentText.addActionListener(new DepartmentSelectListener());
		//用户权限
		rolesInfoText = this.createControl(ID_Text_RolesInfo, Text.class);
		rolesInfoText.addActionListener(new RolesSelectListener());
		//保存
		Button saveButton = this.createControl(ID_Button_Save, Button.class);
		saveButton.addActionListener(new SaveButtonListener());
	}

	/**
	 * 界面添加验证器
	 */
	private void addValidator(){
		//姓名
		registerNotEmptyValidator(nameText, "姓名");
		//手机
		registerNotEmptyValidator(mobileText, "手机");
		//部门
		registerNotEmptyValidator(departmentText, "部门");
		//用户权限
		registerNotEmptyValidator(rolesInfoText, "用户权限");
		//手机号必须为数字
		registerInputValidator(new TextInputValidator(mobileText, "手机号格式错误"){

			@Override
			protected boolean validateText(String text){
				if(StringHelper.isEmpty(text)){
					return true;
				}
				return text.matches(Reg.REGEXP_mob);
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
				//检查列表
				List<TempUserInfo> userList = WizardUtil.getUserInfoList(userTable);
				Map<String, TempUserInfo> map = WizardUtil.getMobileMap(userList);
				TempUserInfo userInfo = map.get(mobile);
				if(tempUserInfo == null && userInfo != null){ //新增
					return false;
				}
				else if(tempUserInfo != null && userInfo != null && !userInfo.getId().equals(tempUserInfo.getId())){
					//编辑
					return false;
				}
				return true;
			}

		});
		//邮箱必须包含@字符
		registerInputValidator(new TextInputValidator(emailText, "邮箱格式错误"){

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
	 * 部门选择侦听器
	 */
	private class DepartmentSelectListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//
			Integer[] roles = null;
			if(rolesInfoText.getData() != null && (Integer)rolesInfoText.getData() != 0){
				roles = new Integer[1];
				roles[0] = (Integer)rolesInfoText.getData();
			}else{
				roles = new Integer[]{};
			}
			//
			PageControllerInstance instance =
			        new PageControllerInstance(new PageController(DepartmentConfigProcessor.class,
			                DepartmentConfigRender.class), roles);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE, Location.Context);
			windowStyle.setSize(240, 360);
			MsgRequest request = new MsgRequest(instance, "设置部门", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					if(returnValue != null){
						DepartmentTree.DepartmentNode node = (DepartmentTree.DepartmentNode)returnValue;
						departmentText.setText(node.getName());
						departmentText.setData(node.getId());
					}
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	/**
	 * 权限选择侦听器
	 */
	private class RolesSelectListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//
			String[] depts = null;
			if(departmentText.getData() != null){
				depts = new String[1];
				depts[0] = departmentText.getData().toString();
			}else{
				depts = new String[]{};
			}
			//
			PageControllerInstance instance =
			        new PageControllerInstance(new PageController(AuthConfigProcessor.class, AuthConfigRender.class),
			                nameText.getText(), (Integer)rolesInfoText.getData(), depts);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
			windowStyle.setSize(640, 400);
			MsgRequest request = new MsgRequest(instance, "设置权限", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					rolesInfoText.setText((String)returnValue2);
					rolesInfoText.setData((Integer)returnValue);
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	/**
	 * 保存按钮侦听器
	 */
	private class SaveButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//验证数据
			if(!validateInput()){
				return;
			}
			getContext().bubbleMessage(new MsgResponse(true, buildTempUserInfo()));
		}

	}

	/**
	 * 组装临时用户对像
	 */
	private TempUserInfo buildTempUserInfo(){
		tempUserInfo.setName(StringHelper.toTrimStr(nameText.getText()));
		tempUserInfo.setMobile(StringHelper.toTrimStr(mobileText.getText()));
		tempUserInfo.setIdentityNumber(StringHelper.toTrimStr(identityNumberText.getText()));
		tempUserInfo.setEmail(StringHelper.toTrimStr(emailText.getText()));
		tempUserInfo.setJob(StringHelper.toTrimStr(jobText.getText()));
		tempUserInfo.setDepartmentId((GUID)departmentText.getData());
		tempUserInfo.setDepartmentName(StringHelper.toTrimStr(departmentText.getText()));
		tempUserInfo.setRoles((Integer)rolesInfoText.getData());
		tempUserInfo.setRolesName(StringHelper.toTrimStr(rolesInfoText.getText()));
		tempUserInfo.setCreate(Boolean.TRUE);
		return tempUserInfo;
	}

}
