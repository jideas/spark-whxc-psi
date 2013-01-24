package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.partner.entity.CustomerItem;

/**
 * 授权客户列表视图
 */
public class B2BCustomerListRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new Label(headerLeftArea).setText("已授权");		
		new Label(headerLeftArea).setID(B2BCustomerListProcessor.ID_LABEL_USECOUNT);		
		new Label(headerLeftArea).setText("位网上下单客户，还可以授权");		
		new Label(headerLeftArea).setID(B2BCustomerListProcessor.ID_LABEL_REMAINCOUNT);		
		new Label(headerLeftArea).setText("位");		
		//
		new Text(headerRightArea).setID(B2BCustomerListProcessor.ID_TEXT_SEARCHTEXT);		
		new Button(headerRightArea).setID(B2BCustomerListProcessor.ID_BUTTON_SEARCH);
		//
		new Button(footerLeftArea).setID(B2BCustomerListProcessor.ID_BUTTON_NEW);		
		new Button(footerLeftArea).setID(B2BCustomerListProcessor.ID_BUTTON_DEL);
	}
	
	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[5];		
		columns[0] = new STableColumn("name", 0, JWT.CENTER, "客户名称");	
		columns[1] = new STableColumn("contactName", 0, JWT.CENTER, "联系人");
		columns[2] = new STableColumn("salesmanName", 0, JWT.CENTER, "销售");
		columns[3] = new STableColumn("tradeTotalCount", 0, JWT.CENTER, "授权商品数");
		columns[4] = new STableColumn("", 0, JWT.CENTER, "授权状态");
		
		for(int i=0,len = columns.length;i<len;i++){
			columns[i].setGrab(true);
		}

		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		style.setSelectionMode(SSelectionMode.Multi);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		CustomerItem item = (CustomerItem) element;
		switch (columnIndex) {
		case 0:
			return item.getName();
		case 1:
			return item.getContactName();
		case 2:
			return item.getSalesmanName();
		case 3:
			return String.valueOf(item.getTradeTotalCount());
		case 4:
			return "";
		default:
			return "";
		}
	}
}
