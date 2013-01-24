package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;

public class MaterialListPageRender extends PSIGoodsListPageRender {

	private boolean isShowJoint = false;
	
	@Override
	public void init(Situation situation) {
		if (null != getArgument3()) {
			isShowJoint = (Boolean) getArgument3();
		}
	}
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		//
		new Label(headerLeftArea).setText("材料数量：");
		Label label = new Label(headerLeftArea);
		label.setID(MaterialListPageProcessor.ID_Label_Count);
		GridData gdLabel = new GridData();
		gdLabel.widthHint = 120;
		label.setLayoutData(gdLabel);
		new Label(headerLeftArea).setText("  ");

		//
		Button newgoodsButton = new Button(footerLeftArea, JWT.APPEARANCE2);
		newgoodsButton.setID(MaterialListPageProcessor.ID_Button_New);
		newgoodsButton.setText(" 新增材料 ");
		new Label(footerLeftArea).setText("  ");
		Button stateButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		stateButton.setID(MaterialListPageProcessor.ID_Button_State);
		new Label(footerLeftArea).setText("  ");
		Button delButton = new Button(footerLeftArea, JWT.APPEARANCE3);
		delButton.setID(MaterialListPageProcessor.ID_Button_Delete);
		delButton.setText(" 删除材料 ");
	}
	
	@Override
	public STableColumn[] getColumns() {
		if (isShowJoint) {
			STableColumn[] columns = new STableColumn[7];
			columns[0] = new STableColumn(MaterialListPageProcessor.ColumnName.supplierName.name(), 120, JWT.LEFT, "供应商名称");
			columns[1] = new STableColumn(MaterialListPageProcessor.ColumnName.percentage.name(), 120, JWT.RIGHT, "提成比例");
			columns[2] = new STableColumn(MaterialListPageProcessor.ColumnName.code.name(), 150, JWT.LEFT, "材料编号");
			columns[3] = new STableColumn(MaterialListPageProcessor.ColumnName.number.name(), 150, JWT.LEFT, "材料条码");
			columns[4] = new STableColumn(MaterialListPageProcessor.ColumnName.name.name(), 120, JWT.LEFT, "材料名称");
			columns[5] = new STableColumn(MaterialListPageProcessor.ColumnName.unit.name(), 80, JWT.CENTER, "单位");
//			columns[6] = new STableColumn(MaterialListPageProcessor.ColumnName.avgPrice.name(), 100, JWT.RIGHT, "平均采购价格");
//			columns[7] = new STableColumn(MaterialListPageProcessor.ColumnName.planPrice.name(), 100, JWT.RIGHT, "计划价格");
			columns[6] = new STableColumn(MaterialListPageProcessor.ColumnName.standardPrice.name(), 90, JWT.RIGHT, "销售价格");
			
			columns[4].setGrab(true);
			columns[2].setSortable(true);
			columns[4].setSortable(true);
			return columns;
		} else {
			STableColumn[] columns = new STableColumn[7];
			columns[0] = new STableColumn(MaterialListPageProcessor.ColumnName.code.name(), 150, JWT.LEFT, "材料编号");
			columns[1] = new STableColumn(MaterialListPageProcessor.ColumnName.number.name(), 150, JWT.LEFT, "材料条码");
			columns[2] = new STableColumn(MaterialListPageProcessor.ColumnName.name.name(), 120, JWT.LEFT, "材料名称");
			columns[3] = new STableColumn(MaterialListPageProcessor.ColumnName.unit.name(), 70, JWT.CENTER, "单位");
			columns[4] = new STableColumn(MaterialListPageProcessor.ColumnName.avgPrice.name(), 90, JWT.RIGHT, "平均采购价格");
			columns[5] = new STableColumn(MaterialListPageProcessor.ColumnName.planPrice.name(), 90, JWT.RIGHT, "计划价格");
			columns[6] = new STableColumn(MaterialListPageProcessor.ColumnName.standardPrice.name(), 90, JWT.RIGHT, "标准价格");
			
			columns[2].setGrab(true);
			columns[0].setSortable(true);
			columns[2].setSortable(true);
			return columns;
		}
		
	}

	@Override
	public String getText(Object element, int columnIndex) {
		MaterialsInfo materialInfo = (MaterialsInfo)element;
		if (isShowJoint) {
			switch(columnIndex) {
			case 0:
				return materialInfo.getSupplier();
			case 1:
				return DoubleUtil.getRoundStr(materialInfo.getPercentage());
			case 2:
				return materialInfo.getCode();
			case 3:
				return materialInfo.getItems()[0].getMaterialNo();
			case 4:
				return StableUtil.toLink(Action.Detail.name(), "", materialInfo.getName());
			case 5:
				return materialInfo.getItems()[0].getUnit();
			case 6:
				return DoubleUtil.getRoundStr(materialInfo.getItems()[0].getSalePrice());
			}
		} else {
			switch(columnIndex) {
			case 0:
				return materialInfo.getCode();
			case 1:
				return materialInfo.getItems()[0].getMaterialNo();
			case 2:
				return StableUtil.toLink(Action.Detail.name(), "", materialInfo.getName());
			case 3:
				return materialInfo.getItems()[0].getUnit();
			case 4:
				return DoubleUtil.getRoundStr(materialInfo.getItems()[0].getAvgBuyPrice());
			case 5:
				return DoubleUtil.getRoundStr(materialInfo.getItems()[0].getPlanPrice());
			case 6:
				return DoubleUtil.getRoundStr(materialInfo.getItems()[0].getStandardPrice());
			}
		}
		return null;
	}
	
	
	@Override
	public String getToolTipText(Object element, int columnIndex) {
		MaterialsInfo materialInfo = (MaterialsInfo)element;
		if (isShowJoint) {
			switch(columnIndex) {
			case 0:
				return materialInfo.getSupplier();
			case 4:
				return materialInfo.getName();
			}
		} else {
			switch(columnIndex) {
			case 2:
				return materialInfo.getName();
			}
		}
		return null;
	}

	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setSelectionMode(SSelectionMode.Multi);
		tableStyle.setRowHeight(24);
		return tableStyle;
	}

}
