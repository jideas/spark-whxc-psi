package com.spark.psi.base.browser.start;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.store.StoreEmployeeSelectRender;

/**
 * <p>������༭�ֿ�ҳ�洦����</p> 
 */

public class EditStoreProcessor extends BaseFormPageProcessor{
	/**
	 * �ؼ�ID
	 */
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Text_Phone = "Text_Phone";
	public final static String ID_Text_Consignee = "Text_Consignee";
	public final static String ID_Text_Mobile = "Text_Mobile";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_Postcode = "Text_Postcode";
	public final static String ID_Text_Keeper = "Text_Keeper";
	public final static String ID_Label_AddKeeper = "Label_AddKeeper";
	public final static String ID_Text_Sales = "Text_Sales";
	public final static String ID_Label_AddSales = "Label_AddSales";
	public final static String ID_Button_Save = "Button_Save";

	/**
	 * �ؼ�
	 */
	private Text nameText;
	private Text phoneText;
	private Text consigneeText;
	private Text mobileText;
	private LWComboList provinceList;
	private LWComboList cityList;
	private LWComboList districtList;
	private Text addressText;
	private Text postcodeText;
	private Text keeperText;
	private Text salesText;
	//
	private TempStorageInfo tempStorageInfo;
	private SEditTable userTable;
	private SEditTable storageTable;

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
	 * ��ʼ���������
	 */
	private void initUIComponent(){
		//�ֿ�����
		nameText = this.createControl(ID_Text_Name, Text.class);
		//�̻�
		phoneText = this.createControl(ID_Text_Phone, Text.class);
		//�ջ���
		consigneeText = this.createControl(ID_Text_Consignee, Text.class);
		//�ֻ�
		mobileText = this.createControl(ID_Text_Mobile, Text.class);
		//ʡ
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		//��
		cityList = this.createControl(ID_List_City, LWComboList.class);
		//��
		districtList = this.createControl(ID_List_District, LWComboList.class);
		//����
		Button saveButton = this.createControl(ID_Button_Save, Button.class);
		saveButton.addActionListener(new SaveButtonListener());
		//��ϸ��ַ
		addressText = this.createControl(ID_Text_Address, Text.class);
		//�ʱ�
		postcodeText = this.createControl(ID_Text_Postcode, Text.class);
		//���Ա
		keeperText = this.createControl(ID_Text_Keeper, Text.class);
		this.createControl(ID_Label_AddKeeper, Label.class).addMouseClickListener(new AddKeeperListener());
		//����Ա
		salesText = this.createControl(ID_Text_Sales, Text.class);
		this.createControl(ID_Label_AddSales, Label.class).addMouseClickListener(new AddSalesListener());
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//��ʼ��ʡ����
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		//
		if(this.getArgument() != null){
			tempStorageInfo = (TempStorageInfo)this.getArgument();
			//��ʼ��
			initStorage(tempStorageInfo);
		}else{
			tempStorageInfo = new TempStorageInfo();
			tempStorageInfo.setId(getContext().newRECID());
			tempStorageInfo.setEnabled(Boolean.FALSE);
		}
		//
		userTable = (SEditTable)this.getArgument2();
		//
		storageTable = (SEditTable)this.getArgument3();
	}
	
	/**
	 * ��ʼ���ֿ�
	 */
	public void initStorage(TempStorageInfo tempStorageInfo){
		nameText.setText(tempStorageInfo.getName());
		phoneText.setText(tempStorageInfo.getPhone());
		consigneeText.setText(tempStorageInfo.getConsignee());
		mobileText.setText(tempStorageInfo.getMobile());
		provinceList.setSelection(tempStorageInfo.getProvince());
		cityList.setSelection(tempStorageInfo.getCity());
		districtList.setSelection(tempStorageInfo.getDistrict());
		addressText.setText(tempStorageInfo.getAddress());
		postcodeText.setText(tempStorageInfo.getPostcode());
		keeperText.setText(tempStorageInfo.getKeeperNames());
		keeperText.setData(tempStorageInfo.getKeeperIds());
		salesText.setText(tempStorageInfo.getSaleNames());
		salesText.setData(tempStorageInfo.getSaleIds());
	}

