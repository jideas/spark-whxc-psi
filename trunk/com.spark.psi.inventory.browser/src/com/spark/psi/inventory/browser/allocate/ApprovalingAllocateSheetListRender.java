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
 * �������Ŀ��������б���ͼ
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
		new Label(headerLeftArea).setText("������������");
		//
		new Label(headerLeftArea).setID(ApprovalingAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
	}

	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[5];
		
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.CreateDate.name(), 100, JWT.CENTER, "��������");
		columns[0].setSortable(true);
		
		columns[1] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.SheetNumber.name(), 0, JWT.CENTER, "��������");
		columns[1].setGrab(true);
		columns[1].setSortable(true);

		columns[2] =new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.SourceStoreName.name(), 0, JWT.CENTER, "�����ֿ�");
		columns[2].setGrab(true);	
		columns[2].setSortable(true);
		
		columns[3] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 0, JWT.CENTER, "����ֿ�");
		columns[3].setGrab(true);
		columns[3].setSortable(true);
		
		columns[4] = new STableColumn(ApprovalingAllocateSheetListProcessor.Columns.CreatorName.name(), 0, JWT.CENTER, "�Ƶ���");
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