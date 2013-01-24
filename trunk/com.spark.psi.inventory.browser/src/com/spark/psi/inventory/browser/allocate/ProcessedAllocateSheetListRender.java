package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;

/**
 * ��������ɵĿ��������б���ͼ
 */
public class ProcessedAllocateSheetListRender extends PSIListPageRender{

	@Override
	protected void afterFooterRender(){

		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(ProcessedAllocateSheetListProcessor.ID_TEXT_SEARCH);
		//
		//		new Button(headerRightArea).setID(ProcessedAllocateSheetListProcessor.ID_BUTTON_SEARCH);
		//
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
		        .setID(ProcessedAllocateSheetListProcessor.ID_COMBOLIST_DATEITEM);
		//
		new Label(headerLeftArea).setText("  ������������");
		//
		new Label(headerLeftArea).setID(ProcessedAllocateSheetListProcessor.ID_LABEL_INVENTORYALLOCATESHEET_COUNT);
	}

	/**
	 * �����
	 */
	public STableColumn[] getColumns(){
		STableColumn[] columns = new STableColumn[6];
		//������
		columns[0] =
		        new STableColumn(ProcessedAllocateSheetListProcessor.Columns.SheetNumber.name(), 0, JWT.LEFT, "��������");
		columns[1] =
		        new STableColumn(ProcessedAllocateSheetListProcessor.Columns.SourceStoreName.name(), 0, JWT.LEFT,
		                "�����ֿ�");
		columns[2] =
		        new STableColumn(ProcessedAllocateSheetListProcessor.Columns.AllocateOutDate.name(), 120, JWT.CENTER,
		                "��������");
		columns[3] =
		        new STableColumn(ProcessedAllocateSheetListProcessor.Columns.DestinationStoreName.name(), 0, JWT.LEFT,
		                "����ֿ�");
		columns[4] =
		        new STableColumn(ProcessedAllocateSheetListProcessor.Columns.AllocateInDate.name(), 120, JWT.CENTER,
		                "��������");
		columns[5] =
		        new STableColumn(ProcessedAllocateSheetListProcessor.Columns.CreatorName.name(), 0, JWT.CENTER, "�Ƶ���");
		//����Ӧ
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[3].setGrab(true);
		columns[5].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle(){
		STableStyle sTableStyle = new STableStyle();
		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	/**
	 * ��Ԫ����ʾ�ı�ֵ
	 */
	public String getText(Object element, int columnIndex){
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem)element;
		switch(columnIndex){
			case 0:
				return StableUtil.toLink(SubmitingAllocateSheetListProcessor.ID_ACTION_VIEW, "", item.getSheetNumber());
			case 1:
				return item.getSourceStoreName();
			case 2:
				return DateUtil.dateFromat(item.getAllocateOutDate());
			case 3:
				return item.getDestinationStoreName();
			case 4:
				return DateUtil.dateFromat(item.getAllocateInDate());
			case 5:
				return item.getCreatorName();
			default:
				return "";
		}
	}
}