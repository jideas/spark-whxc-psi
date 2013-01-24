package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.supplier.NewSupplierProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;

/**
 * 客户供应商信息视图
 * 
 */
public abstract class PartnerInfoRender extends BaseFormPageRender {

	protected final boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected final void renderFormArea(Composite formArea) {
		//
		formArea.setLayout(new FillLayout());
		ScrolledPanel scrolledPanel = new ScrolledPanel(formArea, JWT.V_SCROLL);
		Composite scrolledArea = scrolledPanel.getComposite();
		scrolledPanel.setHorizontalPosition(JWT.NO);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		gl.horizontalSpacing = 5;
		gl.marginRight = 5;
		gl.verticalSpacing = 5;
		scrolledArea.setLayout(gl);

		Composite composite = new Composite(scrolledArea);
		composite.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		renderBaseInfo(composite);

		composite = new Composite(scrolledArea);
		GridData gd = new GridData();
		gd.widthHint = 250;
		gd.heightHint = 120;
		gd.horizontalSpan = 2;
		gd.verticalAlignment = JWT.TOP;
		composite.setLayoutData(gd);
		renderOptionInfo(composite);

		composite = new Composite(scrolledArea);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		gd.heightHint = 1;
		composite.setLayoutData(gd);
		composite.setBackground(new Color(0x78a9bf));

		composite = new Composite(scrolledArea);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		composite.setLayoutData(gd);
		renderAccountInfo(composite);

		composite = new Composite(scrolledArea);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		gd.heightHint = 1;
		composite.setLayoutData(gd);
		composite.setBackground(new Color(0x78a9bf));

		composite = new Composite(scrolledArea);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		composite.setLayoutData(gd);
		composite.setID(PartnerInfoProcessor.ID_Area_OtherInfo);
		renderOtherInfo(composite);

		if (isCanSetCredit()) {
			composite = new Composite(scrolledArea);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			gd.heightHint = 1;
			composite.setLayoutData(gd);
			composite.setBackground(new Color(0x78a9bf));
		}
	}

