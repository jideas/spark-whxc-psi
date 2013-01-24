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
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.partner.task.ValidatePartnerNameTask;

/**
 * �ͻ���Ӧ����Ϣ������
 */
public abstract class PartnerInfoProcessor extends BaseFormPageProcessor implements IDataProcessPrompt {

	public final static String ID_Text_ShortName = "Text_ShortName";
	public final static String ID_Text_FullName = "Text_FullName";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_LandLineNumber = "Text_LineNumber";
	public final static String ID_Text_Number = "Text_Number";
	public final static String ID_Text_Fax = "Text_Fax";
	public final static String ID_Text_TaxRate = "Text_TaxRate";

	public final static String ID_Text_AccountBank = "Text_AccountBank";
	public final static String ID_Text_AccountName = "Text_AccountName";
	public final static String ID_Text_AccountNumber = "Text_AccountNumber";
	public final static String ID_Text_TaxNumber = "Text_TaxNumber";
	public final static String ID_Text_CreditAmount = "Text_CreditAmount";
	public final static String ID_Text_CreditDay = "Text_ID_Text_CreditDay"; 
	public final static String ID_Text_RemindDay = "Text_RemindDay";

	public final static String ID_Text_ContactName = "Text_ContactName";
	public final static String ID_List_ContactSex = "List_ContactSex";
	public final static String ID_Text_ContactMobileNumber = "Text_ContactMobileNumber";
	public final static String ID_Text_ContactLandLineNumber = "Text_ContactLandLineNumber";
	public final static String ID_Text_ContactMail = "Text_ContactMail";

	public final static String ID_Text_Memo = "Text_Memo";

	public final static String ID_Button_Save = "Button_Save";

	public static final String ID_Area_OtherInfo = "Area_OtherInfo";

	/**
	 * ���
	 */
	protected Text shortNameText;

	/**
	 * ȫ��
	 */
	protected Text fullNameText;

	/**
	 * ���
	 */
	protected Text numberText;
	/**
	 * ʡ
	 */
	protected LWComboList provinceList;

	/**
	 * ��
	 */
	protected LWComboList cityList;

	/**
	 * ��
	 */
	protected LWComboList districtList;

	/**
	 * ��ϸ��ַ
	 */
	protected Text addressText;

	/**
	 * �ʱ�
	 */
	protected Text postCodeText;

	/**
	 * 
	 * �̶��绰
	 */
	protected Text landLineNumberText;

	/**
	 * ����
	 */
	protected Text faxText;

	/**
	 * ��������
	 */
	protected Text accountBankText;

	/**
	 * �˺�����
	 */
	protected Text accountNameText;

	/**
	 * �˺�
	 */
	protected Text accountNumberText;

	/**
	 * ��ֵ˰����
	 */
	protected Text taxNumberText, taxRate;

	/**
	 * ���ö��
	 */
	protected SNumberText creditAmountText;

	/**
	 * ��������
	 */
	protected Text creditDayText;

	/**
	 * ������������
	 */
	protected Text remindDayText;
	/**
	 * ��ϵ������
	 */
	protected Text contactNameText;
	/**
	 * ��ϵ���Ա�
	 */
	protected LWComboList contactSexList;
	/**
	 * ��ϵ���ֻ�����
	 */
	protected Text contactMobileNumberText;
	/**
	 * ��ϵ�˹̻�
	 */
	protected Text contactLandLineNumberText;
	/**
	 * ��ϵ������
	 */
	protected Text contactEmailText;

	/**
	 * ��ע
	 */
	protected Text memoText;

	protected GUID id = GUID.emptyID;

