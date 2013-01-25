package com.spark.psi.order.browser.online;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfo;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;

public class OnlineOrderDetailRender extends SimpleSheetPageRender {

	private OnlineOrderInfo orderInfo  = null;
	
	@Override
	public void init(Situation context) {
		GUID orderId = (GUID) getArgument();
		orderInfo = getContext().find(OnlineOrderInfo.class, orderId);
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0) {
			if (column == 0) {
				GridData gd = new GridData();
				gd.widthHint = 80;
				Label label = new Label(baseInfoArea);
				label.setText("客户：");
				label = new Label(baseInfoArea);
				label.setID(OnlineOrderDetailProcessor.ID_Label_CustomerName);
				label.setLayoutData(gd);
				
				 gd = new GridData();
				 gd.widthHint = 110;
				
				label = new Label(baseInfoArea);
				label.setText("手机号码：");
				label = new Label(baseInfoArea);
				label.setID(OnlineOrderDetailProcessor.ID_Label_MobileNo);
				label.setLayoutData(gd);
				
				 gd = new GridData();
				 gd.widthHint = 120;
				
				label = new Label(baseInfoArea);
				label.setText("下单日期：");
				label = new Label(baseInfoArea);
				label.setID(OnlineOrderDetailProcessor.ID_Label_BookingDate);
				label.setLayoutData(gd);
				
				label = new Label(baseInfoArea);
				label.setText("交货日期 ：");
				label = new Label(baseInfoArea);
				label.setID(OnlineOrderDetailProcessor.ID_Label_SendDate);
				label.setLayoutData(gd);
				
				label = new Label(baseInfoArea);
				label.setText("送货上门 ：");
				label = new Label(baseInfoArea);
				label.setID(OnlineOrderDetailProcessor.ID_Label_IsToDoor);
				label.setLayoutData(gd);
				
			} else if (column == 1) {
				Label label = new Label(baseInfoArea);
				label.setText("单据状态：");
				label = new Label(baseInfoArea);
				label.setID(OnlineOrderDetailProcessor.ID_Label_Status);
			}
		}
		
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		Label label = new Label(dataInfoArea);
		label.setText("订单金额：");
		label = new Label(dataInfoArea);
		label.setID(OnlineOrderDetailProcessor.ID_Label_TotalAmount);
		GridData gd = new GridData();
		gd.widthHint = 35;
		label.setLayoutData(gd);
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
		Composite hideArea = new Composite(sheetButtonArea);
		GridData gdHide = new GridData();
		gdHide.exclude = true;
		hideArea.setLayoutData(gdHide);
		hideArea.setVisible(false);
		hideArea.setID(OnlineOrderDetailProcessor.ID_Area_Hide);
		
		Button button = null;
		if (OnlineOrderStatus.Picking.getCode().equals(orderInfo.getStatus())) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 拆分订单 ");
			button.setID(OnlineOrderDetailProcessor.ID_Button_Separate);
		} else if (OnlineOrderStatus.Delivering.getCode().equals(orderInfo.getStatus())) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 确认到货 ");
			button.setID(OnlineOrderDetailProcessor.ID_Button_Arrive);
		} else if (OnlineOrderStatus.Arrivaled.getCode().equals(orderInfo.getStatus())) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 确认完成 ");
			button.setID(OnlineOrderDetailProcessor.ID_Button_Finish);
		}
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {

	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.goodsCode.name(), 100, JWT.LEFT, "商品编号");
		columns[1] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.goodsNo.name(), 140, JWT.LEFT, "商品条码");
		columns[2] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.goodsName.name(), 200, JWT.LEFT, "商品名称");
		columns[3] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.goodsSpec.name(), 100, JWT.LEFT, "规格");
		columns[4] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.unit.name(), 100, JWT.CENTER, "单位");
		columns[5] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.count.name(), 130, JWT.RIGHT, "数量");
		columns[6] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.price.name(), 130, JWT.RIGHT, "单价");
		columns[7] = new STableColumn(OnlineOrderDetailProcessor.ColumnName.amount.name(), 130, JWT.RIGHT, "金额");
		columns[2].setGrab(true);
		
//		for (STableColumn column : columns) {
//			column.setGrab(true);
//		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		OnlineOrderInfoItem item = (OnlineOrderInfoItem)element;
		switch(columnIndex) {
		case 0:
			return item.getGoodsCode();
		case 1:
			return item.getGoodsNo();
		case 2:
			return item.getGoodsName();
		case 3:
			return item.getGoodsSpec();
		case 4:
			return item.getUnit();
		case 5:
			return DoubleUtil.getRoundStr(item.getCount(), item.getGoodsScale());
		case 6:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 7:
			return DoubleUtil.getRoundStr(item.getAmount());
		}
		return null;
	}

}