	protected void renderBaseInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 6;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		GridData gd3 = new GridData();
		gd3.horizontalSpan = 3;
		gd3.grabExcessHorizontalSpace = true;
		gd3.horizontalAlignment = JWT.FILL;

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		PartnerType partnerType = getPartnerType();
		new SAsteriskLabel(composite, partnerType == null ? "" : partnerType.getName() + "：").setLayoutData(gdLabel);
		Text text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_ShortName);
		text.setHint("六字以内简称");
		text.setMaximumLength(6);
		text.setRegExp(SAAS.Reg.TEXT);

		new SAsteriskLabel(composite, "全称：").setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_FullName);
		text.setLayoutData(gd3);
		text.setMaximumLength(50);
		text.setRegExp(SAAS.Reg.TEXT);

		Label label = new Label(composite);
		label.setText("      编号：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_Number);
		text.setMaximumLength(20);
		text.setEnabled(false);

		label = new Label(composite);
		label.setText("      电话：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_LandLineNumber);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);
		label = new Label(composite);
		label.setText("传真：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_Fax);
		text.setRegExp(Reg.REGEXP_PHONE);
		text.setMaximumLength(20);

		new SAsteriskLabel(composite, "地址：").setLayoutData(gdLabel);

		Composite addressArea1 = new Composite(composite);
		addressArea1.setLayout(new GridLayout(3));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 5;
		addressArea1.setLayoutData(gd);
		LWComboList comboList = new LWComboList(addressArea1, JWT.APPEARANCE3);
		comboList.setID(PartnerInfoProcessor.ID_List_Province);
		comboList.setHint("省（直辖市）");
		comboList = new LWComboList(addressArea1, JWT.APPEARANCE3);
		comboList.setID(PartnerInfoProcessor.ID_List_City);
		comboList.setHint("市（区）");
		comboList = new LWComboList(addressArea1, JWT.APPEARANCE3);
		comboList.setID(PartnerInfoProcessor.ID_List_District);
		comboList.setHint("区（县）");

		new Label(composite).setText("");
		Composite addressArea2 = new Composite(composite);
		addressArea2.setLayout(new GridLayout(2));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		addressArea2.setLayoutData(gd);
		text = new Text(addressArea2, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_Address);
		text.setHint("详细地址");
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(50);
		text = new Text(addressArea2, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_PostCode);
		text.setHint("邮编");
		text.setMaximumLength(6);
		text.setRegExp(Reg.REGEXP_POSTCODE);

	}

	protected void renderOptionInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.marginTop = 4;
		gl.numColumns = 3;
		gl.verticalSpacing = 8;
		composite.setLayout(gl);

		Composite line = new Composite(composite);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 1;
		gd.verticalSpan = 4;
		line.setLayoutData(gd);
		line.setBackground(new Color(0x78a9bf));

		Label label = new Label(composite);
		label.setText("  联 系 人：");

		Composite area = new Composite(composite);
		area.setLayout(new GridLayout(2));
		area.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		Text text = new Text(area, JWT.APPEARANCE3);
		text.setID(NewSupplierProcessor.ID_Text_ContactName);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(20);
		LWComboList comboList = new LWComboList(area, JWT.APPEARANCE3);
		comboList.setID(NewSupplierProcessor.ID_List_ContactSex);
		comboList.setHint("性别");
		gd = new GridData();
		gd.widthHint = 70;
		comboList.setLayoutData(gd);

		label = new Label(composite);
		label.setText("  手    机：");
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(NewSupplierProcessor.ID_Text_ContactMobileNumber);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_mob);

		label = new Label(composite);
		label.setText("  固    话：");
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(NewSupplierProcessor.ID_Text_ContactLandLineNumber);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);

		label = new Label(composite);
		label.setText("  邮    箱：");
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(NewSupplierProcessor.ID_Text_ContactMail);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setRegExp(Reg.Mail);
		text.setMaximumLength(25);
	}

	protected void renderAccountInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		Label label = new Label(composite);
		label.setText("  开户银行：");
		label.setLayoutData(gdLabel);
		Text text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_AccountBank);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(50);

		label = new Label(composite);
		label.setText("  开户名称：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_AccountName);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(25);

		label = new Label(composite);
		label.setText("  银行账号：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_AccountNumber);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(30);
		text.setRegExp(Reg.REGEXP_NUM);

		label = new Label(composite);
		label.setText("  增值税号：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_TaxNumber);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(50);

		GridData gd = new GridData();
		gd.widthHint = 100;

		Composite creditCmp = composite;
		LoginInfo login = getContext().find(LoginInfo.class);
		if ((!login.hasAuth(Auth.SubFunction_PartnerMange_ShowCredit)) || !isCanSetCredit()) {
			creditCmp = new Composite(composite.getParent().getParent().getParent()); // XXX：此处的处理方式不好
		}
		label = new Label(creditCmp);
		label.setText("  信用额度：");
		Composite creditAmountArea = new Composite(creditCmp);
		creditAmountArea.setLayout(new GridLayout(4));
		SNumberText numberText = new SNumberText(creditAmountArea, 2);
		numberText.setID(PartnerInfoProcessor.ID_Text_CreditAmount);
		numberText.setLayoutData(gd);
		numberText.setMaximumLength(19);
		numberText.setValue(0);

		new Label(creditAmountArea).setText(" 元");

		label = new Label(creditCmp);
		label.setText("  账期天数：");
		Composite creditDayArea = new Composite(creditCmp);
		creditDayArea.setLayout(new GridLayout(4));
		text = new Text(creditDayArea, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_CreditDay);
		text.setLayoutData(gd);
		text.setMaximumLength(5);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setText("0");

		new Label(creditDayArea).setText(" 天       提前");
		text = new Text(creditDayArea, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_RemindDay);
		text.setLayoutData(gd);
		text.setMaximumLength(5);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setText("0");
		new Label(creditDayArea).setText(" 天提醒");

	}

	protected abstract boolean isCanSetCredit();

	protected void renderOtherInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 6;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		GridData gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.horizontalSpan = 4;

		gdLabel = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gdLabel.verticalIndent = 3;
		Label label = new Label(composite);
		label.setText("  备注信息：");
		label.setLayoutData(gdLabel);
		Text text = new Text(composite, JWT.APPEARANCE3 | JWT.MULTI);
		text.setID(PartnerInfoProcessor.ID_Text_Memo);
		text.setMaximumLength(500);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 5;
		text.setLayoutData(gd);

	}

	protected void renderBeforeOtherInfo(Composite composite) {
		composite.dispose();
	}

	protected void renderAfterOtherInfo(Composite composite) {

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(PartnerInfoProcessor.ID_Button_Save);
		saveButton.setText("    保存    ");

	}

	protected abstract PartnerType getPartnerType();
}