	@Override
	public void process(Situation situation) {
		final LoginInfo login = situation.find(LoginInfo.class);
		shortNameText = this.createControl(ID_Text_ShortName, Text.class);
		fullNameText = this.createControl(ID_Text_FullName, Text.class);
		numberText = this.createControl(ID_Text_Number, Text.class);
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		cityList = this.createControl(ID_List_City, LWComboList.class);
		districtList = this.createControl(ID_List_District, LWComboList.class);
		addressText = this.createControl(ID_Text_Address, Text.class);
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		landLineNumberText = this.createControl(ID_Text_LandLineNumber, Text.class);
		faxText = this.createControl(ID_Text_Fax, Text.class);
		taxRate = this.createControl(ID_Text_TaxRate, Text.class);
		accountBankText = this.createControl(ID_Text_AccountBank, Text.class);
		accountNameText = this.createControl(ID_Text_AccountName, Text.class);
		accountNumberText = this.createControl(ID_Text_AccountNumber, Text.class);
		taxNumberText = this.createControl(ID_Text_TaxNumber, Text.class);
		creditAmountText = this.createControl(ID_Text_CreditAmount, SNumberText.class);
		creditDayText = this.createControl(ID_Text_CreditDay, Text.class);
		remindDayText = this.createControl(ID_Text_RemindDay, Text.class);
		contactNameText = this.createControl(ID_Text_ContactName, Text.class);
		contactSexList = this.createControl(ID_List_ContactSex, LWComboList.class);
		contactMobileNumberText = this.createControl(ID_Text_ContactMobileNumber, Text.class);
		contactLandLineNumberText = this.createControl(ID_Text_ContactLandLineNumber, Text.class);
		contactEmailText = this.createControl(ID_Text_ContactMail, Text.class);
		contactSexList.getList().setSource(new EnumEntitySource(EnumType.Sex));
		contactSexList.getList().setInput("");
		// developeTypeList = this.createControl(ID_List_DevelopeType,
		// LWComboList.class);
		// websiteText = this.createControl(ID_Text_WebSite, Text.class);
		// industyTypeList = this.createControl(ID_List_IndustryType,
		// LWComboList.class);
		// scaleTypeList = this
		// .createControl(ID_List_ScaleType, LWComboList.class);
		memoText = this.createControl(ID_Text_Memo, Text.class);

		//
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);

		// developeTypeList
		// .getList()
		// .setSource(
		// new EnumEntitySource(
		// getPartnerType().equals("�ͻ�") ? EnumType.CustomerDevelopeType
		// : EnumType.SupplierDevelopeType));
		// developeTypeList.getList().setInput("");
		// scaleTypeList.getList().setSource(
		// new EnumEntitySource(EnumType.ScaleType));
		// scaleTypeList.getList().setInput("");
		// industyTypeList.getList().setSource(
		// new EnumEntitySource(EnumType.IndustyType));
		// industyTypeList.getList().setInput("");

