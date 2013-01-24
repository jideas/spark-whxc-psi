package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * 指定商品的库存情况列表视图
 */
public class GoodsInventoryListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("编号/条码：");
		new Label(headerLeftArea).setID(GoodsInventoryListProcessor.ID_Label_Number);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("商品名称：");
		new Label(headerLeftArea).setID(GoodsInventoryListProcessor.ID_Label_Name);
		new Label(headerRightArea).setText("阀值控制范围：");
		new ComboList(headerRightArea).setID(GoodsInventoryListProcessor.ID_List_Scope);
		new Label(headerRightArea).setText("  ");
		new Label(headerRightArea).setText("控制类型：");
		new ComboList(headerRightArea).setID(GoodsInventoryListProcessor.ID_List_Type);
		
		Button saveButton = new Button(footerRightArea);
		saveButton.setID(GoodsInventoryListProcessor.ID_Button_Save);
		saveButton.setText("保存阀值");
	}
	
	@Override
	protected void beforeTableRender() {
		super.beforeTableRender();
		GridData h_gd = new GridData(GridData.FILL_HORIZONTAL);
		h_gd.heightHint = 24;
		
		Composite beforeTabelCmp = new Composite(contentArea);
		beforeTabelCmp.setLayout(new GridLayout(8));
		beforeTabelCmp.setLayoutData(h_gd);
		
		new Label(beforeTabelCmp).setText("商品属性：");
		new Label(beforeTabelCmp).setID(GoodsInventoryListProcessor.ID_Label_Property);
		new Label(beforeTabelCmp).setText("    ");
		new Label(beforeTabelCmp).setText("单位：");
		new Label(beforeTabelCmp).setID(GoodsInventoryListProcessor.ID_Label_Unit);
		new Label(beforeTabelCmp).setText("    ");
		new Label(beforeTabelCmp).setText("状态：");
		new Label(beforeTabelCmp).setID(GoodsInventoryListProcessor.ID_Label_status);
	}
	
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn("salesmanName", 150, JWT.LEFT, "仓库名称");
		columns[1] = new STableColumn("departmentInfo", 200, JWT.LEFT, "库存金额上限");
		columns[2] = new STextEditColumn("customerCreditLimit", 100, JWT.LEFT, "采购库存数量");
		columns[3] = new STextEditColumn("availableTotalCreditLimit", 150, JWT.CENTER, "采购中数量");
		columns[4] = new STableColumn("customerCountUsed", 180, JWT.CENTER, "交货需求");

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		InventoryInfo item = (InventoryInfo) element;
		switch (columnIndex) {
		case 0:
			// TODO 取仓库名称
//			return item.getStoreId();
		case 1:
			return String.valueOf(item.getUpperLimitAmount());
		case 2:
			return String.valueOf(item.getCount());
		case 3:
			return String.valueOf(item.getPurchasingCount());
		case 4:
			return String.valueOf(item.getDeliveryingCount());
		default:
			return "";
		}
	}
}
