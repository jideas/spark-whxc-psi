package com.spark.psi.account.browser.receipt;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.psi.account.browser.PartnerSelectPage;

/**
 * 新增收款界面视图（需要选择收款对象）
 */
public class NewReceipt2Render extends AbstractBoxPageRender {

	protected Composite headerArea;
	protected Composite leftArea;
	protected Composite rightArea;

	private static GridData gdLabel;
	private static GridData gdInput;

	static {
		gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gdInput = new GridData(GridData.VERTICAL_ALIGN_END | GridData.FILL_HORIZONTAL);
	}

	@Override
	protected void beforeFooterRender() {
		Composite mainArea = new Composite(contentArea);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		// gl.horizontalSpacing = 5;
		mainArea.setLayout(gl);
		mainArea.setLayoutData(GridData.INS_FILL_BOTH);

		headerArea = new Composite(mainArea);
		GridData gdHead = new GridData();
		gdHead.heightHint = 24;
		gdHead.horizontalSpan = 2;
		gdHead.widthHint = 270;
		headerArea.setLayoutData(gdHead);
		GridLayout glHead = new GridLayout(2);
		glHead.horizontalSpacing = 20;
		glHead.marginRight = 0;
		headerArea.setLayout(glHead);

		//
		leftArea = new Composite(mainArea);
		GridData gdLeft = new GridData(GridData.FILL_VERTICAL);
		gdLeft.widthHint = gdHead.widthHint;
		leftArea.setLayoutData(gdLeft);
		leftArea.setLayout(new FillLayout());

		rightArea = new Composite(mainArea);
		GridData gdRight = new GridData(GridData.FILL_VERTICAL);
		// gdRight.widthHint = 500;
		gdRight.grabExcessHorizontalSpace = true;
		gdRight.horizontalAlignment = JWT.FILL;
		rightArea.setLayoutData(gdRight);
		GridLayout glRight = new GridLayout();
		glRight.numColumns = 5;
		glRight.verticalSpacing = 10;
		glRight.marginLeft = 10;
		glRight.marginTop = 10;
		rightArea.setLayout(glRight);

		Label selectedObjLabel = new Label(headerArea);
		selectedObjLabel.setText("选择收款对象：");
		selectedObjLabel.setFont(new Font(9, "宋体", JWT.FONT_STYLE_BOLD));
		SSearchText2 search = new SSearchText2(headerArea);
		search.setID(NewReceipt2Processor.ID_Search);
		search.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new PartnerSelectPage(leftArea, PartnerSelectPage.ContentStyle.CustomerFirst).setID(NewReceipt2Processor.ID_PartnerPage);

		GridData gdOneRow = new GridData(GridData.VERTICAL_ALIGN_END);
		gdOneRow.horizontalSpan = 3;
		gdOneRow.heightHint = gdInput.heightHint;

		Label label0 = new Label(rightArea);
		label0.setText("收款对象：");
		label0.setLayoutData(gdLabel);
		Label parterLabel = new Label(rightArea);
		parterLabel.setID(NewReceipt2Processor.ID_Label_Partner);
		parterLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		GridData gdPartnerName = new GridData();
		gdPartnerName.widthHint = 210;
		parterLabel.setLayoutData(gdPartnerName);

		String tempStr = "      ";
		new Label(rightArea).setText(tempStr);
		
		Label label4 = new Label(rightArea);
		label4.setText("预计收款日期：");
		label4.setLayoutData(gdLabel);
		SDatePicker receiptDate = new SDatePicker(rightArea);
		receiptDate.setID(NewReceipt2Processor.ID_Date_Date);
		receiptDate.setLayoutData(gdInput);
		
//		Label space = new Label(rightArea);
//		space.setLayoutData(gdInput);
//		space = new Label(rightArea);
//		space.setLayoutData(gdInput);
		Label label5 = new Label(rightArea);
		label5.setText("收款原因：");
		label5.setLayoutData(gdLabel);
		Label label6 = new Label(rightArea);
		label6.setID(NewReceipt2Processor.ID_Label_Type);
		GridData gd6 = new GridData(GridData.VERTICAL_ALIGN_CENTER);
		label6.setLayoutData(gd6);

		new Label(rightArea).setText(tempStr);
		
		Label label7 = new Label(rightArea);
		label7.setText("  收款方式：");
		label7.setLayoutData(gdLabel);
		LWComboList wayList = new LWComboList(rightArea, JWT.APPEARANCE3);
		wayList.setID(NewReceipt2Processor.ID_List_Way);
		wayList.setLayoutData(gdInput);

//		space = new Label(rightArea);
//		space.setLayoutData(gdInput);
//		space = new Label(rightArea);
//		space.setLayoutData(gdInput);

		renderTable();
	}

