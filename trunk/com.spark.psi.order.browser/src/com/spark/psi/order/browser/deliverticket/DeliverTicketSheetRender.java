package com.spark.psi.order.browser.deliverticket;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfoItem;

public class DeliverTicketSheetRender extends SimpleSheetPageRender {

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (0 == row && 0 == column) {
			GridData gd = new GridData();
			gd.widthHint = 120;
			new Label(baseInfoArea).setText("会员：");
			Label lb = new Label(baseInfoArea);
			lb.setID(DeliverTicketSheetProcessor.ID_Label_CustomerName);
			lb.setLayoutData(gd);
			
			new Label(baseInfoArea).setText("手机号码：");
			lb = new Label(baseInfoArea);
			lb.setID(DeliverTicketSheetProcessor.ID_Label_MobileNo);
			lb.setLayoutData(gd);
			new Label(baseInfoArea).setText("门店：");
			lb = new Label(baseInfoArea);
			lb.setID(DeliverTicketSheetProcessor.ID_Label_StationName);
			lb.setLayoutData(gd);
			new Label(baseInfoArea).setText("发货日期：");
			lb = new Label(baseInfoArea);
			lb.setID(DeliverTicketSheetProcessor.ID_Label_DeliverDate);
			lb.setLayoutData(gd);
		}

	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		new Label(dataInfoArea).setText("订单金额：");
		Label lb = new Label(dataInfoArea);
		lb.setID(DeliverTicketSheetProcessor.ID_Label_TotalAmount);
		GridData gd = new GridData();
		gd.widthHint = 35;
		lb.setLayoutData(gd);
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {

	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {

	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {

	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(DeliverTicketSheetProcessor.ColumnName.goodsNo.name(), 150, JWT.LEFT, "商品条码");
		columns[1] = new STableColumn(DeliverTicketSheetProcessor.ColumnName.goodsName.name(), 250, JWT.LEFT, "商品名称");
		columns[2] = new STableColumn(DeliverTicketSheetProcessor.ColumnName.unit.name(), 150, JWT.CENTER, "单位");
		columns[3] = new STableColumn(DeliverTicketSheetProcessor.ColumnName.count.name(), 150, JWT.RIGHT, "数量");
		columns[4] = new STableColumn(DeliverTicketSheetProcessor.ColumnName.price.name(), 150, JWT.RIGHT, "单价");
		columns[5] = new STableColumn(DeliverTicketSheetProcessor.ColumnName.amount.name(), 150, JWT.RIGHT, "金额");
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		DeliverTicketInfoItem item = (DeliverTicketInfoItem)element;
		switch(columnIndex) {
		case 0:
			return item.getGoodsNo();
		case 1:
			return item.getGoodsName();
		case 2:
			return item.getUnit();
		case 3:
			return DoubleUtil.getRoundStr(item.getCount(), item.getGoodsScale());
		case 4:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 5:
			return DoubleUtil.getRoundStr(item.getAmount());
		}
		return null;
	}

}