		this.createControl(ID_Button_Save, Button.class, JWT.NONE).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (processData()) {
					postDataSaved();
				}
			}
		});

		//
		shortNameText.setMaximumLength(6);
		fullNameText.setMaximumLength(20);
		registerNotEmptyValidator(shortNameText, getPartnerType() + "���");
		registerNotEmptyValidator(fullNameText, getPartnerType() + "ȫ��");
		registerNotEmptyValidator(creditAmountText, "���ö��");
		registerNotEmptyValidator(creditDayText, "��������");
		registerNotEmptyValidator(remindDayText, "Ԥ������");
		registerInputValidator(new TextInputValidator(provinceList, provinceList.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(cityList, cityList.getHint() + "����Ϊ�գ�") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(districtList, districtList.getHint() + "����Ϊ�գ�") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(addressText, addressText.getHint() + "����Ϊ�գ�") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(postCodeText, postCodeText.getHint() + "����Ϊ�գ�") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationDeliveryAdderss())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(remindDayText, "Ԥ����������С�ڻ��������������") {

			@Override
			protected boolean validateText(String text) {
				int remindDay = Integer.parseInt(StringUtils.isEmpty(remindDayText.getText()) ? "0" : remindDayText
						.getText());
				int creditDay = Integer.parseInt(StringUtils.isEmpty(creditDayText.getText()) ? "0" : creditDayText
						.getText());
				return creditDay >= remindDay;
			}
		});

		registerInputValidator(new TextInputValidator(shortNameText, getPartnerType() + "����Ѿ�����") {

			@Override
			protected boolean validateText(String text) {
				ValidatePartnerNameTask.Method shortMethod;
				if (getPartnerType().equals("�ͻ�")) {
					shortMethod = ValidatePartnerNameTask.Method.CustomerShortName;
				} else {
					shortMethod = ValidatePartnerNameTask.Method.SupplierShortName;
				}
				ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(PartnerInfoProcessor.this.id, text);
				getContext().handle(validateTask, shortMethod);
				return !validateTask.isExist();
			}
		});
		registerInputValidator(new TextInputValidator(fullNameText, getPartnerType() + "ȫ���Ѿ�����") {

			@Override
			protected boolean validateText(String text) {
				ValidatePartnerNameTask.Method fullMethod;
				if (getPartnerType().equals("�ͻ�")) {
					fullMethod = ValidatePartnerNameTask.Method.CustomerName;
				} else {
					fullMethod = ValidatePartnerNameTask.Method.SupplierName;
				}
				ValidatePartnerNameTask validateTask = new ValidatePartnerNameTask(PartnerInfoProcessor.this.id, text);
				getContext().handle(validateTask, fullMethod);
				return !validateTask.isExist();
			}
		});
		//		
		// if(isControlCreditRange()&&login.hasAuth(Auth.SubFunction_CustomerMange_Credit)){
		// //���û�С��������������ö�ȡ� Ȩ�� �����ֿ����������ö��
		// final SalesmanCreditInfo sc =
		// getContext().find(SalesmanCreditInfo.class,login.getEmployeeInfo().getId());
		// registerInputValidator(new
		// TextInputValidator(creditAmountText,"���ö�ȳ�����Ȩ��Χ��"){
		//				
		// @Override
		// protected boolean validateText(String text){
		// String t = creditAmountText.getText();
		// double amount = Double.parseDouble(StringUtils.isEmpty(t) ? "0" : t);
		// return sc.getCustomerCreditLimit()>=amount;
		// }
		// });
		//			
		// registerInputValidator(new
		// TextInputValidator(creditAmountText,"�����˿����õ��ۼ����ö�ȣ�"){
		//				
		// @Override
		// protected boolean validateText(String text){
		// LoginInfo employee = getContext().find(LoginInfo.class);
		// if(employee.hasAuth(Auth.SalesManager)){ //���������Ա����֤���������ö��
		// text = StringUtils.isEmpty(text) ? "0" : text;
		// double totalAmount = Double.parseDouble(text);
		// GetSalesManResidualCreditAmountKey
		// getSalesManResidualCreditAmountKey;
		// if(PartnerInfoProcessor.this.id.equals(GUID.emptyID)){
		// getSalesManResidualCreditAmountKey = new
		// GetSalesManResidualCreditAmountKey();
		// }else{
		// getSalesManResidualCreditAmountKey = new
		// GetSalesManResidualCreditAmountKey(new
		// GUID[]{PartnerInfoProcessor.this.id});
		// }
		// double kszz =
		// getContext().find(Double.class,getSalesManResidualCreditAmountKey);
		// return totalAmount<=kszz;
		// }
		// return true;
		// }
		// });
		//			
		// registerInputValidator(new
		// TextInputValidator(creditDayText,"��������������Ȩ��Χ��"){
		//				
		// @Override
		// protected boolean validateText(String text){
		// int creditDay =
		// Integer.parseInt(StringUtils.isEmpty(creditDayText.getText()) ? "0" :
		// creditDayText.getText());
		// return sc.getCustomerCreditDayLimit()>=creditDay;
		// }
		// });
		// }

	}

	/**
	 * �Ƿ�������ö������
	 * 
	 * @return boolean
	 */
	protected abstract boolean isControlCreditRange();

	/**
	 * �Ƿ���֤�ջ���ַ ���ʡ���С��ء���ϸ��ַ���ʱ�����һ�Ϊ�գ�����Ҫ��֤�ջ���ַ
	 * 
	 * @return boolean
	 */
	private boolean isValidationDeliveryAdderss() {
		return !StringUtils.isEmpty(provinceList.getText()) || !StringUtils.isEmpty(cityList.getText())
				|| !StringUtils.isEmpty(districtList.getText()) || !StringUtils.isEmpty(addressText.getText())
				|| !StringUtils.isEmpty(postCodeText.getText());
	}

	protected abstract void handleSave();

	protected abstract void postDataSaved();

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		if (!validateInput()) {
			return false;
		}
		// if (creditAmountText.getText() != null) {
		// String amount = DoubleUtil.getRoundStr(Double
		// .parseDouble(creditAmountText.getText()));
		// creditAmountText.setDescription(amount);
		// }
		try {
			handleSave();
		} catch (IllegalArgumentException e) {
			alert(e.getMessage());
		}
		resetDataChangedstatus();
		return true;
	}

	protected abstract String getPartnerType();

}
