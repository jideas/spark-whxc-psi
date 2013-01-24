package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;

/**
 * 待审批的库存调拨单列表视图
 */
public class ApprovalingAllocateSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(ApprovalingAllocateSheetListProcessor.ID_TEXT_SEARCH);
		//
//		new Button(headerRightArea).setID(ApprovalingAllocateSheetListProcessor.ID_BUTTON_SEARCH);
		//
		new Label(headerLeftArea).setText("调拨单数量：");
		//
		new Label(headerLeftArea).setID(ApprovalingAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
	}

	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[5];
		
		//需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.CreateDate.name(), 100, JWT.CENTER, "申请日期");
		columns[0].setSortable(true);
		
		columns[1] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.SheetNumber.name(), 0, JWT.CENTER, "调拨单号");
		columns[1].setGrab(true);
		columns[1].setSortable(true);

		columns[2] =new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.SourceStoreName.name(), 0, JWT.CENTER, "调出仓库");
		columns[2].setGrab(true);	
		columns[2].setSortable(true);
		
		columns[3] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 0, JWT.CENTER, "调入仓库");
		columns[3].setGrab(true);
		columns[3].setSortable(true);
		
		columns[4] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.CreatorName.name(), 0, JWT.CENTER, "制单人");
		columns[4].setGrab(true);
		columns[4].setSortable(true);
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
		sTableStyle.setPageAble(false);
//		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	public String getText(Object element, int columnIndex) {
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCreateDate());
		case 1:
			return StableUtil.toLink(SubmitingAllocateSheetListProcessor.ID_ACTION_VIEW, "", item.getSheetNumber());
		case 2:
			return item.getSourceStoreName();
		case 3:
			return item.getDestinationStoreName();
		case 4:
			return item.getCreatorName();
		default:
			return "";
		}
	}
}