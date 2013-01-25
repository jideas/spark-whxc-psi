package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;

public class OnlineOrderForSelectListRender extends PSIListPageRender {

	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("请选择站点：");
		new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(OnlineOrderForSelectListProcessor.ID_List_Station);
		
		new Label(headerLeftArea).setText("  ");
		new SDatePicker(headerLeftArea).setID(OnlineOrderForSelectListProcessor.ID_Date_Begin);
		new Label(headerLeftArea).setText(" 到：");
		new SDatePicker(headerLeftArea).setID(OnlineOrderForSelectListProcessor.ID_Date_End);
		Button button = new Button(headerLeftArea, JWT.APPEARANCE3);
		button.setID(OnlineOrderForSelectListProcessor.ID_Button_DateConfirm);
		button.setText("确定");
		
		new SSearchText2(headerRightArea).setID(OnlineOrderForSelectListProcessor.ID_Search);
		
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(OnlineOrderForSelectListProcessor.ID_Button_Confirm);
		
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(OnlineOrderForSelectListProcessor.ID_Button_Cancel);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(OnlineOrderForSelectListProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, OnlineOrderForSelectListProcessor.ColumnName.sheetNo.getTitle());
		columns[1] = new STableColumn(OnlineOrderForSelectListProcessor.ColumnName.memberName.name(), 150, 
				JWT.CENTER, OnlineOrderForSelectListProcessor.ColumnName.memberName.getTitle());
		columns[1].setGrab(true);
		columns[2] = new STableColumn(OnlineOrderForSelectListProcessor.ColumnName.amount.name(), 130, 
				JWT.RIGHT, OnlineOrderForSelectListProcessor.ColumnName.amount.getTitle());
		columns[3] = new STableColumn(OnlineOrderForSelectListProcessor.ColumnName.createDate.name(), 130, 
				JWT.CENTER, OnlineOrderForSelectListProcessor.ColumnName.createDate.getTitle());
		columns[4] = new STableColumn(OnlineOrderForSelectListProcessor.ColumnName.arrivedConfirmDate.name(), 130, 
				JWT.CENTER, OnlineOrderForSelectListProcessor.ColumnName.arrivedConfirmDate.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		OnlineOrderItem item = (OnlineOrderItem)element;
		switch(columnIndex) {
		case 0:
			return item.getBillsNo();
		case 1:
			return item.getRealName();
		case 2:
			return DoubleUtil.getRoundStr(item.getTotalAmount());
		case 3:
			return DateUtil.dateFromat(item.getCreateDate());
		case 4:
			return DateUtil.dateFromat(item.getArrivedConfirmDate());
		}
		return null;
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setSelectionMode(SSelectionMode.Single);
		return style;
	}
	
}
