package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.psi.base.browser.partner.PartnerInfoProcessor;
import com.spark.psi.base.browser.partner.PartnerInfoRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public abstract class CustomerInfoRender extends PartnerInfoRender {

	@Override
	protected void renderAccountInfo(Composite composite) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		Label label = new Label(composite);
		label.setText("  �ͻ����ͣ�");
		new LWComboList(composite, JWT.APPEARANCE3).setID(CustomerInfoProcessor.ID_List_CustomerType);

		label = new Label(composite);
		label.setText("  ���ò��ԣ�");
		new LWComboList(composite, JWT.APPEARANCE3).setID(CustomerInfoProcessor.ID_Text_PriceStrategy);

		label = new Label(composite);
		label.setText("  ˰��ƾ֤�ţ�");
		Text text = new Text(composite, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_TaxNumber);
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 3;
		text.setLayoutData(layoutData);

		GridData gd = new GridData();
		gd.widthHint = 100;

		Composite creditCmp = composite;
		LoginInfo login = getContext().find(LoginInfo.class);
		if ((!login.hasAuth(Auth.SubFunction_PartnerMange_ShowCredit)) || !isCanSetCredit()) {
			creditCmp = new Composite(composite.getParent().getParent().getParent()); // XXX���˴��Ĵ���ʽ����
		}
		label = new Label(creditCmp);
		label.setText("  ���ö�ȣ�");
		Composite creditAmountArea = new Composite(creditCmp);
		creditAmountArea.setLayout(new GridLayout(4));
		SNumberText numberText = new SNumberText(creditAmountArea, 2);
		numberText.setID(PartnerInfoProcessor.ID_Text_CreditAmount);
		numberText.setLayoutData(gd);
		numberText.setMaximumLength(19);
		numberText.setValue(0);

		new Label(creditAmountArea).setText(" Ԫ");

		label = new Label(creditCmp);
		label.setText("  ����������");
		Composite creditDayArea = new Composite(creditCmp);
		creditDayArea.setLayout(new GridLayout(4));
		text = new Text(creditDayArea, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_CreditDay);
		text.setLayoutData(gd);
		text.setMaximumLength(5);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setText("0");

		new Label(creditDayArea).setText(" ��       ��ǰ");
		text = new Text(creditDayArea, JWT.APPEARANCE3);
		text.setID(PartnerInfoProcessor.ID_Text_RemindDay);
		text.setLayoutData(gd);
		text.setMaximumLength(5);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setText("0");
		new Label(creditDayArea).setText(" ������");
	}
}
