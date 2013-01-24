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
 * <p>�������ý��洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-25
 */

public class QuickConfigurationProcessor extends BaseFormPageProcessor implements IDataProcessPrompt{

	// Ĭ��logo
	public final static ImageDescriptor DEFAULF_LOGO = BaseImages.getImage("/images/config/logo.png");
	// ϵͳ����ɫ
	public final static ImageDescriptor DEFAULF_BACK = BaseImages.getImage("/images/config/top-bg01.gif");
	// �����ϴ�ͼƬ�ĸ�ʽ
	static final String[] suffixs = {"jpg", "png", "gif"};
	// LOGOͼƬ�������ֵ
	final static int MAX_LENGTH = 30000;
	final static int MAX_WIDTH = 150;
	final static int MAX_HEIGHT = 50;

	/**
	 * �ؼ�ID
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
	 * �ؼ�
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
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context){
		// �������
		createUIComponent();
		//�����֤��
		addValidator();
		//ע����Ϣ
		regMessageListener();
	}

	/**
	 * ע����Ϣ
	 */
	private void regMessageListener(){
		//��֤�û��Ƿ�ʹ��
		getContext().regMessageListener(ValidateUserUsedUpMessage.class,
		        new MessageListener<ValidateUserUsedUpMessage>(){

			        public void onMessage(Situation context, ValidateUserUsedUpMessage message,
			                MessageTransmitter<ValidateUserUsedUpMessage> transmitter)
			        {
				        //����ת����Ϣ
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
	 * �������
	 */
	private void createUIComponent(){
		//���������򵼰�ť
		Label backWizardIcon = this.createControl(ID_Icon_BackWizard, Label.class);
		backWizardIcon.addMouseClickListener(new BackWizardClickListener());
		Label backWizard = this.createControl(ID_Label_BackWizard, Label.class);
		backWizard.addMouseClickListener(new BackWizardClickListener());
		//��˾���
		shortNameText = this.createControl(ID_Text_ShortName, Text.class);
		//ȫ��
		fullNameText = this.createControl(ID_Text_FullName, Text.class);
		//��ַ
		addressText = this.createControl(ID_Text_Address, Text.class);
		//�ʱ�
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		//�绰
		phoneText = this.createControl(ID_Text_Phone, Text.class);
		//����
		faxText = this.createControl(ID_Text_Fax, Text.class);
		//ʡ
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		//��
		cityList = this.createControl(ID_List_City, LWComboList.class);
		//��
		districtList = this.createControl(ID_List_District, LWComboList.class);
		//Logo
		logoLabel = this.createControl(ID_Label_Logo, Label.class);
		// �ļ��ϴ�
		fileImg = this.createControl(ID_FileChooser_Img, FileChooser.class, JWT.NONE);
		Button uploadButton = this.createControl(ID_Button_UploadImg, Button.class, JWT.NONE);
		uploadButton.addActionListener(new UploadButtonListener());
		fileImg.setRelativeTarget(uploadButton, ActionListener.class);
		//�޸ı���
		titleText = this.createControl(ID_Text_Title, Text.class);
		Button modifyTitleButton = this.createControl(ID_Button_ModifyTitle, Button.class);
		modifyTitleButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub

			}
		});
		//�����û�table
		setUserTable = this.createControl(ID_Table_SetUser, SEditTable.class);
		//���òֿ�table
		setStorageTable = this.createControl(ID_Table_SetStorage, SEditTable.class);
		//����ϵͳ
		Button startSystemButton = this.createControl(ID_Button_StartSystem, Button.class);
		startSystemButton.addActionListener(new StartSystemButtonListener());
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		//��ʼ��ʡ����
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		//��ʼ����
		initData();
	}

	/**
	 * ����ͼ�����Ϊ������ʾĬ��ͼ��
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
	 * ���������֤��
	 */
	private void addValidator(){
		registerNotEmptyValidator(shortNameText, "��ҵ���");
		registerNotEmptyValidator(fullNameText, "��ҵȫ��");
	}

	/**
	 * ���������򵼰�ť������
	 */
	private class BackWizardClickListener implements MouseClickListener{

		public void click(MouseEvent e){
			//���Ϸ�����Ϣ�������򵼽���
			PageControllerInstance controllerInstance = new PageControllerInstance("ConfigurationWizardPage");
			getContext().bubbleMessage(new StepPageMessage("������", controllerInstance));
		}

	}

	/**
	 * �ϴ�΢�갴ť������
	 */
	private class UploadButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			try{
				String fileName = fileImg.getFileName();
				if(CheckIsNull.isEmpty(fileName)){
					alert("��ѡ��Ҫ�ϴ���ͼƬ��");
					return;
				}
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if(!validateSuffix(suffix)){
					alert("ͼƬ��ʽ����ȷ�����ϴ���׺��Ϊ��" + getSuffixs() + " ���ļ���");
					return;
				}
				InputStream in = fileImg.getInputStream(fileImg.getFileName());
				final byte[] imgBytes = new byte[in.available()];
				in.read(imgBytes);
				if(imgBytes.length > MAX_LENGTH){
					alert("ͼƬ��ʽ����ȷ���봫��30KB���µ�ͼƬ��");
					in.close();
					return;
				}
				ImageDescriptor logoImg = DataImageDescriptor.createImageDescriptor(imgBytes, logoLabel);
				if(logoImg.getWidth() > MAX_WIDTH || logoImg.getHeight() > MAX_HEIGHT){
					alert("ͼƬ��ʽ����ȷ��ͼƬ���Ȳ��ܴ���" + MAX_WIDTH + "���أ��߶Ȳ��ܴ���" + MAX_HEIGHT + "���ء�");
					in.close();
					logoImg.dispose();
					return;
				}
				// ���ñ���ͼƬ,Ԥ������
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
		 * ��֤��׺���ж��Ƿ�ΪͼƬ
		 *
		 *@param suffix ��׺
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
		 * ���֧��ͼƬ��ʽ�ĺ�׺��
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
	 * ������ҵ��Ϣ
	 */
	private void saveCompanyInfo(){
		// �������������־û�����
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
		// ֪ͨ�ϼ�ҳ�棬�⻧Logo��ϵͳ���ⷢ���ı�
		Display.getCurrent().getSituation().bubbleMessage(new MsgCompanyInfoChanged());
	}

	/**
	 * �����û�
	 */
	private void saveUser(List<TempUserInfo> userList){
		if(CheckIsNull.isEmpty(userList)){
			return;
		}
		List<UpdateEmployeeInfoTask.Item> items = new ArrayList<UpdateEmployeeInfoTask.Item>();
		for(TempUserInfo user : userList){
			//����������
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
	 * ����ֿ�
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
	 * ����ϵͳ��ť������
	 */
	private class StartSystemButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//�Ѿ���ʼ����ҵ��Ϣ����
			Boolean hasInitCompany =
			        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_COMPANY));
			if(hasInitCompany){
				alert("����ҵ�����û����������������ݣ������µ�¼��", new Runnable(){

					public void run(){
						getContext().bubbleMessage(new ReLoginMessage());
					}
				});
				return;
			}
			//
			confirm("��ȷ���Ƿ�����ϵͳ��", new Runnable(){

				public void run(){
					//��֤��ҵ��Ϣ
					if(!validateInput()){
						return;
					}
					//
					List<TempUserInfo> userList = WizardUtil.getUserInfoList(setUserTable);
					List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList(setStorageTable);
					//��ֱ��ģʽʱ�����ٴ���һ�������õĲֿ�
					TenantInfo tenantInfo = getContext().get(TenantInfo.class);
					if(!tenantInfo.isDirectDelivery() && !isExistEnabledStorage(storageList)){
						alert("����Ҫ����һ������״̬�Ĳֿ⣡");
						return;
					}
					//������ҵ��Ϣ
					saveCompanyInfo();
					//�����û�
					saveUser(userList);
					//����ֿ�
					saveStorage(storageList);
					//�⻧ϵͳ�������Ѿ�����ϵͳ��Ϊtrue
					SaveOrUpdateTenantsSysParamTask task =
					        new SaveOrUpdateTenantsSysParamTask(SysParamKey.HAS_INIT_START, Boolean.TRUE);
					getContext().handle(task);
					//���Ͷ���
					EmployeeInfo employeeInfo = getContext().get(EmployeeInfo.class, tenantInfo.getId());
					getContext().handle(
					        new SendMsgTask(employeeInfo.getMobileNo(), StringHelper.toTrimStr(shortNameText.getText())
					                + "��ҵ����ϵͳ�Ѿ����ã�", getContext().find(LoginInfo.class).getTenantId()));
					hint("ϵͳ���óɹ���");
					//�رմ���
					getContext().bubbleMessage(new MsgResponse(true));
				}
			});
		}

	}

	/**
	 * �ж��Ƿ���������õĲֿ�
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
	 * ����ID�ַ������GUID����
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
}
