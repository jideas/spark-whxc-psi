package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.TicketItem;

public class GoodsDeliverSummaryRender extends AbstractSummaryRender {
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[11];
		columns[0] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.memberRealName.name(), 100, 
				JWT.LEFT, GoodsDeliverSummaryProcessor.ColumnName.memberRealName.getTitle());
		columns[1] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, GoodsDeliverSummaryProcessor.ColumnName.sheetNo.getTitle());
		columns[2] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.createDate.name(), 100, 
				JWT.CENTER, GoodsDeliverSummaryProcessor.ColumnName.createDate.getTitle());
		columns[3] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.deliverTicketType.name(), 100, 
				JWT.CENTER, GoodsDeliverSummaryProcessor.ColumnName.deliverTicketType.getTitle());
		columns[4] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.goodsCode.name(), 150, 
				JWT.LEFT, GoodsDeliverSummaryProcessor.ColumnName.goodsCode.getTitle());
		columns[5] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.goodsNo.name(), 150, 
				JWT.LEFT, GoodsDeliverSummaryProcessor.ColumnName.goodsNo.getTitle());
		columns[6] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.goodsName.name(), 120, 
				JWT.LEFT, GoodsDeliverSummaryProcessor.ColumnName.goodsName.getTitle());
		columns[7] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.unit.name(), 100, 
				JWT.CENTER, GoodsDeliverSummaryProcessor.ColumnName.unit.getTitle());
		columns[8] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.count.name(), 100, 
				JWT.RIGHT, GoodsDeliverSummaryProcessor.ColumnName.count.getTitle());
		columns[9] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.price.name(), 100, 
				JWT.RIGHT, GoodsDeliverSummaryProcessor.ColumnName.price.getTitle());
		columns[10] = new STableColumn(GoodsDeliverSummaryProcessor.ColumnName.amount.name(), 100, 
				JWT.RIGHT, GoodsDeliverSummaryProcessor.ColumnName.amount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		TicketItem item = (TicketItem)element;
		switch(columnIndex) {
		case 0:
			return item.getMemberRealName();
		case 1:
			return item.getSheetNo();
		case 2:
			return DateUtil.dateFromat(item.getCreateDate());
		case 3:
			return item.getDeliverTicketType().getName();
		case 4:
			return item.getGoodsCode();
		case 5:
			return item.getGoodsNo();
		case 6:
			return item.getGoodsName();
		case 7:
			return item.getUnit();
		case 8:
			return DoubleUtil.getRoundStr(item.getCount());
		case 9:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 10:
			return DoubleUtil.getRoundStr(item.getAmount());
		}
		return null;
	}

}
