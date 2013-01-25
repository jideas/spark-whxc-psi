package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.SalesItem;

public class SalesOrderSummaryRender extends AbstractSummaryRender {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[15];
		columns[0] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.customerNo.name(), 120, 
				JWT.LEFT, SalesOrderSummaryProcessor.ColumnName.customerNo.getTitle());
		columns[1] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.customerName.name(), 120, 
				JWT.LEFT, SalesOrderSummaryProcessor.ColumnName.customerName.getTitle());
		columns[2] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.sheetNo.name(), 120, 
				JWT.LEFT, SalesOrderSummaryProcessor.ColumnName.sheetNo.getTitle());
		columns[3] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.createDate.name(), 120, 
				JWT.CENTER, SalesOrderSummaryProcessor.ColumnName.createDate.getTitle());
		columns[4] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.deliveryDate.name(), 120, 
				JWT.CENTER, SalesOrderSummaryProcessor.ColumnName.deliveryDate.getTitle());
		columns[5] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.goodsCode.name(), 120, 
				JWT.LEFT, SalesOrderSummaryProcessor.ColumnName.goodsCode.getTitle());
		columns[6] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.goodsNo.name(), 120, 
				JWT.LEFT, SalesOrderSummaryProcessor.ColumnName.goodsNo.getTitle());
		columns[7] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.goodsName.name(), 120, 
				JWT.LEFT, SalesOrderSummaryProcessor.ColumnName.goodsName.getTitle());
		columns[8] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.price.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.price.getTitle());
		columns[9] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.count.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.count.getTitle());
		columns[10] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.amount.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.amount.getTitle());
		columns[11] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.checkedoutCount.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.checkedoutCount.getTitle());
		columns[12] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.checkedoutAmount.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.checkedoutAmount.getTitle());
		columns[13] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.checkoutCount.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.checkoutCount.getTitle());
		columns[14] = new STableColumn(SalesOrderSummaryProcessor.ColumnName.checkoutAmount.name(), 120, 
				JWT.RIGHT, SalesOrderSummaryProcessor.ColumnName.checkoutAmount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		SalesItem item = (SalesItem)element;
		switch(columnIndex) {
		case 0:
			return item.getCustomerNo();
		case 1:
			return item.getCustomerName();
		case 2:
			return item.getBillsNo();
		case 3:
			return DateUtil.dateFromat(item.getCreateDate());
		case 4:
			return DateUtil.dateFromat(item.getDeliveryDate());
		case 5:
			return item.getGoodsCode();
		case 6:
			return item.getGoodsNo();
		case 7:
			return item.getGoodsName();
		case 8:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 9:
			return DoubleUtil.getRoundStr(item.getCount());
		case 10:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 11:
			return DoubleUtil.getRoundStr(item.getCheckedoutCount());
		case 12:
			return DoubleUtil.getRoundStr(item.getCheckedoutAmount());
		case 13:
			return DoubleUtil.getRoundStr(item.getCheckoutCount());
		case 14:
			return DoubleUtil.getRoundStr(item.getCheckoutAmount());
		}
		return null;
	}

}
