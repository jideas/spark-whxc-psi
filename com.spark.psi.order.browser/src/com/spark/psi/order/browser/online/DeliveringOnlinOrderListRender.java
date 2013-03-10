package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIMultiItemListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderShowItem;

public class DeliveringOnlinOrderListRender extends PSIMultiItemListPageRender{

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		table.setSpanProvider(this);
		
		GridData gd = new GridData();
		gd.widthHint = 100;
		new Label(headerLeftArea).setText("订单数量：");
		Label lb  =  new Label(headerLeftArea);
		lb.setID(DeliveringOnlinOrderListProcessor.ID_Label_Count);
		lb.setLayoutData(gd);
		
		
		new Label(headerLeftArea).setText("订单金额：");
		lb = new Label(headerLeftArea);
		lb.setID(DeliveringOnlinOrderListProcessor.ID_Label_Amount);
		lb.setLayoutData(gd);
		
		
		SSearchText2 search = new SSearchText2(headerRightArea);
		search.setID(DeliveringOnlinOrderListProcessor.ID_Search);
		
		Button advancedBtn = new Button(headerRightArea, JWT.APPEARANCE3);
		advancedBtn.setText(" 高级搜索 ");
		advancedBtn.setID(DeliveringOnlinOrderListProcessor.ID_Search_Advanced);
		
	}
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[11];
		int index = 0;
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.code.name(), 150, JWT.LEFT, "订单编号");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.goodsName.name(), 150, JWT.LEFT, "商品名称", "订单商品");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.properties.name(), 150, JWT.LEFT, "商品规格", "订单商品");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.count.name(), 150, JWT.RIGHT, "商品数量", "订单商品");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.customerName.name(), 90, JWT.CENTER, "会员");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.amount.name(), 90, JWT.RIGHT, "订单金额");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.isToDoor.name(), 80, JWT.CENTER, "积分商品");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.isToDoor.name(), 80, JWT.CENTER, "送货上门");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.deliverTime.name(), 100, JWT.CENTER, "发货时间");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.deliverPerson.name(), 90, JWT.CENTER, "发货人");
		columns[index++] = new STableColumn(DeliveringOnlinOrderListProcessor.ColumnName.station.name(), 130, JWT.LEFT, "站点");
		
		columns[10].setGrab(true);
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
			return order.getTotalAmount()==0&&order.getVantagesCost()>0?"是":"否";
		case 7:
			return order.isToDoor()?"是":"否";
		case 8:
			return DateUtil.dateFromat(order.getDeliverDate(), DateUtil.DATE_TIME_PATTERN);
		case 9:
			return order.getConsignor();
		case 10:
			return order.getStationName();
		}
		return null;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		OnlineOrderShowItem order = (OnlineOrderShowItem)element;
		switch(columnIndex) {
		case 8:
			return DateUtil.dateFromat(order.getDeliverDate(), DateUtil.DATE_TIME_PATTERN);
		default:
			return null;
		}
	}
	
	public int getColSpan(Object element, int columnIndex) {
		// TODO Auto-generated method stub
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
		case 10:
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
		case 9:
			return true;
		default:
			return false;
		}
	}
	
	protected SSelectionMode getSelectionMode() {
		return SSelectionMode.None;
	}

}
