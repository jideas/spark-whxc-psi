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
 * 审核配置界面视图
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
		btnOn.setText("开启审核");

		Button btnOff = new Button(composite, JWT.RADIO);
		btnOff.setID(strings[3]);
		btnOff.setText("关闭审核");

		if (strings.length > 4) {
			compositeTxt = new Composite(composite);
			compositeTxt.setID(strings[4]);
			compositeTxt.setLayout(new GridLayout(3));
			GridData gridata_txt = new GridData();
			gridata_txt.widthHint = 100;
			new Label(compositeTxt).setText("订单金额达到：");
			SNumberText txt = new SNumberText(compositeTxt, 2);
			txt.setID(strings[5]);
			txt.setLayoutData(gridata_txt);
			txt.setMaximumLength(20);
			new Label(compositeTxt).setText("元时,进行审核。");
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
		
		InitUI(cmp, "销售订货审核：", "设置用户提交的销售订货单,是否需要具有销售经理权限的用户或总经理进行审批。",
				ApprovalConfigProcessor.ID_RADIO_SALEORDER_ON,
				ApprovalConfigProcessor.ID_RADIO_SALEORDER_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_SALEORDEROFF,
				ApprovalConfigProcessor.ID_TEXT_SALE_APPROVALAMOUNT);
		InitUI(cmp, "销售退货审核：", "设置用户提交的销售退货单,是否需要具有销售经理权限的用户或总经理进行审批。",
				ApprovalConfigProcessor.ID_RADIO_SALERETURN_ON,
				ApprovalConfigProcessor.ID_RADIO_SALERETURN_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_SALERETURNOFF,
				ApprovalConfigProcessor.ID_TEXT_SALERETURN_APPROVALAMOUNT);
		InitUI(cmp, "采购订货审核：", "设置用户提交的采购订货单,是否需要具有采购经理权限的用户或总经理进行审批。",
				ApprovalConfigProcessor.ID_RADIO_PURCHASEORDER_ON,
				ApprovalConfigProcessor.ID_RADIO_PURCHASEORDER_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_PURCHASEORDEROFF,
				ApprovalConfigProcessor.ID_TEXT_PURCHASE_APPROVALAMOUNT);
		InitUI(cmp, "采购退货审核：", "设置用户提交的采购退货单,是否需要具有采购经理权限的用户或总经理进行审批。",
				ApprovalConfigProcessor.ID_RADIO_PURCHASERETURN_ON,
				ApprovalConfigProcessor.ID_RADIO_PURCHASERETURN_OFF,
				ApprovalConfigProcessor.ID_COMPOSITE_PURCHASERETURNOFF,
				ApprovalConfigProcessor.ID_TEXT_PURCHASERETURN_APPROVALAMOUNT);
		InitUI(cmp, "库存调拨审核：",
				"设置用户提交的库存调拨单,是否需要出库仓库的库管经理、入库仓库的库管经理或总经理进行审批。",
				ApprovalConfigProcessor.ID_RADIO_INVENTORYALLOCATE_ON,
				ApprovalConfigProcessor.ID_RADIO_INVENTORYALLOCATE_OFF);
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("   保存   ");
		button.setID(ApprovalConfigProcessor.ID_BUTTON_SAVEBUTTON);
	}
}