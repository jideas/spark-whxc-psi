package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.inventory.entity.ShelfLifeWarningMaterialsItem;

public class ShelfLifeWarningMaterialsListRender extends AbstractShelfLifeWarningRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[13];
		int index = 0 ;
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.storeName.name(), 150, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.storeName.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfNo.name(), 40, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfNo.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.tiersNo.name(), 40, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.tiersNo.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.materialName.name(), 150, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.materialName.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.materialCode.name(), 120, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.materialCode.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.materailNo.name(), 120, 
				JWT.LEFT, ShelfLifeWarningMaterialsListProcessor.ColumnName.materailNo.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.spec.name(), 100, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.spec.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.unit.name(), 80, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.unit.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.count.name(), 120, 
				JWT.RIGHT, ShelfLifeWarningMaterialsListProcessor.ColumnName.count.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLifeWarningType.name(), 80, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLifeWarningType.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.produceDate.name(), 100, 
				JWT.CENTER, ShelfLifeWarningMaterialsListProcessor.ColumnName.produceDate.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLife.name(), 80, 
				JWT.RIGHT, ShelfLifeWarningMaterialsListProcessor.ColumnName.shelfLife.getTitle());
		columns[index++] = new STableColumn(ShelfLifeWarningMaterialsListProcessor.ColumnName.warningDay.name(), 80, 
				JWT.RIGHT, ShelfLifeWarningMaterialsListProcessor.ColumnName.warningDay.getTitle());
		columns[3].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ShelfLifeWarningMaterialsItem item = (ShelfLifeWarningMaterialsItem)element;
		switch(columnIndex) {
		case 0:
			return item.getStoreName();
		case 1:
			return item.getShelfNo()+"";
		case 2:
			return item.getTiersNo()+"";
		case 3:
			return item.getMaterialName();
		case 4:
			return item.getMaterialCode();
		case 5:
			return item.getMaterialNo();
		case 6:
			return item.getMaterialSpec();
		case 7:
			return item.getMaterialUnit();
		case 8:
			return DoubleUtil.getRoundStr(item.getCount());
		case 9:
			return item.getShelfLifeWarningType().getName();
		case 10:
			return DateUtil.dateFromat(item.getProduceDate());
		case 11:
			return item.getShelfLife()+"";
		case 12:
			return item.getWarningDay()+"";
		}
		return null;
	}

}
