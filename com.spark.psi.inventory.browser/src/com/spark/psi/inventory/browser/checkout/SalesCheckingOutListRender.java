package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;

/**
 * 销售出库单列表视图
 */
public class SalesCheckingOutListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(SalesCheckingOutListProcessor.ID_BUTTON_NEW_LYCK);
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(SalesCheckingOutListProcessor.ID_BUTTON_NEW_GIFT);
		//
		new SSearchText2(headerRightArea).setID(SalesCheckingOutListProcessor.ID_TEXT_SEARCH);
		new Label(headerLeftArea).setText("出库需求数量：");
		new Label(headerLeftArea).setID(SalesCheckingOutListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
	}
	
	public STableColumn[] getColumns() {
		//复杂表头怎么构造
		
		STableColumn[] columns = new STableColumn[5];
		
		//需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(SalesCheckingOutListProcessor.Columns.PlanOutDate.name(), 100, JWT.CENTER, "预计出库日期");
		columns[0].setSortable(true);
		
		columns[1] = new STableColumn(SalesCheckingOutListProcessor.Columns.RelatedNumber.name(), 200, JWT.CENTER, "相关单据");//entity接口中单词拼写错误getSeetNumber
		columns[1].setGrab(true);
		columns[1].setSortable(true);

		columns[2] =new STableColumn(SalesCheckingOutListProcessor.Columns.StoreName.name(), 200, JWT.CENTER, "仓库");
		columns[2].setGrab(true);
		columns[2].setSortable(true);
		
		columns[3] = new STableColumn(SalesCheckingOutListProcessor.Columns.status.name(), 200, JWT.CENTER, "处理状态");
		columns[3].setGrab(true);
		columns[3].setSortable(true);
		
		columns[4] = new STableColumn(SalesCheckingOutListProcessor.Columns.CreateDate.name(), 200, JWT.CENTER, "创建时间");
		columns[4].setSortable(true);
		
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		CheckingOutItem item = (CheckingOutItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getPlanCheckoutDate());
		case 1:
			return item.getRelaBillsNo();
		case 2:
			return item.getStoreName();
		case 3:
			return item.getStatus().getName();
		case 4:
			return DateUtil.dateFromat(item.getCreateDate());
		default:
			return "";
		}
	}
}