package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIMultiItemListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderShowItem;

public class PickingOnlineOrderListRender extends PSIMultiItemListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		table.setSpanProvider(this);
		GridData gd = new GridData();
		gd.widthHint = 100;
		new Label(headerLeftArea).setText("订单数量：");
		Label lb = new Label(headerLeftArea);
		lb.setID(PickingOnlineOrderListProcessor.ID_Label_Count);
		lb.setLayoutData(gd);
		
		new Label(headerLeftArea).setText("订单金额：");
		Label label = new Label(headerLeftArea);
		label.setID(PickingOnlineOrderListProcessor.ID_Label_Amount);
		label.setLayoutData(gd);
		
		SSearchText2 search = new SSearchText2(headerRightArea);
		search.setID(PickingOnlineOrderListProcessor.ID_Search);
		
		Button advancedBtn = new Button(headerRightArea, JWT.APPEARANCE3);
		advancedBtn.setText(" 高级搜索 ");
		advancedBtn.setID(PickingOnlineOrderListProcessor.ID_Search_Advanced);
		
		Button distributeBtn = new Button(footerRightArea, JWT.APPEARANCE3);
		distributeBtn.setText(" 配 送 ");
		distributeBtn.setID(PickingOnlineOrderListProcessor.ID_Button_Distribute);
		
		Button printButton = new Button(footerRightArea, JWT.APPEARANCE3);
		printButton.setText(" 打 印 ");
		printButton.setID(PickingOnlineOrderListProcessor.ID_Button_Print);
		
		Composite hideArea = new Composite(footerRightArea);
		GridData gdHide = new GridData();
		gdHide.exclude = true;
		hideArea.setLayoutData(gdHide);
		hideArea.setVisible(false);
		hideArea.setID(PickingOnlineOrderListProcessor.ID_Area_Hide);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[10];
		int index = 0;
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.code.name(), 145, JWT.LEFT, "订单编号");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.goodsName.name(), 140, JWT.LEFT, "商品名称", "订单商品");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.properties.name(), 140, JWT.LEFT, "商品规格", "订单商品");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.count.name(), 140, JWT.RIGHT, "商品数量", "订单商品");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.customerName.name(), 90, JWT.CENTER, "会员");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.amount.name(), 90, JWT.RIGHT, "订单金额");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.isToDoor.name(), 80, JWT.CENTER, "送货上门");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.bookingTime.name(), 110, JWT.CENTER, "下单时间");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.deliveredTime.name(), 110, JWT.CENTER, "交货时间");
		columns[index++] = new STableColumn(PickingOnlineOrderListProcessor.ColumnName.station.name(), 120, JWT.LEFT, "站点");
		
		columns[9].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		OnlineOrderShowItem order = (OnlineOrderShowItem)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", order.getBillsNo());
		case 1:
			return order.getGoodsName();
		case 2:
			return order.getGoodsSpec();
		case 3:
			return DoubleUtil.getRoundStr(order.getCount());
		case 4:
			return order.getRealName();
		case 5:
			return DoubleUtil.getRoundStr(order.getTotalAmount());
		case 6:
			return order.isToDoor()?"是":"否";
		case 7:
			return DateUtil.dateFromat(order.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
		case 8:
			return DateUtil.dateFromat(order.getDeliverDate(), DateUtil.DATE_TIME_PATTERN);
		case 9:
			return order.getStationName();
		}
		return null;
	}
	
	@Override
	public String getToolTipText(Object element, int columnIndex) {
		OnlineOrderShowItem order = (OnlineOrderShowItem)element;
		switch(columnIndex) {
		case 6:
			return DateUtil.dateFromat(order.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
		case 7:
			return DateUtil.dateFromat(order.getDeliverDate(), DateUtil.DATE_TIME_PATTERN);
		default:
			return null;
		}
	}

	public int getColSpan(Object element, int columnIndex) {
		return 0;
	}

	public int getRowSpan(Object element, int columnIndex) {
		OnlineOrderShowItem order = (OnlineOrderShowItem)element;
		switch(columnIndex) {
		case 0:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return order.getRowSpan();
		default:
			return 0;
		}
	}
	
	public boolean isSpanAlready(Object element, int columnIndex) {
		OnlineOrderShowItem order = (OnlineOrderShowItem)element;
		if (order.isFirstItem()) return false;
		switch(columnIndex) {
		case 0:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			return true;
		default:
			return false;
		}
	}


}
