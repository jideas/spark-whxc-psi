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
 * ���д�����Ŀ��������б���ͼ
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
		new Label(headerLeftArea).setText("������������");
		//
		new Label(headerLeftArea).setID(ProcessingAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
	}

	/**
	 * ������
	 */
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.CreateDate.name(), 100, JWT.CENTER, "��������");
		columns[1] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.SheetNumber.name(), 0, JWT.LEFT, "��������");
		columns[2] =new STableColumn(ProcessingAllocateSheetListProcessor.Columns.SourceStoreName.name(), 0, JWT.LEFT, "�����ֿ�", "������");
		columns[3] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.status.name(), 0, JWT.CENTER, "״̬", "������");
		columns[4] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 0, JWT.LEFT, "����ֿ�", "���뷽");
		columns[5] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.status.name(), 0, JWT.CENTER, "״̬", "���뷽");
		columns[6] = new STableColumn(ProcessingAllocateSheetListProcessor.Columns.CreatorName.name(), 0, JWT.LEFT, "�Ƶ���");
		//����Ӧ
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
	 * ��õ����ֿ�״̬
	 */
	private String getAllocateOutstatusName(InventoryAllocateSheetItem item){
		if(item.getStatus().equals(InventoryAllocateStatus.Submitted) && StringHelper.isNotEmpty(item.getOutExamName())){//������->�ѵ������
			return "������";
		}
		return item.getStatus().getAllocateOutstatusName();
	}
	
	/**
	 * ��õ���ֿ�״̬
	 */
	private String getAllocateInstatusName(InventoryAllocateSheetItem item){
		if(item.getStatus().equals(InventoryAllocateStatus.Submitted) && StringHelper.isNotEmpty(item.getInExamName())){//������->�ѵ������
			return "������";
		}
		return item.getStatus().getAllocateInstatusName();
	}
}