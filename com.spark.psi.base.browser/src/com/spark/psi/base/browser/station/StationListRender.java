package com.spark.psi.base.browser.station;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.station.entity.StationItem;

public class StationListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		new Label(headerLeftArea).setText("站点数量：");
		new Label(headerLeftArea).setID(StationListProcessor.ID_LABEL_COUNT);
		//
		Text text = new SSearchText2(headerRightArea);
		text.setHint("输入搜索内容");
		text.setID(StationListProcessor.ID_TEXT_SEARCHTEXT);
		text.setRegExp(SAAS.Reg.TEXT);

		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setID(StationListProcessor.ID_BUTTON_NEW);
		button.setText(" 新增站点 "); 
		 

	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(StationListProcessor.Columns.Number.name(), 120, JWT.LEFT, "站点编号");
		columns[1] = new STableColumn(StationListProcessor.Columns.ShortName.name(), 120, JWT.LEFT, "站点简称");
		columns[2] = new STableColumn(StationListProcessor.Columns.Address.name(), 200, JWT.LEFT, "站点地址");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(StationListProcessor.Columns.Manager.name(), 150, JWT.LEFT, "负责人");
		columns[4] = new STableColumn(StationListProcessor.Columns.MobileNo.name(), 100, JWT.LEFT, "联系方式");
		
		columns[0].setSortable(true);
		columns[1].setSortable(true); 
		return columns;
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

	public String getText(Object element, int columnIndex) {
		StationItem item = (StationItem) element;
		switch (columnIndex) {
		case 0:
			return item.getStationNo();
		case 1:
			return StableUtil.toLink("edit", "", item.getShortName());
		case 2:
			return item.getAddress();
		case 3:
			return item.getManager();
		case 4:
			return item.getMobileNo();
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		StationItem item = (StationItem) element;
		switch (columnIndex) {
		case 1:
			return item.getName();
		default:
			return "";
		}
	}

}
