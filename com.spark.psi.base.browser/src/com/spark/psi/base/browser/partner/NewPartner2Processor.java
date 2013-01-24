package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.base.partner.task.UpdatePartnerTask;
import com.spark.psi.publish.base.partner.task.ValidatePartnerNameTask;

/**
 * 快速新增客户处理器
 */
public abstract class NewPartner2Processor extends BaseFormPageProcessor implements IDataProcessPrompt{

	public final static String ID_Text_ShortName = "Text_ShortName";
	public final static String ID_Text_FullName = "Text_FullName";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_Fax = "Text_Fax";
	public final static String ID_Text_ContactName = "Text_ContactName";
	public final static String ID_List_ContactSex = "List_ContactSex";
	public final static String ID_Text_ContactMobile = "Text_ContactMobile";
	public final static String ID_Text_ContactLandLine = "Text_ContactLandLine";
	public final static String ID_Text_ContactMail = "Text_ContactMail";

	public final static String ID_List_DeliveryProvince = "List_DeliveryProvince";
	public final static String ID_List_DeliveryCity = "List_DeliveryCity";
	public final static String ID_List_DeliveryDistrict = "List_DeliveryDistrict";
	public final static String ID_Text_DeliveryAddress = "Text_DeliveryAddress";
	public final static String ID_Text_DeliveryPostCode = "Text_DeliveryPostCode";
	public final static String ID_Text_Consignee = "Text_Consignee";
	public final static String ID_Text_ConsigneeMobileNo = "Text_ConsigneeMobileNo";
	public final static String ID_Text_ConsigneeLandLineNumber = "Text_LandLineNumber";

	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Cancel = "Button_Cancel";

	private Text shortNameText;
	private Text fullNameText;
	private LWComboList provinceList;
	private LWComboList cityList;
	private LWComboList districtList;
	private Text addressText;
	private Text postCodeText;
	private Text faxText;
	private Text contactNameText;
	private LWComboList contactSexList;
	private Text contactMobileText;
	private Text contactLandlineText;
	private Text contactMailText;
	private LWComboList deliveryProvinceList;
	private LWComboList deliveryCityList;
	private LWComboList deliveryDistrictList;
	private Text deliveryAddressText;
	private Text deliveryPostCodeText;
	private Text consigneeText;
	private Text consigneeMobileText;
	private Text consigneeLandlineText;

