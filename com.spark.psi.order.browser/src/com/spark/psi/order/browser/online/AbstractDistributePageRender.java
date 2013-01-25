package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;

public abstract class AbstractDistributePageRender extends BaseFormPageRender {

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
		
		renderLeftArea(leftArea);
		renderRightArea(rightArea);
		renderBottomArea(formArea);
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
		label.setID(AbstractDistributePageProcessor.ID_Label_StoreName);
		gd = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER | GridData.FILL_HORIZONTAL);
		label.setLayoutData(gd);

		//
		STableColumn column = new STableColumn(AbstractDistributePageProcessor.StoreTableColumn.name.name(), 100, JWT.LEFT, "仓库名称");
		column.setGrab(true);
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.None);
		STable table = new STable(parent, new STableColumn[] { column },
				tableStyle);
		table.setID(AbstractDistributePageProcessor.ID_Table_Store);
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
		GridLayout glHeader = new GridLayout();
		glHeader.numColumns = 2;
		glHeader.horizontalSpacing = 0;
		header.setLayout(glHeader);
		
		renderRightHeaderArea(header);
		
		renderRightTableArea(parent);
	}
	
	private void renderRightHeaderArea(Composite rightHeaderArea) {
		Composite rHeaderLeft = new Composite(rightHeaderArea);
		GridData gdLeft = new GridData(GridData.FILL_VERTICAL);
		rHeaderLeft.setLayoutData(gdLeft);
		GridLayout glLeft = new GridLayout();
		glLeft.horizontalSpacing = 5;
		glLeft.numColumns = 8;
		rHeaderLeft.setLayout(glLeft);
		
		Composite rHeaderRight = new Composite(rightHeaderArea);
		GridData gdRight = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.FILL_VERTICAL);
		rHeaderRight.setLayoutData(gdRight);
		GridLayout glRight = new GridLayout();
		glRight.horizontalSpacing = 0;
		glRight.numColumns = 8;
		rHeaderRight.setLayout(glRight);

		Label label = new Label(rHeaderLeft);
		label.setText("分配材料：");
		
		renderRHeaderLeft(rHeaderLeft);
		renderRHeaderRight(rHeaderRight);
		
		label = new Label(rHeaderRight);
		label.setID(AbstractDistributePageProcessor.ID_Label_ResolveAll);
		label.setText("剩余材料分配到本库 ");
		label.setForeground(Color.COLOR_BLUE);
		label.setCursor(Cursor.HAND);
	}
	
	private void renderRightTableArea(Composite rightArea) {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(AbstractDistributePageProcessor.MaterialTableColumn.name.name(), 200, JWT.LEFT, "材料名称");
		columns[1] = new STableColumn(AbstractDistributePageProcessor.MaterialTableColumn.unit.name(), 100, JWT.CENTER, "单位");
		columns[2] = new STableColumn(AbstractDistributePageProcessor.MaterialTableColumn.orderCount.name(), 130, JWT.RIGHT, "订货数量");
		columns[3] = new STableColumn(AbstractDistributePageProcessor.MaterialTableColumn.UnAllocate.name(), 130, JWT.RIGHT, "未分配量");
		columns[4] = new STableColumn(AbstractDistributePageProcessor.MaterialTableColumn.Available.name(), 130, JWT.RIGHT, "本库可用");
		columns[5] = new SNumberEditColumn(AbstractDistributePageProcessor.MaterialTableColumn.Allocate.name(), 130, JWT.RIGHT, "本库分配");
		((SNumberEditColumn) columns[5]).setFormulas(new SAsignFormula(
				"[#Allocating]-[Allocate]", "UnAllocate"));
		columns[0].setGrab(true);
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		SEditTable table = new SEditTable(rightArea, columns, tableStyle,
				null);
		table.setID(AbstractDistributePageProcessor.ID_Table_Material);
		table.setLayoutData(GridData.INS_FILL_BOTH);
	}
	
	protected void renderRHeaderLeft(Composite headerLeftArea) {
		
	}

	protected void renderRHeaderRight(Composite headerRightArea) {
		
	}
	
	protected void renderBottomArea(Composite bottomArea) {
		
	}
	
	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE2);
		button.setText(" 重新分配 ");
		button.setID(AbstractDistributePageProcessor.ID_Button_Reset);
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(getConfirmButtonTitle());
		button.setID(AbstractDistributePageProcessor.ID_Button_Confirm);
	}
	
	protected abstract String getConfirmButtonTitle();

}
