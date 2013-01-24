package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.b2c.publish.JointVenture.entity.JointVentureLogItem;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.common.components.table.edit.SEditTableStyle;

public class SupplierJoinGoodsListRender extends PSIListPageRender {

	
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("供应商名称：");
		new Label(headerLeftArea).setID(SupplierJoinGoodsListProcessor.ID_Label_SupplierName);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("记录数量：");
		new Label(headerLeftArea).setID(SupplierJoinGoodsListProcessor.ID_Label_Count);
		
		new SSearchText2(headerRightArea).setID(SupplierJoinGoodsListProcessor.ID_Search);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.materialName.name(), 120, 
				JWT.LEFT, SupplierJoinGoodsListProcessor.ColumnName.materialName.getTitle());
		columns[1] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.materialCode.name(), 150, 
				JWT.LEFT, SupplierJoinGoodsListProcessor.ColumnName.materialCode.getTitle());
		columns[2] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.materialNo.name(), 150, 
				JWT.LEFT, SupplierJoinGoodsListProcessor.ColumnName.materialNo.getTitle());
		columns[3] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.materialUnit.name(), 80, 
				JWT.CENTER, SupplierJoinGoodsListProcessor.ColumnName.materialUnit.getTitle());
		columns[4] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.percentage.name(), 100, 
				JWT.RIGHT, SupplierJoinGoodsListProcessor.ColumnName.percentage.getTitle());
		columns[5] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.beginDate.name(), 120, 
				JWT.CENTER, SupplierJoinGoodsListProcessor.ColumnName.beginDate.getTitle());
		columns[6] = new STableColumn(SupplierJoinGoodsListProcessor.ColumnName.endDate.name(), 120, 
				JWT.CENTER, SupplierJoinGoodsListProcessor.ColumnName.endDate.getTitle());
		
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		JointVentureLogItem item = (JointVentureLogItem)element;
		switch(columnIndex) {
		case 0:
			return item.getMaterialName();
		case 1:
			return item.getMaterialCode();
		case 2:
			return item.getMaterialNo();
		case 3:
			return item.getMaterialUnit();
		case 4:
			return DoubleUtil.getRoundStr(item.getPercentage());
		case 5:
			return DateUtil.dateFromat(item.getBeginDate());
		case 6:
			return DateUtil.dateFromat(item.getEndDate());
		}
		return null;
	}
	public STableStyle getTableStyle() {
		SEditTableStyle style = new SEditTableStyle();
		style.setPageAble(false);
		return style;
	}
}
