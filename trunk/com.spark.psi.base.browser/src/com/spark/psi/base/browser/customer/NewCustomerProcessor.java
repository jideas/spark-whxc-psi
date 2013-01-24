package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.partner.task.UpdateCustomerTask;

/**
 * �����ͻ�������
 */
public class NewCustomerProcessor extends CustomerInfoProcessor {

	public final static String ID_Text_ContactName = "Text_ContactName";
	public final static String ID_List_ContactSex = "List_ContactSex";
	public final static String ID_Text_ContactMobileNumber = "Text_ContactMobileNumber";
	public final static String ID_Text_ContactLandLineNumber = "Text_ContactLandLineNumber";
	public final static String ID_Text_ContactMail = "Text_ContactMail";

	public final static String ID_Button_SaveAndNew = "Button_SaveAndNew";

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

	@Override
	public void process(Situation situation) {
		super.process(situation);
		contactNameText = this.createControl(ID_Text_ContactName, Text.class);
		contactSexList = this.createControl(ID_List_ContactSex, LWComboList.class);
		contactMobileNumberText = this.createControl(ID_Text_ContactMobileNumber, Text.class);
		contactLandLineNumberText = this.createControl(ID_Text_ContactLandLineNumber, Text.class);
		contactEmailText = this.createControl(ID_Text_ContactMail, Text.class);
		contactSexList.getList().setSource(new EnumEntitySource(EnumType.Sex));
		contactSexList.getList().setInput("");
		this.createControl(ID_Button_SaveAndNew, Button.class, JWT.NONE).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!validateInput()) {
					return;
				}
				handleSaveAndNew();
			}
		});

		registerInputValidator(new TextInputValidator(contactNameText, "��ϵ����������Ϊ�գ�") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationContact())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
	}

	/**
	 * �Ƿ���֤��ϵ������ �����ϵ���ֻ����룬�̻�����������һ����Ϊ�վ���Ҫ��֤��ϵ������
	 * 
	 * @return boolean
	 */
	private boolean isValidationContact() {
		return !StringUtils.isEmpty(contactEmailText.getText())
				|| !StringUtils.isEmpty(contactMobileNumberText.getText())
				|| !StringUtils.isEmpty(contactLandLineNumberText.getText());
	}

	protected final void fillTaskData(UpdateCustomerTask task) {
		task.setShortName(shortNameText.getText());
		task.setName(fullNameText.getText());
		task.setAddress(addressText.getText());
		task.setPostcode(postCodeText.getText());
		task.setProvince(provinceList.getText());
		task.setCity(cityList.getText());
		task.setTown(districtList.getText());
		task.setLinkmanEmail(contactEmailText.getText());
		task.setLinkmanTel(contactLandLineNumberText.getText());
		task.setLinkmanMobile(contactMobileNumberText.getText());
		task.setLinkmanName(contactNameText.getText());
		task.setLinkmanSuffix(contactSexList.getText()); //
		task.setCreditAmount(creditAmountText.getDoubleValue(0));
		task.setAccountPeriod(Integer.parseInt(creditDayText.getText() == null ? "0" : creditDayText.getText()));
		task.setAccountRemind(Integer.parseInt(remindDayText.getText() == null ? "0" : remindDayText.getText()));
		task.setWorkTel(landLineNumberText.getText());
		task.setFax(faxText.getText());
		task.setRemark(memoText.getText());
		task.setPricePolicy(pricePolicyList.getText());
		task.setCustomerType(customerTypeList.getText());
		task.setTaxNumber(taxNumberText.getText());
	}

	@Override
	protected boolean isControlCreditRange() {
		LoginInfo login = getContext().find(LoginInfo.class);
		return !login.hasAuth(Auth.SubFunction_CustomerMange_Credit2);
	}

	protected void handleSave() {
		UpdateCustomerTask task = new UpdateCustomerTask();
		fillTaskData(task);
		getContext().handle(task, UpdateCustomerTask.Method.CREATE);
		// �ص��б�ҳ
		MsgRequest request = new MsgRequest(new PageControllerInstance(new PageController(
				PotentialCustomerListProcessor.class, PotentialCustomerListRender.class)));
		getContext().bubbleMessage(request);
	}

	protected void handleSaveAndNew() {
		UpdateCustomerTask task = new UpdateCustomerTask();
		fillTaskData(task);
		getContext().handle(task, UpdateCustomerTask.Method.CREATE);
		// ˢ���½�ҳ
		MsgRequest request = new MsgRequest(new PageControllerInstance(new PageController(NewCustomerProcessor.class,
				NewCustomerRender.class)));
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getPartnerType() {
		return "�ͻ�";
	}

	@Override
	protected void postDataSaved() {

	}

}
