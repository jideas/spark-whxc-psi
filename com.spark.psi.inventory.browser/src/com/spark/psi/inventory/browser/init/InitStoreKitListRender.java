package com.spark.psi.inventory.browser.init;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.store.entity.StoreInitKitItem;

/**
 * 初始化仓库其他物品库存界面视图
 */
public class InitStoreKitListRender extends PSIListPageRender {
	
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
	protected void afterFooterRender() {		
		super.afterFooterRender();		
		new Label(headerLeftArea).setText("仓库名称：");
		new Label(headerLeftArea).setID(InitStoreKitListProcessor.ID_Label_StoreName);
		new Label(headerLeftArea).setText("      ");
		new Label(headerLeftArea).setText("物品数量：");
		new Label(headerLeftArea).setID(InitStoreKitListProcessor.ID_Label_KitItemCount);		
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" 新增物品 ");	
		button.setID(InitStoreKitListProcessor.ID_Button_AddKitItem);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  保 存  ");
		button.setID(InitStoreKitListProcessor.ID_Button_Save);
	}

	@Override
	public STableColumn[] getColumns() {		
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(InitStoreKitListProcessor.Columns.kitName.name(), 0, JWT.LEFT, "物品名称");		
		columns[1] = new STableColumn(InitStoreKitListProcessor.Columns.kitDescription.name(), 0, JWT.LEFT, "物品描述");		
		columns[2] = new STableColumn(InitStoreKitListProcessor.Columns.unit.name(), 60, JWT.CENTER, "单位");
		columns[3] =new SNumberEditColumn(InitStoreKitListProcessor.Columns.count.name(), 0, JWT.RIGHT, "初始数量");		
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {		
		if (element instanceof StoreInitKitItem) {	
			StoreInitKitItem item = (StoreInitKitItem) element;
			switch (columnIndex) {
				case 0:
					return item.getKitName();
				case 1:
					return item.getKitDescription();
				case 2:
					return item.getUnit();
				case 3:
					return String.valueOf(item.getCount());//DoubleUtil.getRoundStr(item.getCount());
				default:
					return "";
			} 
		}else {
			return null;
		}
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}