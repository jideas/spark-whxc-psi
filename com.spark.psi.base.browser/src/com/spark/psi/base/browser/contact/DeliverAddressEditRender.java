package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

public class DeliverAddressEditRender extends BaseFormPageRender {

	private ContactBookInfo contactBookInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			this.contactBookInfo = (ContactBookInfo) this.getArgument();
		}
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.verticalSpacing = 8;
		formArea.setLayout(gl);

		GridData gd1 = new GridData();
		gd1.widthHint = 120;

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		new SAsteriskLabel(formArea, "收货地址：");

		LWComboList comboList = new LWComboList(formArea, JWT.APPEARANCE3);
		comboList.setID(DeliverAddressEditProcessor.ID_List_Province);
		comboList.setLayoutData(gd1);

		comboList = new LWComboList(formArea, JWT.APPEARANCE3);
		comboList.setID(DeliverAddressEditProcessor.ID_List_City);
		comboList.setLayoutData(gd1);

		comboList = new LWComboList(formArea, JWT.APPEARANCE3);
		comboList.setID(DeliverAddressEditProcessor.ID_List_District);
		comboList.setLayoutData(gd1);

		GridData gd2 = new GridData();
		gd2.widthHint = 245;
		gd2.horizontalSpan = 2;

		GridData gd3 = new GridData();
		gd3.horizontalSpan = 3;
		gd3.widthHint = 245;

		Label label = new Label(formArea);

		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setHint("详细地址");
		text.setID(DeliverAddressEditProcessor.ID_Text_Address);
		text.setLayoutData(gd2);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setHint("邮编");
		text.setID(DeliverAddressEditProcessor.ID_Text_PostCode);
		text.setMaximumLength(6);
		text.setLayoutData(gd1);

		label = new Label(formArea);
		label.setLayoutData(gdLabel);
		label.setText("收货人：");
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gd3);
		text.setID(DeliverAddressEditProcessor.ID_Text_Consignee);

		label = new Label(formArea);
		label.setText("固话：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gd3);
		text.setID(DeliverAddressEditProcessor.ID_Text_LandLineNumber);
		text.setMaximumLength(20);

		label = new Label(formArea);
		label.setLayoutData(gdLabel);
		label.setText("手机：");
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gd3);
		text.setID(DeliverAddressEditProcessor.ID_Text_MobileNo);
		text.setMaximumLength(11);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		if (contactBookInfo != null) {
			button.setText(" 确定修改 ");
		} else {
			button.setText(" 确定新增 ");
		}
		button.setID(DeliverAddressEditProcessor.ID_Button_Confirm);
		button = new Button(buttonArea, JWT.APPEARANCE3);
		if (contactBookInfo != null) {
			button.setText(" 放弃修改 ");
		} else {
			button.setText(" 放弃新增 ");
		}
		button.setID(DeliverAddressEditProcessor.ID_Button_Cancel);
	}

}
