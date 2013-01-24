package com.spark.psi.base.browser.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.DataImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.MsgCompanyInfoChanged;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.config.task.UpdateCompanyInfoTask;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask;
import com.spark.psi.publish.base.sms.task.SendMsgTask;
import com.spark.psi.publish.base.start.key.FindSysParamValueKey;
import com.spark.psi.publish.base.start.task.SaveOrUpdateTenantsSysParamTask;
import com.spark.psi.publish.base.store.task.StoreInfoTask;

/**
 * <p>快速配置界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-25
 */

public class QuickConfigurationProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	// 默认logo
	public final static ImageDescriptor DEFAULF_LOGO = BaseImages.getImage("/images/config/logo.png");
	// 系统背景色
	public final static ImageDescriptor DEFAULF_BACK = BaseImages.getImage("/images/config/top-bg01.gif");
	// 允许上传图片的格式
	static final String[] suffixs = {"jpg", "png", "gif"};
	// LOGO图片最大容量值
	final static int MAX_LENGTH = 30000;
	final static int MAX_WIDTH = 150;
	final static int MAX_HEIGHT = 50;

	/**
	 * 控件ID
	 */
	public final static String ID_Icon_BackWizard = "Icon_BackWizard";
	public final static String ID_Label_BackWizard = "Label_BackWizard";
	public final static String ID_Text_ShortName = "Text_ShortName";
	public final static String ID_Text_FullName = "Text_FullName";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_Phone = "Text_Phone";
	public final static String ID_Text_Fax = "Text_Fax";
	public final static String ID_Label_Logo = "Label_Logo";
	public final static String ID_FileChooser_Img = "FileChooser_Img";
	public final static String ID_Button_UploadImg = "Button_UploadImg";
	public final static String ID_Text_Title = "Text_Title";
	public final static String ID_Button_ModifyTitle = "Button_ModifyTitle";
	public final static String ID_Table_SetUser = "Table_SetUser";
	public final static String ID_Table_SetStorage = "Table_SetStorage";
	public final static String ID_Button_StartSystem = "Button_StartSystem";

	/**
	 * 控件
	 */
	private Text shortNameText;
	private Text fullNameText;
	private Text addressText;
	private Text postCodeText;
	private Text phoneText;
	private Text faxText;
	private LWComboList provinceList;
	private LWComboList cityList;
	private LWComboList districtList;
	private Label logoLabel;
	private FileChooser fileImg;
	private Text titleText;
	private SEditTable setUserTable;
	private SEditTable setStorageTable;
	//
	private GUID tenantId;
	private byte[] logoData;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation context){
		// 创建组件
		createUIComponent();
		//添加验证器
		addValidator();
		//注册消息
		regMessageListener();
	}

	/**
	 * 注册消息
	 */
	private void regMessageListener(){
		//验证用户是否被使用
		getContext().regMessageListener(ValidateUserUsedUpMessage.class,
		        new MessageListener<ValidateUserUsedUpMessage>(){

			        public void onMessage(Situation context, ValidateUserUsedUpMessage message,
			                MessageTransmitter<ValidateUserUsedUpMessage> transmitter)
			        {
				        //向下转发消息
				        final ValidateUserUsedUpMessage upMessage = message;
				        ValidateUserUsedDownMessage downMessage = new ValidateUserUsedDownMessage(message.getUserId());
				        downMessage.setResponseHandler(new ResponseHandler(){

					        public void handle(Object returnValue, Object returnValue2, Object returnValue3,
					                Object returnValue4)
					        {
						        upMessage.getResponseHandler().handle(returnValue, returnValue2, returnValue3,
						                returnValue4);
					        }
				        });
				        getContext().broadcastMessage(downMessage);
			        }

		        });
	}

	/**
	 * 创建组件
	 */
	private void createUIComponent(){
		//返回配置向导按钮
		Label backWizardIcon = this.createControl(ID_Icon_BackWizard, Label.class);
		backWizardIcon.addMouseClickListener(new BackWizardClickListener());
		Label backWizard = this.createControl(ID_Label_BackWizard, Label.class);
		backWizard.addMouseClickListener(new BackWizardClickListener());
		//公司简称
		shortNameText = this.createControl(ID_Text_ShortName, Text.class);
		//全称
		fullNameText = this.createControl(ID_Text_FullName, Text.class);
		//地址
		addressText = this.createControl(ID_Text_Address, Text.class);
		//邮编
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		//电话
		phoneText = this.createControl(ID_Text_Phone, Text.class);
		//传真
		faxText = this.createControl(ID_Text_Fax, Text.class);
		//省
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		//市
		cityList = this.createControl(ID_List_City, LWComboList.class);
		//县
		districtList = this.createControl(ID_List_District, LWComboList.class);
		//Logo
		logoLabel = this.createControl(ID_Label_Logo, Label.class);
		// 文件上传
		fileImg = this.createControl(ID_FileChooser_Img, FileChooser.class, JWT.NONE);
		Button uploadButton = this.createControl(ID_Button_UploadImg, Button.class, JWT.NONE);
		uploadButton.addActionListener(new UploadButtonListener());
		fileImg.setRelativeTarget(uploadButton, ActionListener.class);
		//修改标题
		titleText = this.createControl(ID_Text_Title, Text.class);
		Button modifyTitleButton = this.createControl(ID_Button_ModifyTitle, Button.class);
		modifyTitleButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub

			}
		});
		//设置用户table
		setUserTable = this.createControl(ID_Table_SetUser, SEditTable.class);
		//设置仓库table
		setStorageTable = this.createControl(ID_Table_SetStorage, SEditTable.class);
		//启动系统
		Button startSystemButton = this.createControl(ID_Button_StartSystem, Button.class);
		startSystemButton.addActionListener(new StartSystemButtonListener());
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		//初始化省市县
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		//初始数据
		initData();
	}

	/**
	 * 加载图标如果为空则显示默认图标
	 */
	private void initData(){
		CompanyInfo company = getContext().get(CompanyInfo.class);
		if(company != null){
			tenantId = company.getId();
			if(company.getLogo() != null && company.getLogo().length > 0){
				ImageDescriptor img = DataImageDescriptor.createImageDescriptor(company.getLogo(), logoLabel);
				logoLabel.setImage(img);
			}
			if(company.getSystemName() != null && company.getSystemName().length() > 0){
				logoLabel.setText(company.getSystemName());
			}
			titleText.setText(company.getSystemName());
			shortNameText.setText(company.getCompanyShortName());
			fullNameText.setText(company.getCompanyName());
			if(company.getProvince() != null){
				provinceList.setSelection(company.getProvince());
			}
			if(company.getCity() != null){
				cityList.setSelection(company.getCity());
			}
			if(company.getDistrict() != null){
				districtList.setSelection(company.getDistrict());
			}
			addressText.setText(company.getAddress());
			phoneText.setText(company.getLandLineNumber());
			faxText.setText(company.getFaxNumber());
			postCodeText.setText(company.getPostcode());
		}
	}

	/**
	 * 界面添加验证器
	 */
	private void addValidator(){
		registerNotEmptyValidator(shortNameText, "企业简称");
		registerNotEmptyValidator(fullNameText, "企业全称");
	}

	/**
	 * 返回配置向导按钮侦听器
	 */
	private class BackWizardClickListener implements MouseClickListener{

		public void click(MouseEvent e){
			//向上发送消息打开配置向导界面
			PageControllerInstance controllerInstance = new PageControllerInstance("ConfigurationWizardPage");
			getContext().bubbleMessage(new StepPageMessage("配置向导", controllerInstance));
		}

	}

	/**
	 * 上传微标按钮侦听器
	 */
	private class UploadButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			try{
				String fileName = fileImg.getFileName();
				if(CheckIsNull.isEmpty(fileName)){
					alert("请选择要上传的图片。");
					return;
				}
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if(!validateSuffix(suffix)){
					alert("图片格式不正确，请上传后缀名为：" + getSuffixs() + " 的文件。");
					return;
				}
				InputStream in = fileImg.getInputStream(fileImg.getFileName());
				final byte[] imgBytes = new byte[in.available()];
				in.read(imgBytes);
				if(imgBytes.length > MAX_LENGTH){
					alert("图片格式不正确，请传入30KB以下的图片。");
					in.close();
					return;
				}
				ImageDescriptor logoImg = DataImageDescriptor.createImageDescriptor(imgBytes, logoLabel);
				if(logoImg.getWidth() > MAX_WIDTH || logoImg.getHeight() > MAX_HEIGHT){
					alert("图片格式不正确，图片长度不能大于" + MAX_WIDTH + "像素，高度不能大于" + MAX_HEIGHT + "像素。");
					in.close();
					logoImg.dispose();
					return;
				}
				// 设置背景图片,预览功能
				logoLabel.setImage(logoImg);
				logoLabel.getParent().layout();
				logoData = imgBytes;
				in.close();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}

		/**
		 * 验证后缀，判断是否为图片
		 *
		 *@param suffix 后缀
		 *@return
		 */
		private boolean validateSuffix(String suffix){
			for(String string : suffixs){
				if(string.equals(suffix)){
					return true;
				}
			}
			return false;
		}

		/**
		 * 获得支持图片格式的后缀串
		 *
		 *@return
		 */
		private String getSuffixs(){
			StringBuilder buffer = new StringBuilder();
			for(String string : suffixs){
				buffer.append(",");
				buffer.append(string);
			}
			return buffer.toString().substring(1);
		}

	}

	/**
	 * 保存企业信息
	 */
	private void saveCompanyInfo(){
		// 调用任务处理器持久化数据
		UpdateCompanyInfoTask task = new UpdateCompanyInfoTask();
		task.setId(tenantId);
		task.setCompanyName(StringHelper.toTrimStr(fullNameText.getText()));
		task.setCompanyShortName(StringHelper.toTrimStr(shortNameText.getText()));
		task.setAddress(addressText.getText());
		task.setProvince(provinceList.getText());
		task.setCity(cityList.getText());
		task.setDistrict(districtList.getText());
		task.setPhone(phoneText.getText());
		task.setPostcode(postCodeText.getText());
		task.setFax(faxText.getText());
		if(logoData != null && logoData.length > 0){
			task.setLogo(logoData);
		}
		else{
			CompanyInfo company = getContext().get(CompanyInfo.class);
			if(company != null){
				task.setLogo(company.getLogo());
			}
		}
		task.setSystemName(titleText.getText());
		getContext().handle(task, UpdateCompanyInfoTask.Method.Update);
		// 通知上级页面，租户Logo和系统标题发生改变
		Display.getCurrent().getSituation().bubbleMessage(new MsgCompanyInfoChanged());
	}

	/**
	 * 保存用户
	 */
	private void saveUser(List<TempUserInfo> userList){
		if(CheckIsNull.isEmpty(userList)){
			return;
		}
		List<UpdateEmployeeInfoTask.Item> items = new ArrayList<UpdateEmployeeInfoTask.Item>();
		for(TempUserInfo user : userList){
			//仅保存新增
			if(user.isCreate()){
				UpdateEmployeeInfoTask.Item item = new UpdateEmployeeInfoTask.Item();
				item.setCreate(user.isCreate());
				item.setId(user.getId());
				item.setName(user.getName());
				item.setIdNumber(user.getIdentityNumber());
				item.setMobileNo(user.getMobile());
				item.setEmail(user.getEmail());
				item.setPosition(user.getJob());
				item.setDepartmentId(user.getDepartmentId());
				item.setRoles(user.getRoles());
				items.add(item);
			}
		}
		getContext().handle(new UpdateEmployeeInfoTask(items.toArray(new UpdateEmployeeInfoTask.Item[items.size()])));
	}

	/**
	 * 保存仓库
	 */
	private void saveStorage(List<TempStorageInfo> storageList){
		if(CheckIsNull.isEmpty(storageList)){
			return;
		}
		for(TempStorageInfo storage : storageList){
			StoreInfoTask task = new StoreInfoTask();
			task.setId(storage.getId());
			task.setName(storage.getName());
			task.setAddress(storage.getAddress());
			task.setProvinceCode(storage.getProvince());
			task.setCityCode(storage.getCity());
			task.setTownCode(storage.getDistrict());
			task.setConsignee(storage.getConsignee());
			task.setPostcode(storage.getPostcode());
			task.setMobileNo(storage.getMobile());
//			task.setLandLineNumber(storage.getPhone());
//			task.setSalesIds(getGuidArrByIdStr(storage.getSaleIds()));
			task.setKeeperIds(getGuidArrByIdStr(storage.getKeeperIds()));
			if(storage.isEnabled()){
				task.setStatus(StoreStatus.ENABLE);
			}
			else{
				task.setStatus(StoreStatus.DISABLED);
			}
			getContext().handle(task, StoreInfoTask.Method.Create);
		}
	}

	/**
	 * 启动系统按钮侦听器
	 */
	private class StartSystemButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//已经初始化企业信息配置
			Boolean hasInitCompany =
			        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_COMPANY));
			if(hasInitCompany){
				alert("本企业其他用户更新了配置向导数据，请重新登录！", new Runnable(){

					public void run(){
						getContext().bubbleMessage(new ReLoginMessage());
					}
				});
				return;
			}
			//
			confirm("请确认是否启用系统？", new Runnable(){

				public void run(){
					//验证企业信息
					if(!validateInput()){
						return;
					}
					//
					List<TempUserInfo> userList = WizardUtil.getUserInfoList(setUserTable);
					List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList(setStorageTable);
					//非直供模式时，至少存在一个已启用的仓库
					TenantInfo tenantInfo = getContext().get(TenantInfo.class);
					if(!tenantInfo.isDirectDelivery() && !isExistEnabledStorage(storageList)){
						alert("至少要包含一个启用状态的仓库！");
						return;
					}
					//保存企业信息
					saveCompanyInfo();
					//保存用户
					saveUser(userList);
					//保存仓库
					saveStorage(storageList);
					//租户系统参数，已经启动系统置为true
					SaveOrUpdateTenantsSysParamTask task =
					        new SaveOrUpdateTenantsSysParamTask(SysParamKey.HAS_INIT_START, Boolean.TRUE);
					getContext().handle(task);
					//发送短信
					EmployeeInfo employeeInfo = getContext().get(EmployeeInfo.class, tenantInfo.getId());
					getContext().handle(
					        new SendMsgTask(employeeInfo.getMobileNo(), StringHelper.toTrimStr(shortNameText.getText())
					                + "企业管理系统已经启用！", getContext().find(LoginInfo.class).getTenantId()));
					hint("系统启用成功！");
					//关闭窗口
					getContext().bubbleMessage(new MsgResponse(true));
				}
			});
		}

	}

	/**
	 * 判断是否存在已启用的仓库
	 */
	private boolean isExistEnabledStorage(List<TempStorageInfo> storageList){
		if(CheckIsNull.isEmpty(storageList)){
			return false;
		}
		for(TempStorageInfo item : storageList){
			if(item.isEnabled()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据ID字符串获得GUID数组
	 */
	private GUID[] getGuidArrByIdStr(String idStrs){
		if(CheckIsNull.isEmpty(idStrs)){
			return null;
		}
		String[] id = idStrs.split(";");
		GUID[] idArr = new GUID[id.length];
		for(int i = 0; i < id.length; i++){
			idArr[i] = GUID.valueOf(id[i]);
		}
		return idArr;
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
}
