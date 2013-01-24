package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

/**
 * 其他物品入库 查看明细
 */
public class KitCheckInSheetDetailRender extends ExtendSimpleSheetPageRender {

	@Override
	public STableColumn[] getColumns() {		
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(KitCheckInSheetDetailProcessor.Columns.kitName.name(), 0, JWT.LEFT, "物品名称");		
		columns[1] = new STableColumn(KitCheckInSheetDetailProcessor.Columns.kitDescription.name(), 0, JWT.LEFT, "物品描述");		
		columns[2] = new STableColumn(KitCheckInSheetDetailProcessor.Columns.unit.name(), 110, JWT.CENTER, "单位");
		columns[3] =new STableColumn(KitCheckInSheetDetailProcessor.Columns.count.name(), 110, JWT.RIGHT, "入库数量");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {		
		if (element instanceof String) {
			return "";
		}
		CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
		switch (columnIndex) {
		case 0:
			return item.getGoodsName();
		case 1:
			return item.getGoodsSpec();
		case 2:
			return item.getUnit();
		case 3:
			return String.valueOf(item.getRealCount());//DoubleUtil.getRoundStr(Double.valueOf(item.getCount()));
		default:
			return "";
		}
	}
	
	@Override
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
			case 3:
				return 0;
			default:
				return -1;
		} 
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,int column) {
		if(row == 0) {
			if(column == 0) {				
				Label label = new Label(baseInfoArea);
				label.setID(KitCheckInSheetDetailProcessor.ID_Label_Store);
				label.setText("");				
				label = new Label(baseInfoArea);
				label.setID(KitCheckInSheetDetailProcessor.ID_Label_Source);
				label.setText("");
			} else if (column == 1) {	
				addProcessing(baseInfoArea);
			}
		}
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		
	}
	
	
}