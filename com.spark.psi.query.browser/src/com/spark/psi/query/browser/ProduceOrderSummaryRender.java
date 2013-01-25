package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.query.intf.publish.entity.ProduceItem;

public class ProduceOrderSummaryRender extends AbstractSummaryRender {
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.billsNo.name(), 150, 
				JWT.LEFT, ProduceOrderSummaryProcessor.ColumnName.billsNo.getTitle());
		columns[1] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.goodsCode.name(), 150, 
				JWT.LEFT, ProduceOrderSummaryProcessor.ColumnName.goodsCode.getTitle());
		columns[2] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.goodsName.name(), 150, 
				JWT.LEFT, ProduceOrderSummaryProcessor.ColumnName.goodsName.getTitle());
		columns[3] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.unit.name(), 150, 
				JWT.CENTER, ProduceOrderSummaryProcessor.ColumnName.unit.getTitle());
		columns[4] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.count.name(), 150, 
				JWT.RIGHT, ProduceOrderSummaryProcessor.ColumnName.count.getTitle());
		columns[5] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.createDate.name(), 150, 
				JWT.CENTER, ProduceOrderSummaryProcessor.ColumnName.createDate.getTitle());
		columns[6] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.planDate.name(), 150, 
				JWT.CENTER, ProduceOrderSummaryProcessor.ColumnName.planDate.getTitle());
		columns[7] = new STableColumn(ProduceOrderSummaryProcessor.ColumnName.finishedCount.name(), 150, 
				JWT.RIGHT, ProduceOrderSummaryProcessor.ColumnName.finishedCount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ProduceItem item = (ProduceItem)element;
		switch(columnIndex) {
		case 0:
			return item.getBillsNo();
		case 1:
			return item.getGoodsCode();
		case 2:
			return item.getGoodsName();
		case 3:
			return item.getUnit();
		case 4:
			return DoubleUtil.getRoundStr(item.getCount());
		case 5:
			return DateUtil.dateFromat(item.getCreateDate());
		case 6:
			return DateUtil.dateFromat(item.getPlanDate());
		case 7:
			return StableUtil.toLink(ProduceOrderSummaryProcessor.ID_ViewDetail, "", DoubleUtil.getRoundStr(item.getFinishedCount()));
		}
		return null;
	}

}
