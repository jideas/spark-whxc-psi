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
 * ���ύ�Ŀ��������б���ͼ
 */
public class SubmitingAllocateSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {		
		super.afterFooterRender();
		//���󲿷�
		new Label(headerLeftArea).setText("������������");
		new Label(headerLeftArea).setID(SubmitingAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
		//���Ҳ���
		new SSearchText2(headerRightArea).setID(SubmitingAllocateSheetListProcessor.ID_TEXT_SEARCH);
//		new Button(headerRightArea).setID(SubmitingAllocateSheetListProcessor.ID_BUTTON_SEARCH);
		//���󲿷�
		new Button(footerLeftArea,JWT.APPEARANCE2).setID(SubmitingAllocateSheetListProcessor.ID_BUTTON_NEW_INVENTORYALLOCATESHEET);
		//�����ؼ�
		//LWComboList
	}
	
	//Ŀǰֻ�����ñ�����еķ�������еķ����ʱδ����
	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[5];		
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.CreateDate.name(), 120, JWT.CENTER, "��������");		
		columns[0].setSortable(true);
		columns[1] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.SheetNumber.name(), 150, JWT.CENTER, "��������");
		columns[1].setSortable(true);
		columns[1].setGrab(true);
		columns[2] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.status.name(), 150, JWT.CENTER, "����״̬");
		columns[2].setSortable(true);
		columns[2].setGrab(true);
		columns[3] =new STableColumn(SubmitingAllocateSheetListProcessor.Columns.SourceStoreName.name(), 150, JWT.CENTER, "�����ֿ�");
		columns[3].setSortable(true);
		columns[3].setGrab(true);		
		columns[4] = new STableColumn(SubmitingAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 150, JWT.CENTER, "����ֿ�");
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
	 * ��ȡָ���ж�����ָ���е�����
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