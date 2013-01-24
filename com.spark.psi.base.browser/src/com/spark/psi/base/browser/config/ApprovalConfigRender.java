package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * ������ý�����ͼ
 */
public class ApprovalConfigRender extends BaseFormPageRender {

	Composite compositeTxt;

	private void InitUI(Composite formArea, String... strings) {

		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;

		Label lblGroup = new Label(formArea);
		lblGroup.setText(strings[0]);
		lblGroup.setFont(new Font(9, JWT.FONT_STYLE_NAME_BOLD,
				JWT.FONT_STYLE_BOLD));
		lblGroup.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Composite composite = new Composite(formArea);
		composite.setLayout(new GridLayout(2));

		Label lblTitle = new Label(composite);
		lblTitle.setLayoutData(gridData);
		lblTitle.setText(strings[1]);

		Button btnOn = new Button(composite, JWT.RADIO);
		btnOn.setLayoutData(gridData);
		btnOn.setID(strings[2]);
		btnOn.setText("�������");

		Button btnOff = new Button(composite, JWT.RADIO);
		btnOff.setID(strings[3]);
		btnOff.setText("�ر����");

		if (strings.length > 4) {
			compositeTxt = new Composite(composite);
			compositeTxt.setID(strings[4]);
			compositeTxt.setLayout(new GridLayout(3));
			GridData gridata_txt = new GridData();
			gridata_txt.widthHint = 100;
			new Label(compositeTxt).setText("�������ﵽ��");
			SNumberText txt = new SNumberText(compositeTxt, 2);
			txt.setID(strings[5]);
			txt.setLayoutData(gridata_txt);
			txt.setMaximumLength(20);
			new Label(compositeTxt).setText("Ԫʱ,������ˡ�");
		}
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		
		formArea.setLayout(new GridLayout());
		formArea.setLayoutData(GridData.INS_FILL_BOTH);
		
		GridLayout layout = new GridLayout(2);
//		layout.verticalSpacing = 2;
		
		Composite cmp = new Composite(formArea);		
		cmp.setLayout(layout);
		cmp.setLayoutData(GridData.INS_CENTER_BOTH);
		
		InitUI(cmp, "���۶�����ˣ�", "�����û��ύ�����۶�����,�Ƿ���Ҫ�������۾���Ȩ�޵��û����ܾ������������",
				ApprovalConfigProcessor.ID_RADIO_SALEORDER_ON,
				ApprovalConfigProcessor.ID_RADIO_SALEORDER_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_SALEORDEROFF,
				ApprovalConfigProcessor.ID_TEXT_SALE_APPROVALAMOUNT);
		InitUI(cmp, "�����˻���ˣ�", "�����û��ύ�������˻���,�Ƿ���Ҫ�������۾���Ȩ�޵��û����ܾ������������",
				ApprovalConfigProcessor.ID_RADIO_SALERETURN_ON,
				ApprovalConfigProcessor.ID_RADIO_SALERETURN_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_SALERETURNOFF,
				ApprovalConfigProcessor.ID_TEXT_SALERETURN_APPROVALAMOUNT);
		InitUI(cmp, "�ɹ�������ˣ�", "�����û��ύ�Ĳɹ�������,�Ƿ���Ҫ���вɹ�����Ȩ�޵��û����ܾ������������",
				ApprovalConfigProcessor.ID_RADIO_PURCHASEORDER_ON,
				ApprovalConfigProcessor.ID_RADIO_PURCHASEORDER_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_PURCHASEORDEROFF,
				ApprovalConfigProcessor.ID_TEXT_PURCHASE_APPROVALAMOUNT);
		InitUI(cmp, "�ɹ��˻���ˣ�", "�����û��ύ�Ĳɹ��˻���,�Ƿ���Ҫ���вɹ�����Ȩ�޵��û����ܾ������������",
				ApprovalConfigProcessor.ID_RADIO_PURCHASERETURN_ON,
				ApprovalConfigProcessor.ID_RADIO_PURCHASERETURN_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_PURCHASERETURNOFF,
				ApprovalConfigProcessor.ID_TEXT_PURCHASERETURN_APPROVALAMOUNT);
		InitUI(cmp, "��������ˣ�",
				"�����û��ύ�Ŀ�������,�Ƿ���Ҫ����ֿ�Ŀ�ܾ������ֿ�Ŀ�ܾ�����ܾ������������",
				ApprovalConfigProcessor.ID_RADIO_INVENTORYALLOCATE_ON,
				ApprovalConfigProcessor.ID_RADIO_INVENTORYALLOCATE_OFF);
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("   ����   ");
		button.setID(ApprovalConfigProcessor.ID_BUTTON_SAVEBUTTON);
	}
}