	private void renderTable() {
		Composite comp = new Composite(rightArea);
		GridData gd = new GridData();
		gd.horizontalSpan = 6;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = JWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = JWT.FILL;
		comp.setLayoutData(gd);
		comp.getParent().layout();
		GridLayout gl = new GridLayout(1);
		gl.verticalSpacing = 0;
		comp.setLayout(gl);

		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setAutoAddRow(false);
		tableStyle.setPageAble(false);
//		tableStyle.setNoHScroll(true);
		tableStyle.setSelectionMode(SSelectionMode.Multi);

		SEditTable table = new SEditTable(comp, getColumns(), tableStyle, null);
		table.setID(NewReceipt2Processor.ID_Table);
		table.setLayoutData(GridData.INS_FILL_BOTH);
		renderRemarkInfo(comp);
	}

	private void renderRemarkInfo(Composite area) {
		Composite tableBottomArea = new Composite(area);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 60;
		gd.verticalIndent = -1;
		tableBottomArea.setLayoutData(gd);
		GridLayout gl = new GridLayout(4);
		gl.marginTop = 5;
		gl.marginRight = 5;
		tableBottomArea.setLayout(gl);

		tableBottomArea.setBackground(Color.COLOR_WHITE);
		tableBottomArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));

		Label label = new Label(tableBottomArea);
		label.setText("  备注：");
		GridData gdLabel = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		label.setLayoutData(gdLabel);

		Text memoText = new Text(tableBottomArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		memoText.setID(NewReceipt2Processor.ID_Text_Remark);
		GridData gdMemoText = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoText.heightHint = 50;
		memoText.setLayoutData(gdMemoText);

		label = new Label(tableBottomArea);
		label.setText("  总额：");
		label.setLayoutData(gdLabel);

		Text value = new Text(tableBottomArea);
		value.setID(NewReceipt2Processor.ID_Text_TotalAmount);
		value.setText("0.00");
		GridData valuegd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		valuegd.widthHint = 100;
		value.setEnabled(false);
		value.setLayoutData(valuegd);

	}

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(NewReceipt2Processor.Columns.checkDate.name(), 100, JWT.CENTER, "出库时间");
		columns[1] = new STableColumn(NewReceipt2Processor.Columns.billsNo.name(), 100, JWT.LEFT, "出库单号");
		columns[2] = new STableColumn(NewReceipt2Processor.Columns.relaBillsNo.name(), 100, JWT.LEFT, "相关单据");
		columns[3] = new STableColumn(NewReceipt2Processor.Columns.checkedAmount.name(), 100, JWT.RIGHT, "出库金额");
		// columns[4] = new SNumberEditColumn(NewReceipt2Processor.Columns.molingAmount.name(), 100, JWT.RIGHT, "抹零金额");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		// columns[4].setGrab(true);
		return columns;
	}

	@Override
	protected void afterFooterRender() {
		GridLayout glFooter = new GridLayout();
		glFooter.numColumns = 1;
		footerArea.setLayout(glFooter);
		
		Composite footerRightArea = new Composite(footerArea);
		GridData gdFooter = new GridData(GridData.FILL_VERTICAL | GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		footerRightArea.setLayoutData(gdFooter);
		GridLayout glFooterRigth = new GridLayout();
		glFooterRigth.numColumns = 2;
		footerRightArea.setLayout(glFooterRigth);
		
		GridData gdButton = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gdButton.heightHint = 28;

		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setID(NewReceipt2Processor.ID_Button_Save);
		button.setText("  保 存  ");
		button.setLayoutData(gdButton);

		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setID(NewReceipt2Processor.ID_Button_Submit);
		button.setText("  提 交 ");
		button.setLayoutData(gdButton);

	}
}
