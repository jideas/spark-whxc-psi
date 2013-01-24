package com.spark.psi.base.browser.store;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.store.entity.StoreItem;

/**
 * 仓库列表界面视图
 * 
 */
public class StoreListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		if(!hideFooterArea()){
			//
			Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
			button.setText(" 新增仓库 ");
			button.setID(StoreListProcessor.ID_BUTTON_NEWSTORE);
		}

		//
		new Label(headerLeftArea).setText("仓库数量：");

		//
		Label label = new Label(headerLeftArea);
		label.setID(StoreListProcessor.ID_LABEL_STORE_COUNT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);
	}
	
	@Override
	public STableStyle getTableStyle(){
		STableStyle tableStyle = super.getTableStyle();
		tableStyle.setPageAble(false);
	    return tableStyle;
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(StoreListProcessor.Columns.Name.name(), 200, JWT.LEFT, "仓库名称");
		columns[1] = new STableColumn(StoreListProcessor.Columns.Address.name(), 300, JWT.LEFT, "地址");
		columns[2] = new STableColumn(StoreListProcessor.Columns.Keepers.name(), 200, JWT.LEFT, "库管员");
		columns[3] = new STableColumn(StoreListProcessor.Columns.status.name(), 150, JWT.CENTER, "仓库状态");
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		columns[2].setSortable(true);
		columns[3].setSortable(true);
		return columns;
	}

	public String getText(Object element, int columnIndex) {
		StoreItem item = (StoreItem) element;
		switch (columnIndex) {
		case 0:
			return StableUtil.toLink("edit", "", item.getName());
		case 1:
			return item.getAddress();
		case 2:
			return item.getKepperInfo();
		case 3:
			return item.getStatus().getName();
		default:
			return "";
		}
	}
}
