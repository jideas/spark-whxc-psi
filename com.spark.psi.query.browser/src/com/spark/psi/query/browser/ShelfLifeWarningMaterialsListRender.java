package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.inventory.entity.ShelfLifeWarningMaterialsItem;

public class ShelfLifeWarningMaterialsListRender extends AbstractShelfLifeWarningRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[11];
		columns[0] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.storeName.name(), 150, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.storeName.getTitle());
		columns[1] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.materialName.name(), 150, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.materialName.getTitle());
		columns[2] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.materialCode.name(), 120, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.materialCode.getTitle());
		columns[3] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.materailNo.name(), 120, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.materailNo.getTitle());
		columns[4] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.spec.name(), 100, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.spec.getTitle());
		columns[5] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.unit.name(), 80, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.unit.getTitle());
		columns[6] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.count.name(), 120, 
				JWT.RIGHT, ShelfLifeWarningMaterialsListProcessor.ColumnName.count.getTitle());
		columns[7] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLifeWarningType.name(), 80, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLifeWarningType.getTitle());
		columns[8] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.produceDate.name(), 100, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.produceDate.getTitle());
		columns[9] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLife.name(), 80, 
				JWT.RIGHT, ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLife.getTitle());
		columns[10] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.warningDay.name(), 80, 
				JWT.RIGHT, ShelfLifeWarningMaterialsListProcessor.ColumnName.warningDay.getTitle());
		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ShelfLifeWarningMaterialsItem item = (ShelfLifeWarningMaterialsItem)element;
		switch(columnIndex) {
		case 0:
			return item.getStoreName();
		case 1:
			return item.getMaterialName();
		case 2:
			return item.getMaterialCode();
		case 3:
			return item.getMaterialNo();
		case 4:
			return item.getMaterialSpec();
		case 5:
			return item.getMaterialUnit();
		case 6:
			return DoubleUtil.getRoundStr(item.getCount());
		case 7:
			return item.getShelfLifeWarningType().getName();
		case 8:
			return DateUtil.dateFromat(item.getProduceDate());
		case 9:
			return item.getShelfLife()+"";
		case 10:
			return item.getWarningDay()+"";
		}
		return null;
	}

}
