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
 * <p>新增或编辑仓库页面处理器</p> 
 */

public class EditStoreProcessor extends BaseFormPageProcessor{
	/**
	 * 控件ID
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
	 * 控件
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
	 * 初始化界面组件
	 */
	private void initUIComponent(){
		//仓库名称
		nameText = this.createControl(ID_Text_Name, Text.class);
		//固话
		phoneText = this.createControl(ID_Text_Phone, Text.class);
		//收货人
		consigneeText = this.createControl(ID_Text_Consignee, Text.class);
		//手机
		mobileText = this.createControl(ID_Text_Mobile, Text.class);
		//省
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		//市
		cityList = this.createControl(ID_List_City, LWComboList.class);
		//县
		districtList = this.createControl(ID_List_District, LWComboList.class);
		//保存
		Button saveButton = this.createControl(ID_Button_Save, Button.class);
		saveButton.addActionListener(new SaveButtonListener());
		//详细地址
		addressText = this.createControl(ID_Text_Address, Text.class);
		//邮编
		postcodeText = this.createControl(ID_Text_Postcode, Text.class);
		//库管员
		keeperText = this.createControl(ID_Text_Keeper, Text.class);
		this.createControl(ID_Label_AddKeeper, Label.class).addMouseClickListener(new AddKeeperListener());
		//零售员
		salesText = this.createControl(ID_Text_Sales, Text.class);
		this.createControl(ID_Label_AddSales, Label.class).addMouseClickListener(new AddSalesListener());
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//初始化省市县
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		//
		if(this.getArgument() != null){
			tempStorageInfo = (TempStorageInfo)this.getArgument();
			//初始化
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
	 * 初始化仓库
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
	 * 界面添加验证器
	 */
	private void addValidator(){
		registerNotEmptyValidator(nameText, "仓库名称");
		registerNotEmptyValidator(consigneeText, "收货人");
		registerNotEmptyValidator(provinceList, "省份");
		registerNotEmptyValidator(cityList, "市");
		registerNotEmptyValidator(districtList, "县");
		registerNotEmptyValidator(addressText, "详细地址");
		registerNotEmptyValidator(postcodeText, "邮编");
		registerNotEmptyValidator(keeperText, "库管员");
		//仓库名称重复
		registerInputValidator(new TextInputValidator(nameText, "仓库名称重复"){

			@Override
			protected boolean validateText(String text){
				//检查列表
				List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList(storageTable);
				Map<String, TempStorageInfo> map = WizardUtil.getStorageNameMap(storageList);
				TempStorageInfo storageInfo = map.get(StringHelper.toTrimStr(nameText.getText()));
				if(tempStorageInfo == null && storageInfo != null){ //新增
					return false;
				}else if(tempStorageInfo != null && storageInfo != null && !tempStorageInfo.getId().equals(storageInfo.getId())){
					//编辑
					return false;
				}
				return true;
			}
		});
		//仓库名称不能包含特殊字符
		registerInputValidator(new TextInputValidator(nameText, "仓库名称中不能保存特殊字符"){

			@Override
			protected boolean validateText(String text){
				if(text == null) return true;
				String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(text);
				return !m.find();
			}
		});

	}

	/**
	 * 选择库管员侦听器
	 */
	private class AddKeeperListener implements MouseClickListener{

		public void click(MouseEvent e){
			PageController pc = new PageController(QuickKeepersSelectProcessor.class, StoreEmployeeSelectRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, (String)keeperText.getData(), tempStorageInfo.getId(), userTable);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
			windowStyle.setSize(540, 360);
			MsgRequest request = new MsgRequest(pci, "选择库管人员", windowStyle);
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
	 * 选择零售人员侦听器
	 */
	private class AddSalesListener implements MouseClickListener{

		public void click(MouseEvent e){
			PageController pc = new PageController(QuickSalerSelectProcessor.class, StoreEmployeeSelectRender.class);
			PageControllerInstance instance = new PageControllerInstance(pc, (String)salesText.getData(), tempStorageInfo.getId(), userTable, storageTable);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
			windowStyle.setSize(540, 360);
			MsgRequest request = new MsgRequest(instance, "选择零售人员", windowStyle);
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
	 * 保存按钮侦听器
	 */
	private class SaveButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//验证数据
			if(!validateInput()){
				return;
			}
			getContext().bubbleMessage(new MsgResponse(true, buildTempStoreInfo()));
		}

	}
	
	/**
	 * 组装临时仓库对像
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
