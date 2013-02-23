package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;

public class DistributeRender extends BaseFormPageRender {
	/**
	 * 是否自定义Form布局
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 10;
		gl.numColumns = 3;
		formArea.setLayout(gl);

		Composite leftArea = new Composite(formArea);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 250;
		leftArea.setLayoutData(gd);

		Composite emptyArea = new Composite(formArea);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 1;
		emptyArea.setLayoutData(gd);

		Composite rightArea = new Composite(formArea);
		rightArea.setLayoutData(GridData.INS_FILL_BOTH);
		
		Composite bottomArea = new Composite(formArea);
		GridData gdBottom = new GridData(GridData.FILL_HORIZONTAL);
		gdBottom.horizontalSpan = 3;
		gdBottom.heightHint = 50;
		bottomArea.setLayoutData(gdBottom);
		
		renderLeftArea(leftArea);
		renderRightArea(rightArea);
		bottomArea.dispose();
//		renderBottomArea(bottomArea);
	}

	private void renderLeftArea(Composite parent) {
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		parent.setLayout(gl);

		Composite header = new Composite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 25;
		header.setLayoutData(gd);
		header.setLayout(new GridLayout());

		Label label = new Label(header);
		label.setText("选择仓库：");
		label.setID(DistributeProcessor.ID_Label_StoreName);
//		label.setID(DistributeSalesOrderProcessor.ID_Label_StoreInfo);
		gd = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER | GridData.FILL_HORIZONTAL);
		label.setLayoutData(gd);

		//
		STableColumn column = new STableColumn(DistributeProcessor.StoreTableColumn.name.name(), 100, JWT.LEFT, "仓库名称");
		column.setGrab(true);
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.None);
		STable table = new STable(parent, new STableColumn[] { column },
				tableStyle);
		table.setID(DistributeProcessor.ID_Table_Store);
		table.setLayoutData(GridData.INS_FILL_BOTH);
	}

	private void renderRightArea(Composite parent) {
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		parent.setLayout(gl);

		Composite header = new Composite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 25;
		header.setLayoutData(gd);
		header.setLayout(new GridLayout(5));

		Label label = new Label(header);
		label.setText("分配材料：");

		label = new Label(header);
		label.setText("计划完成日期：");
		gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_CENTER | GridData.GRAB_VERTICAL);
		label.setLayoutData(gd);

		DatePicker datePicker = new SDatePicker(header);
		datePicker.setID(DistributeProcessor.ID_Date_Date);
		datePicker.setEnabled(true);
		new Label(header).setText("  ");
		label = new Label(header);
		label.setID(DistributeProcessor.ID_Button_ResolveAll);
		label.setText("剩余材料分配到本库 ");
		label.setForeground(Color.COLOR_BLUE);
		label.setCursor(Cursor.HAND);
		label.setLayoutData(new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER));

		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(DistributeProcessor.MaterialTableColumn.name.name(), 200, JWT.LEFT, "材料名称");
		columns[1] = new STableColumn(DistributeProcessor.MaterialTableColumn.unit.name(), 100, JWT.CENTER, "单位");
		columns[2] = new STableColumn(DistributeProcessor.MaterialTableColumn.orderCount.name(), 130, JWT.RIGHT, "数量");
		columns[3] = new STableColumn(DistributeProcessor.MaterialTableColumn.UnAllocate.name(), 130, JWT.RIGHT, "未分配量");
//		columns[4] = new STableColumn(DistributeProcessor.MaterialTableColumn.Available.name(), 130, JWT.RIGHT, "本库可用");
		columns[4] = new SNumberEditColumn(DistributeProcessor.MaterialTableColumn.Allocate.name(), 130, JWT.RIGHT, "本库分配");
		((SNumberEditColumn) columns[4]).setFormulas(new SAsignFormula(
				"[#Allocating]-[Allocate]", "UnAllocate"));
		columns[0].setGrab(true);
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		SEditTable table = new SEditTable(parent, columns, tableStyle,
				null);
		table.setID(DistributeProcessor.ID_Table_Material);
		table.setLayoutData(GridData.INS_FILL_BOTH);
	}

//	private void renderBottomArea(Composite bottomArea) {
//		GridLayout glBottom = new GridLayout();
//		glBottom.numColumns = 2;
//		bottomArea.setLayout(glBottom);
//		Label memoLabel = new Label(bottomArea);
//		memoLabel.setText("备注：");
//		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING
//				| GridData.HORIZONTAL_ALIGN_END));
//		Text memoText = new Text(bottomArea, JWT.APPEARANCE3);
//		memoText.setID(DistributeProcessor.ID_Text_Remark);
//		GridData gdMemo = new GridData(GridData.FILL_BOTH);
//		memoText.setLayoutData(gdMemo);
//		memoText.dispose();
//	}
	
	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE2);
		button.setText(" 重新分配 ");
		button.setID(DistributeProcessor.ID_Button_Reset);
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确定 ");
		button.setID(DistributeProcessor.ID_Button_Confirm);
	}
}
