package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInItem;

public class MaterialsCheckinSummaryRender extends AbstractSummaryRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[14];
		columns[0] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.partnerNo.name(), 150, 
				JWT.LEFT, MaterialsCheckinSummaryProcessor.ColumnName.partnerNo.getTitle());
		columns[1] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.partnerName.name(), 120, 
				JWT.LEFT, MaterialsCheckinSummaryProcessor.ColumnName.partnerName.getTitle());
		columns[2] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.purchaseSheetNo.name(), 150, 
				JWT.LEFT, MaterialsCheckinSummaryProcessor.ColumnName.purchaseSheetNo.getTitle());
		columns[3] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, MaterialsCheckinSummaryProcessor.ColumnName.sheetNo.getTitle());
		columns[4] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.checkinDate.name(), 120, 
				JWT.CENTER, MaterialsCheckinSummaryProcessor.ColumnName.checkinDate.getTitle());
		columns[5] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.checkingInType.name(), 100, 
				JWT.CENTER, MaterialsCheckinSummaryProcessor.ColumnName.checkingInType.getTitle());
		columns[6] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.goodsCode.name(), 150, 
				JWT.LEFT, MaterialsCheckinSummaryProcessor.ColumnName.goodsCode.getTitle());
		columns[7] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.goodsName.name(), 120, 
				JWT.LEFT, MaterialsCheckinSummaryProcessor.ColumnName.goodsName.getTitle());
		columns[8] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.unit.name(), 100, 
				JWT.CENTER, MaterialsCheckinSummaryProcessor.ColumnName.unit.getTitle());
		columns[9] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.count.name(), 120, 
				JWT.RIGHT, MaterialsCheckinSummaryProcessor.ColumnName.count.getTitle());
		columns[10] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.price.name(), 120, 
				JWT.RIGHT, MaterialsCheckinSummaryProcessor.ColumnName.price.getTitle());
		columns[11] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.amount.name(), 120, 
				JWT.RIGHT, MaterialsCheckinSummaryProcessor.ColumnName.amount.getTitle());
		columns[12] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.standardCost.name(), 120, 
				JWT.RIGHT, MaterialsCheckinSummaryProcessor.ColumnName.standardCost.getTitle());
		columns[13] = new STableColumn(MaterialsCheckinSummaryProcessor.ColumnName.standardAmount.name(), 120, 
				JWT.RIGHT, MaterialsCheckinSummaryProcessor.ColumnName.standardAmount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		MaterialsCheckInItem item = (MaterialsCheckInItem)element;
		switch(columnIndex) {
		case 0:
			return item.getPartnerNo();
		case 1:
			return item.getPartnerName();
		case 2:
			return item.getPurchaseSheetNo();
		case 3:
			return item.getSheetNo();
		case 4:
			return DateUtil.dateFromat(item.getCheckinDate());
		case 5:
			return item.getCheckingInType().getName();
		case 6:
			return item.getGoodsCode();
		case 7:
			return item.getGoodsName();
		case 8:
			return item.getUnit();
		case 9:
			return DoubleUtil.getRoundStr(item.getCount());
		case 10:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 11:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 12:
			return DoubleUtil.getRoundStr(item.getStandardCost());
		case 13:
			return DoubleUtil.getRoundStr(item.getStandardAmount());
		}
		return null;
	}

}
