package com.spark.psi.base.browser.station;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.PSIConstant;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.station.StationManagerSelectProcessor.StationManagerInfo;
import com.spark.psi.publish.base.station.entity.StationInfo;
import com.spark.psi.publish.base.station.task.StationInfoTask;
import com.spark.psi.publish.base.station.task.ValidateStationNameTask;

public class EditStationProcessor extends BaseFormPageProcessor {

	private StationInfo info;

	public void init(Situation context) {
		super.init(context);
		if (null != this.getArgument()) {
			GUID id = (GUID) this.getArgument();
			info = context.find(StationInfo.class, id);
		}
	}

	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_SaveAdd = "Button_SaveAdd";
	public final static String ID_Text_ShortName = "Text_ShortName";
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_Number = "Text_Number";
	public final static String ID_Text_Fax = "Text_Fax";
	public final static String ID_Text_WorkTel = "Text_WorkTel";
	public final static String ID_Text_Remark = "Text_Remark";
	public final static String ID_Text_ManageName = "Text_ManageName";
	public final static String ID_Text_ManageMobile = "Text_ManageMobile";
	public final static String ID_Text_ManageEmail = "Text_ManageEmail";
	public final static String ID_Text_ManagePersonNo = "Text_ManagePersonNo";
	public static final String ID_Label_AddKeeper = "Label_AddKeeper";

	private Button button_Save, Button_SaveAdd;
	private Text text_ShortName, text_Name, text_Address, text_Number, text_Fax, text_WorkTel, text_Remark,
			text_ManageName, text_ManageMobile, text_ManageEmail, text_ManagePersonNo;
	private LWComboList provinceList, cityList, districtList;

	private Label labelAddKeepers;

	@Override
	public void process(Situation context) {
		initControls();
		registerInputCannotNull();
		fillContrls();
		registerButtonAction();
	}

	private void registerInputCannotNull() {

		registerInputValidator(new TextInputValidator(text_ShortName, "站点简称不能为空！") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(text_Name, "站点全称不能为空！") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});

