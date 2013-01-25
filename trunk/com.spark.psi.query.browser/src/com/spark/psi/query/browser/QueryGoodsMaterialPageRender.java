package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem.MaterialsItem;

public class QueryGoodsMaterialPageRender extends BaseFormPageRender {

	
	@Override
	protected boolean customizeButtonLayout() {
		return true;
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		GridLayout gl = new GridLayout();
		gl.marginTop = 5;
		gl.numColumns = 3;
		buttonArea.setLayout(gl);
		GridData gdbtn = new GridData(GridData.FILL_HORIZONTAL);
		gdbtn.heightHint = 32;
		buttonArea.setLayoutData(gdbtn);
		
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		Button button = new Button(buttonArea, JWT.APPEARANCE2);
		button.setText(" 选择商品 ");
		button.setID(QueryGoodsMaterialPageProcessor.ID_Button_Goods);
		button.setLayoutData(gd);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 计算材料 ");
		button.setID(QueryGoodsMaterialPageProcessor.ID_Button_Calculate);
		button.setLayoutData(gd);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 重 置 ");
		button.setID(QueryGoodsMaterialPageProcessor.ID_Button_Reset);
		GridData gdReset = new GridData(GridData.FILL_VERTICAL | GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		button.setLayoutData(gdReset);
	}

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
		GridData gd = new GridData(GridData.FILL_BOTH);
//		gd.widthHint = 500;
		leftArea.setLayoutData(gd);

		Composite emptyArea = new Composite(formArea);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 1;
		emptyArea.setLayoutData(gd);

		Composite rightArea = new Composite(formArea);
		rightArea.setLayoutData(GridData.INS_FILL_BOTH);
		
		renderLeftArea(leftArea);
		renderRightArea(rightArea);
	}

	private void renderLeftArea(Composite leftArea) {
		leftArea.setLayout(new GridLayout());
		Label label = new Label(leftArea);
		label.setText("请选择商品：");
		SActionInfo[] actions = new SActionInfo[] {
				PSIActionCommon.getActionInfo(Action.Delete.name()),
		};
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		SEditTable goodsTable = new SEditTable(leftArea, getGoodsTableColumns(), tableStyle, actions);
		goodsTable.setLayoutData(GridData.INS_FILL_BOTH);
		goodsTable.setID(QueryGoodsMaterialPageProcessor.ID_Table_Goods);
		goodsTable.setLabelProvider(new GoodsTableLabelProvider());
	}
	
	private void renderRightArea(Composite rightArea) {
		rightArea.setLayout(new GridLayout());
		Label label = new Label(rightArea);
		label.setText("所需材料信息：");
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		STable materialTable = new STable(rightArea, getMaterialTableColumns(), tableStyle);
		materialTable.setLayoutData(GridData.INS_FILL_BOTH);
		materialTable.setID(QueryGoodsMaterialPageProcessor.ID_Table_Material);
		materialTable.setLabelProvider(new MaterialTableLabelProvider());
	}
	
	private STableColumn[] getGoodsTableColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(QueryGoodsMaterialPageProcessor.GoodsTableName.goodsName.name(), 120,
				JWT.LEFT, QueryGoodsMaterialPageProcessor.GoodsTableName.goodsName.getTitle());
		columns[1] = new STableColumn(QueryGoodsMaterialPageProcessor.GoodsTableName.spec.name(), 120,
				JWT.LEFT, QueryGoodsMaterialPageProcessor.GoodsTableName.spec.getTitle());
		columns[2] = new STableColumn(QueryGoodsMaterialPageProcessor.GoodsTableName.unit.name(), 100,
				JWT.CENTER, QueryGoodsMaterialPageProcessor.GoodsTableName.unit.getTitle());
		columns[3] = new SNumberEditColumn(QueryGoodsMaterialPageProcessor.GoodsTableName.count.name(), 120,
				JWT.RIGHT, QueryGoodsMaterialPageProcessor.GoodsTableName.count.getTitle());
		((SNumberEditColumn)columns[3]).setDecimal(2);
		
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		return columns;
	}
	
	private STableColumn[] getMaterialTableColumns() {
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn(QueryGoodsMaterialPageProcessor.MaterialTableName.name.name(), 120,
				JWT.LEFT, QueryGoodsMaterialPageProcessor.MaterialTableName.name.getTitle());
		columns[1] = new STableColumn(QueryGoodsMaterialPageProcessor.MaterialTableName.unit.name(), 100,
				JWT.CENTER, QueryGoodsMaterialPageProcessor.MaterialTableName.unit.getTitle());
		columns[2] = new STableColumn(QueryGoodsMaterialPageProcessor.GoodsTableName.count.name(), 120,
				JWT.RIGHT, QueryGoodsMaterialPageProcessor.MaterialTableName.count.getTitle());
		return columns;
	}

	private class GoodsTableLabelProvider implements SLabelProvider, SNumberLabelProvider {

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getText(Object element, int columnIndex) {
			GoodsItemInfo item = (GoodsItemInfo)element;
			switch(columnIndex) {
			case 0:
				return item.getBaseInfo().getName();
			case 1:
				return item.getItemData().getSpec();
			case 2:
				return item.getItemData().getUnit();
			}
			return null;
		}

		@Override
		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		@Override
		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}
		
	}
	
	private class MaterialTableLabelProvider implements SLabelProvider {

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getText(Object element, int columnIndex) {
			MaterialsItem item = (MaterialsItem)element;
			switch(columnIndex) {
			case 0:
				return item.getMaterialName();
			case 1:
				return item.getUnit();
			case 2:
				return DoubleUtil.getRoundStr(item.getCount(), 2);
			}
			return null;
		}

		@Override
		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}
}
