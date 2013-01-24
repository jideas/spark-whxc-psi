package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;

/**
 * 待提交的库存调拨单列表视图
 */
public class SubmitingAllocateSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {		
		super.afterFooterRender();
		//上左部分
		new Label(headerLeftArea).setText("调拨单数量：");
		new Label(headerLeftArea).setID(SubmitingAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
		//上右部分
		new SSearchText2(headerRightArea).setID(SubmitingAllocateSheetListProcessor.ID_TEXT_SEARCH);
//		new Button(headerRightArea).setID(SubmitingAllocateSheetListProcessor.ID_BUTTON_SEARCH);
		//上左部分
		new Button(footerLeftArea,JWT.APPEARANCE2).setID(SubmitingAllocateSheetListProcessor.ID_BUTTON_NEW_INVENTORYALLOCATESHEET);
		//下拉控件
		//LWComboList
	}
	
	//目前只能设置表标题列的风格，内容列的风格暂时未完善
	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[5];		
		//需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.CreateDate.name(), 120, JWT.CENTER, "申请日期");		
		columns[0].setSortable(true);
		columns[1] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.SheetNumber.name(), 150, JWT.CENTER, "调拨单号");
		columns[1].setSortable(true);
		columns[1].setGrab(true);
		columns[2] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.status.name(), 150, JWT.CENTER, "单据状态");
		columns[2].setSortable(true);
		columns[2].setGrab(true);
		columns[3] =new STableColumn(SubmitingAllocateSheetListProcessor.Columns.SourceStoreName.name(), 150, JWT.CENTER, "调出仓库");
		columns[3].setSortable(true);
		columns[3].setGrab(true);		
		columns[4] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 150, JWT.CENTER, "调入仓库");
		columns[4].setSortable(true);
		columns[4].setGrab(true);		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
//		sTableStyle.setSortAll(true);
		sTableStyle.setSortAll(false);
		return sTableStyle;
	}

	/**
	 * 获取指定行对象在指定列的数据
	 */
	public String getText(Object element, int columnIndex) {
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCreateDate());
		case 1:
			return StableUtil.toLink(SubmitingAllocateSheetListProcessor.ID_ACTION_EDIT, "", item.getSheetNumber());
		case 2:
			return item.getStatus().getName();
		case 3:
			return item.getSourceStoreName();
		case 4:
			return item.getDestinationStoreName();
		default:
			return "";
		}
		
	}
}