	@Override
	public void process(Situation situation) {
		shortNameText = this.createControl(ID_Text_ShortName, Text.class);
		fullNameText = this.createControl(ID_Text_FullName, Text.class);
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		cityList = this.createControl(ID_List_City, LWComboList.class);
		districtList = this.createControl(ID_List_District, LWComboList.class);
		addressText = this.createControl(ID_Text_Address, Text.class);
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		faxText = this.createControl(ID_Text_Fax, Text.class);
		contactNameText = this.createControl(ID_Text_ContactName, Text.class);
		contactSexList = this.createControl(ID_List_ContactSex,
				LWComboList.class);
		contactMobileText = this.createControl(ID_Text_ContactMobile,
				Text.class);
		contactLandlineText = this.createControl(ID_Text_ContactLandLine,
				Text.class);
		contactMailText = this.createControl(ID_Text_ContactMail, Text.class);
		deliveryProvinceList = this.createControl(ID_List_DeliveryProvince,
				LWComboList.class);
		deliveryCityList = this.createControl(ID_List_DeliveryCity,
				LWComboList.class);
		deliveryDistrictList = this.createControl(ID_List_DeliveryDistrict,
				LWComboList.class);
		deliveryAddressText = this.createControl(ID_Text_DeliveryAddress,
				Text.class);
		deliveryPostCodeText = this.createControl(ID_Text_DeliveryPostCode,
				Text.class);
		consigneeText = this.createControl(ID_Text_Consignee, Text.class);
		consigneeMobileText = this.createControl(ID_Text_ConsigneeMobileNo,
				Text.class);
		consigneeLandlineText = this.createControl(
				ID_Text_ConsigneeLandLineNumber, Text.class);

		//
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		PSIProcessorUtils.initAreaSource(deliveryProvinceList,
				deliveryCityList, deliveryDistrictList);
		contactSexList.getList().setSource(new EnumEntitySource(EnumType.Sex));
		contactSexList.getList().setInput("");

		//
		registerNotEmptyValidator(shortNameText, getPartnerType() + "简称");
		registerNotEmptyValidator(fullNameText, getPartnerType() + "全称");
		registerInputValidator(new TextInputValidator(provinceList,
				provinceList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationAdderssFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(cityList,
				cityList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationAdderssFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(districtList,
				districtList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationAdderssFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(addressText,
				addressText.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationAdderssFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(postCodeText,
				postCodeText.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationAdderssFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		//
		registerInputValidator(new TextInputValidator(contactNameText,
				contactNameText.getHint() + "不能为空！") {

			protected boolean validateText(String text) {
				if (!isValidationContactFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		//
		registerInputValidator(new TextInputValidator(deliveryProvinceList,
				"收货地址的" + deliveryProvinceList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(deliveryCityList, "收货地址的"
				+ deliveryCityList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(deliveryDistrictList,
				"收货地址的" + deliveryDistrictList.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(deliveryAddressText,
				"收货地址的" + deliveryAddressText.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(deliveryPostCodeText,
				"收货地址的" + deliveryPostCodeText.getHint() + "不能为空！") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryFields())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(shortNameText,
				getPartnerType() + "简称已经存在") {
			@Override
			protected boolean validateText(String text) {
				ValidatePartnerNameTask.Method shortMethod;
				if (getPartnerType().equals("客户")) {
					shortMethod = ValidatePartnerNameTask.Method.CustomerShortName;
				} else {
					shortMethod = ValidatePartnerNameTask.Method.SupplierShortName;
				}
				ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(
						GUID.emptyID, text);
				getContext().handle(validateTask, shortMethod);
				return !validateTask.isExist();
			}
		});
		registerInputValidator(new TextInputValidator(fullNameText,
				getPartnerType() + "全称已经存在") {

			@Override
			protected boolean validateText(String text) {
				ValidatePartnerNameTask.Method fullMethod;
				if (getPartnerType().equals("客户")) {
					fullMethod = ValidatePartnerNameTask.Method.CustomerName;
				} else {
					fullMethod = ValidatePartnerNameTask.Method.SupplierName;
				}
				ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(
						GUID.emptyID, text);
				getContext().handle(validateTask, fullMethod);
				return !validateTask.isExist();
			}
		});

		//
		this.createControl(ID_Button_Save, Button.class, JWT.NONE)
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						processData();
					}
				});
		this.createControl(ID_Button_Cancel, Button.class, JWT.NONE)
				.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						getContext().bubbleMessage(new MsgCancel());
					}
				});
	}

	/**
	 * 是否验证地址，如果省、市、县、详细地址、邮编任意一项不为空，则需要地址相关字段
	 * 
	 * @return boolean
	 */
	private boolean isValidationAdderssFields() {
		return !StringUtils.isEmpty(provinceList.getText())
				|| !StringUtils.isEmpty(cityList.getText())
				|| !StringUtils.isEmpty(districtList.getText())
				|| !StringUtils.isEmpty(addressText.getText())
				|| !StringUtils.isEmpty(postCodeText.getText());
	}

	/**
	 * 是否验证联系人，如果性别、手机、固话、邮箱一项不为空，则需要联系人
	 * 
	 * @return boolean
	 */
	private boolean isValidationContactFields() {
		return !StringUtils.isEmpty(contactSexList.getText())
				|| !StringUtils.isEmpty(contactMobileText.getText())
				|| !StringUtils.isEmpty(contactLandlineText.getText())
				|| !StringUtils.isEmpty(contactMailText.getText());
	}

	/**
	 * 是否验证收货地址字段，如果省、市、县、详细地址、邮编任意一项不为空，则需要地址相关字段
	 * 
	 * @return boolean
	 */
	private boolean isValidationDeliveryFields() {
		return !StringUtils.isEmpty(deliveryProvinceList.getText())
				|| !StringUtils.isEmpty(deliveryCityList.getText())
				|| !StringUtils.isEmpty(deliveryDistrictList.getText())
				|| !StringUtils.isEmpty(deliveryAddressText.getText())
				|| !StringUtils.isEmpty(deliveryPostCodeText.getText())
				|| !StringUtils.isEmpty(consigneeText.getText())
				|| !StringUtils.isEmpty(consigneeMobileText.getText())
				|| !StringUtils.isEmpty(consigneeLandlineText.getText());
	}

	protected final void fillTaskData(UpdatePartnerTask<?> task) {
		task.setShortName(shortNameText.getText());
		task.setName(fullNameText.getText());
		task.setAddress(addressText.getText());
//		task.setPostCode(postCodeText.getText());
//		task.setProvince(provinceList.getText());
//		task.setCity(cityList.getText());
//		task.setDistrict(districtList.getText());
//		task.setContactEmail(contactMailText.getText());
//		task.setContactLandLineNumber(contactLandlineText.getText());
//		task.setContactMobileNo(contactMobileText.getText());
//		task.setContactName(contactNameText.getText());
//		task.setContactSex(contactSexList.getText()); //
//		task.setFaxNumber(faxText.getText());
//		if (!StringUtils.isEmpty(contactNameText.getText())) {
//			UpdatePartnerTask.ContactPersonItem contactItem = new UpdatePartnerTask.ContactPersonItem();
//			contactItem.setName(contactNameText.getText());
//			contactItem.setEmail(contactMailText.getText());
//			contactItem.setLandLineNumber(contactLandlineText.getText());
//			contactItem.setMobileNo(contactMobileText.getText());
//			contactItem.setSex(contactSexList.getText());
//			contactItem.setMain(true);
//			task.setContactPersons(new UpdatePartnerTask.ContactPersonItem[] { contactItem });
//		}
//		if (!StringUtils.isEmpty(deliveryAddressText.getText())) {
//			UpdatePartnerTask.DeliveryAddressItem addressItem = new UpdatePartnerTask.DeliveryAddressItem();
//			addressItem.setAddress(deliveryAddressText.getText());
//			addressItem.setPostCode(deliveryPostCodeText.getText());
//			addressItem.setProvince(deliveryProvinceList.getText());
//			addressItem.setCity(deliveryCityList.getText());
//			addressItem.setDistrict(deliveryDistrictList.getText());
//			addressItem.setName(consigneeText.getText());
//			addressItem.setLandLineNumber(consigneeLandlineText.getText());
//			addressItem.setMobileNo(consigneeMobileText.getText());
//			task.setDeliveryAddresses(new UpdatePartnerTask.DeliveryAddressItem[] { addressItem });
//		}
	}

	protected abstract String getPartnerType();

	protected abstract GUID handleSave();
	
	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		if (!validateInput()) {
			return false;
		}
		//
		getContext().bubbleMessage(new MsgResponse(true,handleSave())); 
		return true;
	}

}
