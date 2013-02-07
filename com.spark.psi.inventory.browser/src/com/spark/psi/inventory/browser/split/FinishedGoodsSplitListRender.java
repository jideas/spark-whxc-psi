package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.checkout.entity.CheckoutSheetItem;

/**
 * 已处理完成的出库单列表视图
 */
public class FinishedGoodsSplitListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(FinishedGoodsSplitListProcessor.ID_TEXT_SEARCH);
//		new Button(headerRightArea).setID(NewGoodsSplitListProcessor.ID_BUTTON_SEARCH);
		//
		new LWComboList(headerLeftArea,JWT.APPEARANCE3).setID(FinishedGoodsSplitListProcessor.ID_COMBOLIST_DATEITEM);		
		new Label(headerLeftArea).setText("  单据数量：");
		new Label(headerLeftArea).setID(FinishedGoodsSplitListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
	}

	public STableColumn[] getColumns() {
		//复杂表头怎么构造
		
		STableColumn[] columns = new STableColumn[6];
		
		//需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(NewGoodsSplitListProcessor.Columns.LastCheckoutDate.name(), 120, JWT.CENTER, "出库日期");
		
		columns[1] = new STableColumn(NewGoodsSplitListProcessor.Columns.SheetNumber.name(), 120, JWT.CENTER, "出库单号");
		columns[1].setGrab(true);

		columns[2] = new STableColumn(NewGoodsSplitListProcessor.Columns.Type.name(), 120, JWT.CENTER, "出库类型");
		columns[2].setGrab(true);

		columns[3] =new STableColumn(NewGoodsSplitListProcessor.Columns.RelatedNumber.name(), 120, JWT.CENTER, "相关单据");
		columns[3].setGrab(true);		
		
		columns[4] = new STableColumn(NewGoodsSplitListProcessor.Columns.StoreName.name(), 120, JWT.CENTER, "仓库");
		columns[4].setGrab(true); 
		
		columns[5] = new STableColumn(NewGoodsSplitListProcessor.Columns.CheckoutEmployees.name(), 120, JWT.CENTER, "确认出库");
		columns[5].setGrab(true);
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		CheckoutSheetItem item = (CheckoutSheetItem) element;
		switch (columnIndex) {
		case 6:
			return item.getCheckoutPersonName();
		default:
			return "";
		}
	}

	public String getText(Object element, int columnIndex) {
		CheckoutSheetItem item = (CheckoutSheetItem) element;
		switch (columnIndex) {
		case 0:
			if(CheckIsNull.isEmpty(item.getCheckoutPersonName()))
			{
				return "-";
			}
			else
			{
				return DateUtil.dateFromat(item.getCheckoutDate());
			}
		case 1:
			return  StableUtil.toLink( NewGoodsSplitListProcessor.ID_ACTION_EDIT, "", item.getBillsNo());
		case 2:
			return item.getSheetType().getName();
		case 3:
			return item.getRelaBillsNo();
		case 4:
			return item.getStoreName();
		case 5: 
			return item.getCheckoutPersonName();
		default:
			return "";
		}
	}
}