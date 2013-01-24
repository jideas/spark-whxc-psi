package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.base.browser.partner.PartnerInfoProcessor;
import com.spark.psi.base.browser.partner.PartnerInfoRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;

public abstract class SupplierInfoRender extends PartnerInfoRender {

	@Override
	protected void renderAccountInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		Label label = new Label(composite);
		label.setText("通知地址：");
		label.setLayoutData(gdLabel);
		Composite addressArea3 = new Composite(composite);
		addressArea3.setLayout(new GridLayout(6));
		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 3;
		addressArea3.setLayoutData(gd1);

		Text text = new Text(addressArea3, JWT.APPEARANCE3);
		text.setID(SupplierInfoProcessor.ID_Text_NoticeAddress);
		text.setHint("通知地址");
		text.setLayoutData(gd1);
		text.setMaximumLength(50);

		text = new Text(addressArea3, JWT.APPEARANCE3);
		text.setID(SupplierInfoProcessor.ID_Text_NoticePost);
		text.setHint("邮编");
		text.setMaximumLength(6);
		text.setRegExp(Reg.REGEXP_POSTCODE);
		Label space = new Label(addressArea3);
		gd1 = new GridData();
		gd1.widthHint = 245;
		space.setLayoutData(gd1);

		label = new Label(composite);
		label.setText("  纳税识别号：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_TaxNumber);
		text.setMaximumLength(50);
		text.setRegExp(Reg.REGEXP_NUM);
		// GridData gdTax = new GridData();
		// gdTax.horizontalSpan =2;
		// text.setLayoutData(gdTax);

		label = new Label(composite);
		label.setText("      税率：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_TaxRate);
		text.setMaximumLength(50);
		text.setRegExp("0(\\.\\d{0,4})?$");

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

		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STextEditColumn(SupplierInfoProcessor.AcountTableName.bank.name(), 200, JWT.CENTER, "开户银行");
		columns[1] = new STextEditColumn(SupplierInfoProcessor.AcountTableName.acountName.name(), 200, JWT.CENTER,
				"开户名称");
		columns[2] = new SNumberEditColumn(SupplierInfoProcessor.AcountTableName.acountNumber.name(), 200, JWT.LEFT,
				"银行账号");
		columns[3] = new STextEditColumn(SupplierInfoProcessor.AcountTableName.remark.name(), 300, JWT.LEFT, "备注");
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		SActionInfo[] actions = { PSIActionCommon.getActionInfo(Action.Delete.name()) };
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setAutoAddRow(true);
		tableStyle.setNoScroll(true);
		SEditTable acountTable = new SEditTable(composite, columns, tableStyle, actions);
		acountTable.setID(SupplierInfoProcessor.ID_Table_Acount);
		GridData gdTable = new GridData(GridData.FILL_BOTH);
		gdTable.horizontalSpan = 4;
		gdTable.heightHint = tableStyle.getRowHeight() * SupplierInfoProcessor.DEFAULT_ACOUNT_COUNT
				+ tableStyle.getHeaderHeight() - 1;
		acountTable.setLayoutData(gdTable);
	}

	@Override
	protected void renderBaseInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

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
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
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
		label.setText("供应商类型：");
		label.setLayoutData(gdLabel);
		LWComboList typeList = new LWComboList(composite, JWT.APPEARANCE3);
		typeList.setID(SupplierInfoProcessor.ID_List_Type);

		new SAsteriskLabel(composite, "地址：").setLayoutData(gdLabel);

		Composite addressArea1 = new Composite(composite);
		addressArea1.setLayout(new GridLayout(3));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
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
		text.setRegExp(Reg.REGEXP_NUM);

		// 通知地址
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

		label = new Label(composite);
		label.setText("      合作方式：");
		label.setLayoutData(gdLabel);
		new LWComboList(composite, JWT.APPEARANCE3).setID(SupplierInfoProcessor.ID_List_Way);
	}
}
