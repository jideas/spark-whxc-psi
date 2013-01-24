package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.task.UpdateCustomerTask;

/**
 * 编辑客户处理器
 */
public class EditCustomerProcessor extends CustomerInfoProcessor {

	protected CustomerInfo partnerInfo;

	public void init(Situation context) {
		super.init(context);
		this.partnerInfo = (CustomerInfo) getPartnerInfo((GUID) this.getArgument());
		this.id = partnerInfo.getId();

	}

	@Override
	protected boolean isControlCreditRange() {
		LoginInfo login = getContext().find(LoginInfo.class);
		return !login.hasAuth(Auth.SubFunction_CustomerMange_Credit2);
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		//
		shortNameText.setText(partnerInfo.getShortName());
		fullNameText.setText(partnerInfo.getName());
		provinceList.setSelection(partnerInfo.getProvince());
		cityList.setSelection(partnerInfo.getCity());
		districtList.setSelection(partnerInfo.getTown());
		addressText.setText(partnerInfo.getAddress());
		postCodeText.setText(partnerInfo.getPostcode());
		landLineNumberText.setText(partnerInfo.getWorkTel());
		faxText.setText(partnerInfo.getFax());
		((SNumberText) creditAmountText).setValue(partnerInfo.getCreditAmount());
		creditDayText.setText(String.valueOf(partnerInfo.getAccountPeriod()));
		remindDayText.setText(String.valueOf(partnerInfo.getAccountRemind()));
		creditAmountText.setText(DoubleUtil.getRoundStr(partnerInfo.getCreditAmount()));
		contactEmailText.setText(partnerInfo.getLinkmanEmail());
		contactLandLineNumberText.setText(partnerInfo.getLinkmanTel());
		contactMobileNumberText.setText(partnerInfo.getLinkmanMobile());
		contactNameText.setText(partnerInfo.getLinkmanName());
		contactSexList.setSelection(partnerInfo.getLinkmanSuffix());
		numberText.setText(partnerInfo.getNumber());
		memoText.setText(partnerInfo.getRemark());
		pricePolicyList.setSelection(partnerInfo.getPricePolicy());
		customerTypeList.setSelection(partnerInfo.getCustomerType());
		taxNumberText.setText(partnerInfo.getTaxNumber());
	}

	protected final void fillTaskData(UpdateCustomerTask task) {
		task.setId(partnerInfo.getId());
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

	protected void handleSave() {
		UpdateCustomerTask task = new UpdateCustomerTask();
		fillTaskData(task);
		//
		getContext().handle(task, UpdateCustomerTask.Method.UPDATE);

		getContext().bubbleMessage(new MsgResponse(false));
	}

	protected PartnerInfo getPartnerInfo(GUID id) {
		return getContext().find(CustomerInfo.class, id);
	}

	@Override
	protected String getPartnerType() {
		return "客户";
	}

	protected final void postDataSaved() {
		hint("保存成功");
	}
}
