package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.PurchaseItem;

public class PurchaseOrderSummaryRender extends AbstractSummaryRender {

	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[17];
		columns[0] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.supplierNo.name(), 120, 
				JWT.LEFT, PurchaseOrderSummaryProcessor.ColumnName.supplierNo.getTitle());
		columns[1] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.supplierName.name(), 100, 
				JWT.LEFT, PurchaseOrderSummaryProcessor.ColumnName.supplierName.getTitle());
		columns[2] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.billsNo.name(), 140, 
				JWT.LEFT, PurchaseOrderSummaryProcessor.ColumnName.billsNo.getTitle());
		columns[3] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.materialCode.name(), 120, 
				JWT.LEFT, PurchaseOrderSummaryProcessor.ColumnName.materialCode.getTitle());
		columns[4] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.materialName.name(), 100, 
				JWT.LEFT, PurchaseOrderSummaryProcessor.ColumnName.materialName.getTitle());
		columns[5] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.unit.name(), 80, 
				JWT.CENTER, PurchaseOrderSummaryProcessor.ColumnName.unit.getTitle());
		columns[6] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.count.name(), 120, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.count.getTitle());
		columns[7] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.price.name(), 100, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.price.getTitle());
		columns[8] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.standardPrice.name(), 100, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.standardPrice.getTitle());
		columns[9] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.amount.name(), 120, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.amount.getTitle());
		columns[10] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.checkedinCount.name(), 120, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.checkedinCount.getTitle());
		columns[11] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.checkedinAmount.name(), 120, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.checkedinAmount.getTitle());
		columns[12] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.checkinCount.name(), 120, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.checkinCount.getTitle());
		columns[13] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.checkinAmount.name(), 120, 
				JWT.RIGHT, PurchaseOrderSummaryProcessor.ColumnName.checkinAmount.getTitle());
		columns[14] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.createDate.name(), 120, 
				JWT.CENTER, PurchaseOrderSummaryProcessor.ColumnName.createDate.getTitle());
		columns[15] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.deliveryDate.name(), 120, 
				JWT.CENTER, PurchaseOrderSummaryProcessor.ColumnName.deliveryDate.getTitle());
		columns[16] = new STableColumn(PurchaseOrderSummaryProcessor.ColumnName.status.name(), 80, 
				JWT.CENTER, PurchaseOrderSummaryProcessor.ColumnName.status.getTitle());
		
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		PurchaseItem item = (PurchaseItem)element;
		switch(columnIndex) {
		case 0:
			return item.getSupplierNo();
		case 1:
			return item.getSupplierName();
		case 2:
			return item.getBillsNo();
		case 3:
			return item.getGoodsCode();
		case 4:
			return item.getGoodsName();
		case 5:
			return item.getUnit();
		case 6:
			return DoubleUtil.getRoundStr(item.getCount());
		case 7:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 8:
			return DoubleUtil.getRoundStr(item.getStandardPrice());
		case 9:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 10:
			return DoubleUtil.getRoundStr(item.getCheckedinCount());
		case 11:
			return DoubleUtil.getRoundStr(item.getCheckedinAmount());
		case 12:
			return DoubleUtil.getRoundStr(item.getCheckinCount());
		case 13:
			return DoubleUtil.getRoundStr(item.getCheckinAmount());
		case 14:
			return DateUtil.dateFromat(item.getCreateDate());
		case 15:
			return DateUtil.dateFromat(item.getDeliveryDate());
		case 16:
			return item.getStatus().getName();
		default:
			return null;
		}
	}

}
