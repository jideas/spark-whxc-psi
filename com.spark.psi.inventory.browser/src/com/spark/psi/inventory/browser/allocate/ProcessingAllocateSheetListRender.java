package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;

/**
 * 所有待处理的库存调拨单列表视图
 */
public class ProcessingAllocateSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(ProcessingAllocateSheetListProcessor.ID_TEXT_SEARCH);
		//
//		new Button(headerRightArea).setID(ProcessingAllocateSheetListProcessor.ID_BUTTON_SEARCH);
		//
		new Label(headerLeftArea).setText("调拨单数量：");
		//
		new Label(headerLeftArea).setID(ProcessingAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
	}

	/**
	 * 构造列
	 */
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		//需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.CreateDate.name(), 100, JWT.CENTER, "申请日期");
		columns[1] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.SheetNumber.name(), 0, JWT.LEFT, "调拨单号");
		columns[2] =new STableColumn(ProcessingAllocateSheetListProcessor.Columns.SourceStoreName.name(), 0, JWT.LEFT, "调出仓库", "调出方");
		columns[3] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.status.name(), 0, JWT.CENTER, "状态", "调出方");
		columns[4] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 0, JWT.LEFT, "调入仓库", "调入方");
		columns[5] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.status.name(), 0, JWT.CENTER, "状态", "调入方");
		columns[6] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.CreatorName.name(), 0, JWT.LEFT, "制单人");
		//自适应
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);				
		columns[4].setGrab(true);
		columns[5].setGrab(true);
		columns[6].setGrab(true);
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
			return getAllocateOutstatusName(item);
		case 4:
			return item.getDestinationStoreName();
		case 5:
			return getAllocateInstatusName(item);
		case 6:
			return item.getCreatorName();
		default:
			return "";
		}
	}
	
	/**
	 * 获得调出仓库状态
	 */
	private String getAllocateOutstatusName(InventoryAllocateSheetItem item){
		if(item.getStatus().equals(InventoryAllocateStatus.Submitted) && StringHelper.isNotEmpty(item.getOutExamName())){//待审批->已调出审核
			return "已审批";
		}
		return item.getStatus().getAllocateOutstatusName();
	}
	
	/**
	 * 获得调入仓库状态
	 */
	private String getAllocateInstatusName(InventoryAllocateSheetItem item){
		if(item.getStatus().equals(InventoryAllocateStatus.Submitted) && StringHelper.isNotEmpty(item.getInExamName())){//待审批->已调入审核
			return "已审批";
		}
		return item.getStatus().getAllocateInstatusName();
	}
}