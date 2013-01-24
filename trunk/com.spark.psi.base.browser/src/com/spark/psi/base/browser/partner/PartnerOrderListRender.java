package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.order.entity.OrderItem;

/**
 * 客户供应商订单列表视图
 */
public abstract class PartnerOrderListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		new SSearchText2(headerRightArea).setID(PartnerOrderListProcessor.ID_TEXT_SEARCHTEXT);

	}

	public final STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(PartnerOrderListProcessor.Columns.CreateDate.name(), 150, JWT.CENTER, "日期");
		columns[1] = new STableColumn(PartnerOrderListProcessor.Columns.SheetNumber.name(), 150, JWT.CENTER, "单据编号");
		columns[1].setGrab(true);
		columns[2] = new STableColumn(PartnerOrderListProcessor.Columns.SheetType.name(), 150, JWT.CENTER, "交易类型");
		columns[3] = new STableColumn(PartnerOrderListProcessor.Columns.Amount.name(), 150, JWT.RIGHT, "交易金额");
		columns[4] = new STableColumn(PartnerOrderListProcessor.Columns.CreatePerson.name(), 150, JWT.CENTER, "制单人");
		columns[5] = new STableColumn(PartnerOrderListProcessor.Columns.Processstatus.name(), 150, JWT.CENTER, "处理情况");
		for (int i = 0; i < 6; i++) {
			columns[i].setSortable(true);
		}
		return columns;
	}

	public String getText(Object element, int columnIndex) {
		OrderItem item = (OrderItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCreateDate());
		case 1:
			return StableUtil.toLink(PartnerOrderListProcessor.Columns.SheetNumber.name(), null, item.getOrderNumber());
		case 2:
			return item.getOrderType().getName();
		case 3:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 4:
			return item.getCreator();
		case 5:
			return item.getOrderStatus().getName();
		default:
			return "";
		}
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

}
