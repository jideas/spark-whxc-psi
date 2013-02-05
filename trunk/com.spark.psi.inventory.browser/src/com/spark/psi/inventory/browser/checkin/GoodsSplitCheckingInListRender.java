package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.CheckingInItem;

/**
 * 销售退货入库单列表视图
 */
public class GoodsSplitCheckingInListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();

		new SSearchText2(headerRightArea).setID(SalesReturnCheckingInListProcessor.ID_TEXT_SEARCH);

		new Label(headerLeftArea).setText("入库需求数量：");
		new Label(headerLeftArea).setID(SalesReturnCheckingInListProcessor.ID_LABEL_CheckingIn_COUNT);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[4];

		// 需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(SalesReturnCheckingInListProcessor.Columns.CreateDate.name(), 100, JWT.CENTER,
				"制单日期");
		columns[0].setSortable(true);

		columns[1] = new STableColumn(SalesReturnCheckingInListProcessor.Columns.RelatedNumber.name(), 100, JWT.CENTER,
				"相关单据");
		columns[1].setSortable(true);
		columns[1].setGrab(true);

		columns[2] = new STableColumn(SalesReturnCheckingInListProcessor.Columns.StoreName.name(), 100, JWT.CENTER,
				"仓库");
		columns[2].setSortable(true);
		columns[2].setGrab(true);

		columns[3] = new STableColumn(SalesReturnCheckingInListProcessor.Columns.status.name(), 100, JWT.CENTER, "处理状态");
		columns[3].setSortable(true);
		columns[3].setGrab(true);

		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		CheckingInItem item = (CheckingInItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCreateDate());

		case 1:
			return item.getRelaBillsNo();
		case 2:
			return item.getStoreName();
		case 3:
			return item.getStatus().getName();
		default:
			return "";
		}
	}
}