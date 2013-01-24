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

/**
 * 联系人编辑界面视图（简单）
 * 
 */
public class ContactEdit2Render extends BaseFormPageRender {

	private ContactBookInfo contactBookInfo;
//	private PartnerInfo partnerInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			this.contactBookInfo = (ContactBookInfo) this.getArgument();
		}
//		if (this.getArgument2() != null) {
//			partnerInfo = (PartnerInfo) getArgument2();
//		}
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

		GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
		gd3.horizontalSpan = 3;
		
//		if (partnerInfo != null) {
//			Label partnerLabel = new Label(formArea);
//			GridData gdPartnerNameLabel = new GridData();
//			gdPartnerNameLabel.horizontalSpan = 4;
//			gdPartnerNameLabel.heightHint = 25;
//			partnerLabel.setLayoutData(gdPartnerNameLabel);
//			partnerLabel.setText(partnerInfo.getName());
//		}

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		new SAsteriskLabel(formArea, "姓名：").setLayoutData(gdLabel);

		Composite area = new Composite(formArea);
		area.setLayout(new GridLayout(2));
		area.setLayoutData(gd3);
		Text text = new Text(area, JWT.APPEARANCE3);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setID(ContactEdit2Processor.ID_Text_Name);
		GridData gd = new GridData();
		gd.widthHint = 80;
		LWComboList comboList = new LWComboList(area, JWT.APPEARANCE3);
		comboList.setLayoutData(gd);
		comboList.setID(ContactEdit2Processor.ID_List_Sex);
		comboList.setHint("性别");

		Label label = new Label(formArea);
		label.setText("部门：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setID(ContactEdit2Processor.ID_Text_Department);

		label = new Label(formArea);
		label.setText("  职务：");
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setID(ContactEdit2Processor.ID_Text_Position);

		label = new Label(formArea);
		label.setText("手机：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setID(ContactEdit2Processor.ID_Text_MobileNo);

		label = new Label(formArea);
		label.setText("  固话：");
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setID(ContactEdit2Processor.ID_Text_LandLineNumber);

		label = new Label(formArea);
		label.setText("电子邮箱：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gd3);
		text.setID(ContactEdit2Processor.ID_Text_Email);

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		if (contactBookInfo != null) {
			button.setText(" 确定修改 ");
		} else {
			button.setText(" 确定新增 ");
		}
		button.setID(ContactEdit2Processor.ID_Button_Confirm);
		button = new Button(buttonArea, JWT.APPEARANCE3);
		if (contactBookInfo != null) {
			button.setText(" 放弃修改 ");
		} else {
			button.setText(" 放弃新增 ");
		}
		button.setID(ContactEdit2Processor.ID_Button_Cancel);
	}

}
