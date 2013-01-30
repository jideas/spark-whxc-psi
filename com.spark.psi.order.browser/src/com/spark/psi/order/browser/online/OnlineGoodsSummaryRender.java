package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;

public class OnlineGoodsSummaryRender extends PSIListPageRender {

	
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("����վ�㣺");
		LWComboList list = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		list.setID(OnlineGoodsSummaryProcessor.ID_List_Station);
		
		new Label(headerLeftArea).setText("    ");
		
		new Label(headerLeftArea).setText("������ʼ���ڣ�");
		SDatePicker date = new SDatePicker(headerLeftArea);
		date.setID(OnlineGoodsSummaryProcessor.ID_Date_Begin);
		new Label(headerLeftArea).setText("�������ڣ�");
		date = new SDatePicker(headerLeftArea);
		date.setID(OnlineGoodsSummaryProcessor.ID_Date_End);
		
		new Label(headerLeftArea).setText("    ");
		
		list = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		list.setID(OnlineGoodsSummaryProcessor.ID_List_DelvierTime);
//		Button button = new Button(headerLeftArea, JWT.APPEARANCE3);
//		button.setText(" ȷ�� ");
//		button.setID(OnlineGoodsSummaryProcessor.ID_Button_Confirm);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(OnlineGoodsSummaryProcessor.ColumnName.goodsName.name(), 200, JWT.LEFT, "��Ʒ����");
		columns[1] = new STableColumn(OnlineGoodsSummaryProcessor.ColumnName.spec.name(), 150, JWT.LEFT, "���");
		columns[2] = new STableColumn(OnlineGoodsSummaryProcessor.ColumnName.price.name(), 100, JWT.RIGHT, "����");
		columns[3] = new STableColumn(OnlineGoodsSummaryProcessor.ColumnName.count.name(), 100, JWT.RIGHT, "����");
		for (int index = 0; index < columns.length; index++) {
			columns[index].setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		SummaryGoods item = (SummaryGoods)element;
		switch(columnIndex) {
		case 0:
			return item.getGoodsName();
		case 1:
			return item.getSpec();
		case 2:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 3:
			return DoubleUtil.getRoundStr(item.getCount());
		}
		return null;
	}

}
