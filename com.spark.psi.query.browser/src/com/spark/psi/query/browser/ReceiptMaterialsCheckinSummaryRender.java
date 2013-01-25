package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckOutItem;

public class ReceiptMaterialsCheckinSummaryRender extends AbstractSummaryRender {
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[11];
		columns[0] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.department.name(), 100, 
				JWT.LEFT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.department.getTitle());
		columns[1] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.produceSheetNo.name(), 150, 
				JWT.LEFT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.produceSheetNo.getTitle());
		columns[2] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.sheetNo.getTitle());
		columns[3] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.createDate.name(), 100, 
				JWT.CENTER, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.createDate.getTitle());
		columns[4] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.checkingOutType.name(), 100, 
				JWT.CENTER, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.checkingOutType.getTitle());
		columns[5] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.materialNo.name(), 120, 
				JWT.LEFT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.materialNo.getTitle());
		columns[6] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.materialName.name(), 100, 
				JWT.LEFT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.materialName.getTitle());
		columns[7] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.unit.name(), 100, 
				JWT.CENTER, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.unit.getTitle());
		columns[8] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.count.name(), 100, 
				JWT.RIGHT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.count.getTitle());
		columns[9] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.cost.name(), 100, 
				JWT.RIGHT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.cost.getTitle());
		columns[10] = new STableColumn(ReceiptMaterialsCheckinSummaryProcessor.ColumnName.amount.name(), 100, 
				JWT.RIGHT, ReceiptMaterialsCheckinSummaryProcessor.ColumnName.amount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		MaterialsCheckOutItem item = (MaterialsCheckOutItem)element;
		switch(columnIndex) {
		case 0:
			return item.getDepartment();
		case 1:
			return item.getProduceSheetNo();
		case 2:
			return item.getSheetNo();
		case 3:
			return DateUtil.dateFromat(item.getCreateDate());
		case 4:
			return item.getCheckingOutType().getName();
		case 5:
			return item.getGoodsCode();
		case 6:
			return item.getGoodsName();
		case 7:
			return item.getUnit();
		case 8:
			return DoubleUtil.getRoundStr(item.getCount());
		case 9:
			return DoubleUtil.getRoundStr(item.getCost());
		case 10:
			return DoubleUtil.getRoundStr(item.getAmount());
		}
		return null;
	}

}