	/**
	 * ���������֤��
	 */
	private void addValidator(){
		registerNotEmptyValidator(nameText, "�ֿ�����");
		registerNotEmptyValidator(consigneeText, "�ջ���");
		registerNotEmptyValidator(provinceList, "ʡ��");
		registerNotEmptyValidator(cityList, "��");
		registerNotEmptyValidator(districtList, "��");
		registerNotEmptyValidator(addressText, "��ϸ��ַ");
		registerNotEmptyValidator(postcodeText, "�ʱ�");
		registerNotEmptyValidator(keeperText, "���Ա");
		//�ֿ������ظ�
		registerInputValidator(new TextInputValidator(nameText, "�ֿ������ظ�"){

			@Override
			protected boolean validateText(String text){
				//����б�
				List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList(storageTable);
				Map<String, TempStorageInfo> map = WizardUtil.getStorageNameMap(storageList);
				TempStorageInfo storageInfo = map.get(StringHelper.toTrimStr(nameText.getText()));
				if(tempStorageInfo == null && storageInfo != null){ //����
					return false;
				}else if(tempStorageInfo != null && storageInfo != null && !tempStorageInfo.getId().equals(storageInfo.getId())){
					//�༭
					return false;
				}
				return true;
			}
		});
		//�ֿ����Ʋ��ܰ��������ַ�
		registerInputValidator(new TextInputValidator(nameText, "�ֿ������в��ܱ��������ַ�"){

			@Override
			protected boolean validateText(String text){
				if(text == null) return true;
				String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(text);
				return !m.find();
			}
		});

	}

	/**
	 * ѡ����Ա������
	 */
	private class AddKeeperListener implements MouseClickListener{

		public void click(MouseEvent e){
			PageController pc = new PageController(QuickKeepersSelectProcessor.class, StoreEmployeeSelectRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, (String)keeperText.getData(), tempStorageInfo.getId(), userTable);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
			windowStyle.setSize(540, 360);
			MsgRequest request = new MsgRequest(pci, "ѡ������Ա", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					keeperText.setText((String)returnValue2);
					keeperText.setData((String)returnValue);
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	/**
	 * ѡ��������Ա������
	 */
	private class AddSalesListener implements MouseClickListener{

		public void click(MouseEvent e){
			PageController pc = new PageController(QuickSalerSelectProcessor.class, StoreEmployeeSelectRender.class);
			PageControllerInstance instance = new PageControllerInstance(pc, (String)salesText.getData(), tempStorageInfo.getId(), userTable, storageTable);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
			windowStyle.setSize(540, 360);
			MsgRequest request = new MsgRequest(instance, "ѡ��������Ա", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					salesText.setText((String)returnValue2);
					salesText.setData((String)returnValue);
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	/**
	 * ���水ť������
	 */
	private class SaveButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//��֤����
			if(!validateInput()){
				return;
			}
			getContext().bubbleMessage(new MsgResponse(true, buildTempStoreInfo()));
		}

	}
	
	/**
	 * ��װ��ʱ�ֿ����
	 */
	private TempStorageInfo buildTempStoreInfo(){
		tempStorageInfo.setName(StringHelper.toTrimStr(nameText.getText()));
		tempStorageInfo.setPhone(StringHelper.toTrimStr(phoneText.getText()));
		tempStorageInfo.setConsignee(StringHelper.toTrimStr(consigneeText.getText()));
		tempStorageInfo.setMobile(StringHelper.toTrimStr(mobileText.getText()));
		tempStorageInfo.setProvince(StringHelper.toTrimStr(provinceList.getText()));
		tempStorageInfo.setCity(StringHelper.toTrimStr(cityList.getText()));
		tempStorageInfo.setDistrict(StringHelper.toTrimStr(districtList.getText()));
		tempStorageInfo.setAddress(StringHelper.toTrimStr(addressText.getText()));
		tempStorageInfo.setPostcode(StringHelper.toTrimStr(postcodeText.getText()));
		tempStorageInfo.setKeeperIds(StringHelper.toTrimStr(keeperText.getData()));
		tempStorageInfo.setKeeperNames(StringHelper.toTrimStr(keeperText.getText()));
		tempStorageInfo.setSaleIds(StringHelper.toTrimStr(salesText.getData()));
		tempStorageInfo.setSaleNames(StringHelper.toTrimStr(salesText.getText()));
		return tempStorageInfo;
	}
}