		registerInputValidator(new TextInputValidator(provinceList, provinceList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(cityList, cityList.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {

				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(districtList, districtList.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {

				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(text_Address, text_Address.getHint() + "不能为空！") {

			@Override
			protected boolean validateText(String text) {

				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(text_ManageName, "负责人不能为空！") {

			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(text_ShortName, "站点简称已经存在") {

			@Override
			protected boolean validateText(String text) {
				GUID id = GUID.emptyID;
				if (null != info) {
					id = info.getId();
				}
				ValidateStationNameTask validateTask = new ValidateStationNameTask(id, text);
				getContext().handle(validateTask, ValidateStationNameTask.Method.ShortName);
				return !validateTask.isExist();
			}
		});
		registerInputValidator(new TextInputValidator(text_Name, "站点全称已经存在") {

			@Override
			protected boolean validateText(String text) {
				GUID id = GUID.emptyID;
				if (null != info) {
					id = info.getId();
				}
				ValidateStationNameTask validateTask = new ValidateStationNameTask(id, text);
				getContext().handle(validateTask, ValidateStationNameTask.Method.FullName);
				return !validateTask.isExist();
			}
		});
	}

	/**
	 * 注册按钮监听器
	 */
	private void registerButtonAction() {
		labelAddKeepers.addMouseClickListener(new MouseClickListener() {

			public void click(MouseEvent e) {
				PageController pc = new PageController(StationManagerSelectProcessor.class,
						StationManagerSelectRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, text_ManageName.getData(), null, null);
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
				windowStyle.setSize(540, 360);
				MsgRequest request = new MsgRequest(pci, "指定负责人", windowStyle);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						StationManagerInfo info = (StationManagerInfo) returnValue;
						if (null == info) {
							return;
						}
						text_ManageName.setText(info.getName());
						text_ManageName.setData(info.getId());
						text_ManageMobile.setText(info.getMobile());
						text_ManagePersonNo.setText(info.getIdNo());
						text_ManageEmail.setText(info.getEmail());
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		this.button_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (doSave()) {
					MsgResponse response = new MsgResponse(true);
					getContext().bubbleMessage(response);
				}
			}
		});
		this.Button_SaveAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doSave()) {
					// 刷新新建页
					MsgResponse response = new MsgResponse(false);
					getContext().bubbleMessage(response);
					clearData();
				}
			}
		});
	}

	private void clearData() {
		this.text_Address.setText(null);
		this.text_Fax.setText(null);
		this.text_ManageEmail.setText(null);
		this.text_ManageMobile.setText(null);
		this.text_ManageName.setText(null);
		this.text_ManageName.setData(null);
		this.text_ManagePersonNo.setText(null);
		this.text_Name.setText(null);
		this.text_Number.setText(null);
		this.text_Number.setEnabled(null);
		this.text_Remark.setText(null);
		this.text_ShortName.setText(null);
		this.text_WorkTel.setText(null);
		this.provinceList.setSelection(null);
		provinceList.setSelection(PSIConstant.DefArea.provinceDefaut);
		cityList.setSelection(PSIConstant.DefArea.cityDefaut);
	}

	private boolean doSave() {
		if (!validateInput()) {
			return false;
		}
		StationInfoTask task = new StationInfoTask();
		if (info != null) {
			task.setId(info.getId());
		}
		task.setShortName(this.text_ShortName.getText());
		task.setAddress(this.text_Address.getText());
		task.setCity(this.cityList.getText());
		task.setProvince(this.provinceList.getText());
		task.setTown(this.districtList.getText());
		task.setFax(this.text_Fax.getText());
		task.setTelephone(this.text_WorkTel.getText());
		if (null != text_ManageName.getData()) {
			task.setManagerId(GUID.valueOf(text_ManageName.getData() + ""));
		}
		task.setManager(this.text_ManageName.getText());
		task.setManagerEmail(this.text_ManageEmail.getText());
		task.setManagerPersonId(this.text_ManagePersonNo.getText());
		task.setManagerPhone(this.text_ManageMobile.getText());
		task.setRemark(this.text_Remark.getText());
		task.setStationName(this.text_Name.getText());
		getContext().handle(task);
		return true;
	}

	/**
	 * 初始化控件
	 */
	private void initControls() {
		button_Save = this.createControl(ID_Button_Save, Button.class);
		Button_SaveAdd = this.createControl(ID_Button_SaveAdd, Button.class);
		text_Fax = this.createControl(ID_Text_Fax, Text.class);
		text_ShortName = this.createControl(ID_Text_ShortName, Text.class);
		text_Name = this.createControl(ID_Text_Name, Text.class);
		text_Number = this.createControl(ID_Text_Number, Text.class);
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		cityList = this.createControl(ID_List_City, LWComboList.class);
		districtList = this.createControl(ID_List_District, LWComboList.class);
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		text_Address = this.createControl(ID_Text_Address, Text.class);
		text_WorkTel = this.createControl(ID_Text_WorkTel, Text.class);
		text_ManagePersonNo = this.createControl(ID_Text_ManagePersonNo, Text.class);
		text_ManageEmail = this.createControl(ID_Text_ManageEmail, Text.class);
		text_ManageMobile = this.createControl(ID_Text_ManageMobile, Text.class);
		text_ManageName = this.createControl(ID_Text_ManageName, Text.class);
		text_Remark = this.createControl(ID_Text_Remark, Text.class);
		labelAddKeepers = this.createControl(ID_Label_AddKeeper, Label.class);
	}

	/**
	 * 编辑的情况下对控件赋值
	 */
	private void fillContrls() {
		if (info == null) {
			return;
		}
		this.text_Address.setText(info.getAddress());
		this.text_Fax.setText(info.getFax());
		this.text_ManageEmail.setText(info.getManagerEmail());
		this.text_ManageMobile.setText(info.getManagerPhone());
		this.text_ManageName.setText(info.getManager());
		this.text_ManageName.setData(info.getManagerId());
		this.text_ManagePersonNo.setText(info.getManagerPersonId());
		this.text_Name.setText(info.getStationName());
		this.text_Number.setText(info.getStationNo());
		this.text_Number.setEnabled(false);
		this.text_Remark.setText(info.getRemark());
		this.text_ShortName.setText(info.getShortName());
		this.text_WorkTel.setText(info.getTelephone());
		this.provinceList.setSelection(info.getProvince());
		this.cityList.setSelection(info.getCity());
		this.districtList.setSelection(info.getTown());
	}
}
