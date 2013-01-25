package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.GoodsCheckInItem;

public class GoodsChecinSummaryRender extends AbstractSummaryRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[13];
		columns[0] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.department.name(), 100, 
				JWT.LEFT, GoodsChecinSummaryProcessor.ColumnName.department.getTitle());
		columns[1] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.produceSheetNo.name(), 150, 
				JWT.LEFT, GoodsChecinSummaryProcessor.ColumnName.produceSheetNo.getTitle());
		columns[2] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, GoodsChecinSummaryProcessor.ColumnName.sheetNo.getTitle());
		columns[3] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.goodsCode.name(), 150, 
				JWT.LEFT, GoodsChecinSummaryProcessor.ColumnName.goodsCode.getTitle());
		columns[4] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.goodsName.name(), 100, 
				JWT.LEFT, GoodsChecinSummaryProcessor.ColumnName.goodsName.getTitle());
		columns[5] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.isNeedProduce.name(), 120, 
				JWT.CENTER, GoodsChecinSummaryProcessor.ColumnName.isNeedProduce.getTitle());
		columns[6] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.unit.name(), 100, 
				JWT.CENTER, GoodsChecinSummaryProcessor.ColumnName.unit.getTitle());
		columns[7] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.cost.name(), 100, 
				JWT.RIGHT, GoodsChecinSummaryProcessor.ColumnName.cost.getTitle());
		columns[8] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.count.name(), 100, 
				JWT.RIGHT, GoodsChecinSummaryProcessor.ColumnName.count.getTitle());
		columns[9] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.amount.name(), 100, 
				JWT.RIGHT, GoodsChecinSummaryProcessor.ColumnName.amount.getTitle());
		columns[10] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.createDate.name(), 120, 
				JWT.CENTER, GoodsChecinSummaryProcessor.ColumnName.createDate.getTitle());
		columns[11] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.standardCost.name(), 100, 
				JWT.RIGHT, GoodsChecinSummaryProcessor.ColumnName.standardCost.getTitle());
		columns[12] = new STableColumn(GoodsChecinSummaryProcessor.ColumnName.standardAmount.name(), 100, 
				JWT.RIGHT, GoodsChecinSummaryProcessor.ColumnName.standardAmount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		GoodsCheckInItem item = (GoodsCheckInItem)element;
		switch(columnIndex) {
		case 0:
			return item.getDepartment();
		case 1:
			return item.getProduceSheetNo();
		case 2:
			return item.getSheetNo();
		case 3:
			return item.getGoodsCode();
		case 4:
			return item.getGoodsName();
		case 5:
			return item.isNeedProduce() ? "ÊÇ" : "·ñ";
		case 6:
			return item.getUnit();
		case 7:
			return DoubleUtil.getRoundStr(item.getCost());
		case 8:
			return DoubleUtil.getRoundStr(item.getCount());
		case 9:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 10:
			return DateUtil.dateFromat(item.getCreateDate());
		case 11:
			return DoubleUtil.getRoundStr(item.getStandardCost());
		case 12:
			return DoubleUtil.getRoundStr(item.getStandardAmount());
		}
		return null;
	}

}
