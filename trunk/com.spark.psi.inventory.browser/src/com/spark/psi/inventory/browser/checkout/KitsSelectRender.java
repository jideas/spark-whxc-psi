package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.inventory.browser.internal.InventoryImages;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;

public class KitsSelectRender extends PSIListPageRender {
	
	@Override
	public STableColumn[] getColumns() {		
		STableColumn[] columns = new STableColumn[3];		
		columns[0] = new STableColumn(KitsSelectProcessor.Columns.kitName.name(), 250, JWT.LEFT, "物品名称");		
		columns[1] = new STableColumn(KitsSelectProcessor.Columns.kitDesc.name(), 200, JWT.LEFT, "物品描述");		
		columns[2] = new STableColumn(KitsSelectProcessor.Columns.unit.name(), 100, JWT.CENTER, "单位");
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {		
		if (element instanceof String) {
			return "";
		}		
		KitInventoryDetail item = (KitInventoryDetail) element;		
		switch (columnIndex) {
		case 0:
			return item.getKitName();
		case 1:
			return item.getKitDesc();
		case 2:
			return item.getUnit();
		default:
			return "";
		}
	}
		
	@Override
	protected void beforeTableRender() {		
		super.beforeTableRender();		
		Label label = new Label(headerLeftArea);
		label.setText("可选物品");
		label.setFont(new Font(9, "宋体", JWT.FONT_STYLE_BOLD));	
		
		new Label(headerRightArea).setImage(InventoryImages.getImage("/images/checkout/saas_pause_auth.png"));	
		new Label(headerRightArea).setText("已选材料");
		label = new Label(headerRightArea);
		label.setText("0");
		label.setForeground(Color.COLOR_RED);
		label.setID(KitsSelectProcessor.ID_Label_SelectedCount);
		new Label(headerRightArea).setText("件");
		new Label(headerRightArea).setText("   ");
		new SSearchText2(headerRightArea).setID(KitsSelectProcessor.ID_TEXT_SEARCH);
	}
	
	@Override
	protected void afterFooterRender() {		
		super.afterFooterRender();		
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 完成选择 ");
		button.setID(KitsSelectProcessor.ID_Button_Finish);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 取消选择 ");
		button.setID(KitsSelectProcessor.ID_Button_Cancel);
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}