package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseListPageRender;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.order.browser.produce.ReturnAndReceiveMaterialPageProcessor.Type;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoMaterialsItem;

public class ReturnAndReceiveMaterialPageRender extends BaseListPageRender {

	private Type type = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		type = (Type)getArgument2();
	}
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(ReturnAndReceiveMaterialPageProcessor.ColumnName.materialName.name(), 350, JWT.LEFT, "材料名称");
		columns[0].setGrab(true);
		columns[1] = new STableColumn(ReturnAndReceiveMaterialPageProcessor.ColumnName.count.name(), 180, JWT.RIGHT, "数量");
		columns[2] = new STableColumn(ReturnAndReceiveMaterialPageProcessor.ColumnName.receivedCount.name(), 180, JWT.RIGHT, "已申请领料数量");
		columns[3] = new STableColumn(ReturnAndReceiveMaterialPageProcessor.ColumnName.returnedCount.name(), 180, JWT.RIGHT, "已申请退料数量");
		if (type == Type.RECEIVE) {
			columns[4] = new SNumberEditColumn(ReturnAndReceiveMaterialPageProcessor.ColumnName.currentReceivCount.name(), 180, JWT.RIGHT, "本次领料数量");
		} else {
			columns[4] = new SNumberEditColumn(ReturnAndReceiveMaterialPageProcessor.ColumnName.currentReturnCount.name(), 180, JWT.RIGHT, "本次退料数量");
		}
		((SNumberEditColumn)columns[4]).setDecimal(2);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem)element;
		switch(columnIndex) {
		case 0:
			return item.getMaterialName();
		case 1:
			return DoubleUtil.getRoundStr(item.getCount());
		case 2:
			return DoubleUtil.getRoundStr(item.getReceivedCount());
		case 3:
			return DoubleUtil.getRoundStr(item.getReturnedCount());
		}
		return null;
	}

	
	
	@Override
	public int getDecimal(Object element, int columnIndex) {
		return 2;
	}

	
	
	@Override
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		
		new Label(headerLeftArea).setID(ReturnAndReceiveMaterialPageProcessor.ID_Label_OrderNo);
		
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 确认 ");
		button.setID(ReturnAndReceiveMaterialPageProcessor.ID_Button_Confirm);
	}

}
