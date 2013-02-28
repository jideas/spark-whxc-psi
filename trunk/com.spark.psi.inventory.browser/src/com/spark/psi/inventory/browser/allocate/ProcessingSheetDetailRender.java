package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;

/**
 * <p>进行中详细调拔单视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-24
 */

public class ProcessingSheetDetailRender extends AllocateSheetDetailBaseRender{
	/**
	 * 页面底部右边按钮区
	 */
	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea){
		super.renderSheetButtonArea(sheetButtonArea);
		
		//确定调出　或　确定调入
		Button inOrOutButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		inOrOutButton.setID(ProcessingSheetDetailProcessor.ID_Button_InOrOut);
		inOrOutButton.setText("确定调出");
		inOrOutButton.setLayoutData(buttonGridData);
	}
	
	/**
	 * 获得列
	 */
	@Override
	public STableColumn[] getColumns(){
		//创建列
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(ProcessingSheetDetailProcessor.Columns.code.name(), 150, JWT.LEFT, "编号");
		columns[1] = new STableColumn(ProcessingSheetDetailProcessor.Columns.number.name(), 150, JWT.LEFT, "条码");
		columns[2] = new STableColumn(ProcessingSheetDetailProcessor.Columns.name.name(), 100, JWT.LEFT, "材料名称");
		columns[3] = new STableColumn(ProcessingSheetDetailProcessor.Columns.spec.name(), 100, JWT.LEFT, "规格");
		columns[4] = new STableColumn(ProcessingSheetDetailProcessor.Columns.unit.name(), 120, JWT.CENTER, "单位");
		columns[5] =
		        new SNumberEditColumn(ProcessingSheetDetailProcessor.Columns.availableCount.name(), 150, JWT.RIGHT, "可用库存");
		columns[6] =
		        new SNumberEditColumn(ProcessingSheetDetailProcessor.Columns.allocateCount.name(), 150, JWT.RIGHT, "调拨数量");
		//自适应
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}

	/**
	 * 单元格文本值
	 */
	@Override
	public String getText(Object element, int columnIndex){
		if(element instanceof AllocateShowItem){
			AllocateShowItem item = (AllocateShowItem)element;
			switch(columnIndex){
				case 0:
					return item.getStockItemCode();
				case 1:
					return item.getStockItemNumber();
				case 2:
					return item.getStockItemName();
				case 3:
					return item.getStockSpec();
				case 4:
					return item.getStockItemUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getAvailableCount());
				case 6:
					return DoubleUtil.getRoundStr(item.getAllocateCount());
			}
		}
		return "";
	}
}
