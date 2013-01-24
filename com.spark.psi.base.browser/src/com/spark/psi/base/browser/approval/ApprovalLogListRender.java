package com.spark.psi.base.browser.approval;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.approval.ApprovalRecordItem;

/**
 * 审批记录列表视图
 */
public class ApprovalLogListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new ComboList(headerLeftArea).setID(ApprovalLogListProcessor.ID_COMBO_TIME);			
		new Label(headerLeftArea).setID(ApprovalLogListProcessor.ID_LABEL_COUNT);		
		//
		new Text(headerRightArea).setID(ApprovalLogListProcessor.ID_TEXT_SEARCHTEXT);		
		new Button(headerRightArea).setID(ApprovalLogListProcessor.ID_BUTTON_SEARCH);		
	}
	
	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[6];
		
		columns[0] = new STableColumn("examDate", 200, JWT.CENTER, "审批日期");
		columns[1] = new STableColumn("", 150, JWT.CENTER, "单据编号");
		columns[2] = new STableColumn("busTypeName", 150, JWT.CENTER, "类型");
		columns[3] = new STableColumn("", 150, JWT.CENTER, "金额");
		columns[4] = new STableColumn("", 150, JWT.CENTER, "制单人");
		columns[5] = new STableColumn("", 150, JWT.CENTER, "制单日期");
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		ApprovalRecordItem item = (ApprovalRecordItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getApprovalDate());
		case 1:
			return item.getBillsNumber();
		case 2:
			return item.getBusTypeName();
		case 3:
			return String.valueOf(item.getAmount());
		case 4:
			return item.getCreatePerson();
		case 5:
			return DateUtil.dateFromat(item.getCreateDate());
		default:
			return "";
		}
	}
}
