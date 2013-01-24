package com.spark.psi.base.browser.customer;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.partner.entity.CustomerItem;

/**
 * 正式客户列表视图
 */
public class FormalCustomerListRender extends PSIListPageRender {
	
	private boolean hasAuthForShowCredit;
	
	public void init(Situation context) {
		super.init(context);
		hasAuthForShowCredit = getContext().find(LoginInfo.class).hasAuth(Auth.SubFunction_PartnerMange_ShowCredit);
	}

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
				.setID(FormalCustomerListProcessor.ID_COMBO_CUSTOMERSCOPE);
		new Label(headerLeftArea).setText("    ");
//		new ComboList(headerLeftArea, JWT.APPEARANCE3)
//				.setID(FormalCustomerListProcessor.ID_COMBO_TIME);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("客户数量：");
		new Label(headerLeftArea)
				.setID(FormalCustomerListProcessor.ID_LABEL_COUNT);
		//
		new SSearchText2(headerRightArea).setID(FormalCustomerListProcessor.ID_TEXT_SEARCHTEXT);
		// new
		// Button(headerRightArea).setID(FormalCustomerListProcessor.ID_BUTTON_SEARCH);
		// Button detailButton = new Button(headerRightArea);
		// detailButton.setID(FormalCustomerListProcessor.ID_BUTTON_SEARCH);
		// detailButton.setText("高级搜索");
		//
		Button button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 分配客户 ");
		button.setID(FormalCustomerListProcessor.ID_BUTTON_ALLOCATE);
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 设为共享 ");
		button.setID(FormalCustomerListProcessor.ID_BUTTON_SHARE);
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 调整信用 ");
		button.setID(FormalCustomerListProcessor.ID_BUTTON_CHANGECREDIT);
	}

	public STableColumn[] getColumns() {

		List<STableColumn> columns = new ArrayList<STableColumn>();
		LoginInfo login = getContext().find(LoginInfo.class);
		STableColumn column = new STableColumn(
				FormalCustomerListProcessor.Columns.Name.name(), 100, JWT.LEFT,
				"客户名称");
		column.setGrab(true);
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.TotalTradeAmount.name(),
				100, JWT.RIGHT, "销售总额");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.TotalTradeCount.name(),
				100, JWT.RIGHT, "销售次数");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.RecentTradeDate.name(),
				100, JWT.CENTER, "最近订单");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.BalanceAmount.name(), 100,
				JWT.RIGHT, "应收余额");
		column.setSortable(true);
		columns.add(column);
		if(login.hasAuth(Auth.SubFunction_PartnerMange_ShowCredit)){
			column = new STableColumn(
					FormalCustomerListProcessor.Columns.CreditAmount.name(), 100,
					JWT.RIGHT, "信用额度");
			column.setSortable(true);
			columns.add(column);
			column = new STableColumn(
					FormalCustomerListProcessor.Columns.CreditDay.name(), 55,
					JWT.RIGHT, "账期");
			column.setSortable(true);
			columns.add(column);
		}
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.ContactPerson.name(), 100,
				JWT.CENTER, "联系人");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.SalesName.name(), 100,
				JWT.CENTER, "销售");
		column.setSortable(true);
		columns.add(column);
		return columns.toArray(new STableColumn[0]);
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		style.setSelectionMode(SSelectionMode.Multi);
		return style;
	}
	
	@Override
	public String getToolTipText(Object element, int columnIndex){
		CustomerItem item = (CustomerItem) element;
		if(hasAuthForShowCredit){
			switch (columnIndex) {
			case 0:
				return item.getName();
			case 7:
				return getContactTooltip(item);
			}	
		} else {
			switch (columnIndex) {
			case 0:
				return item.getName();
			case 5:
				return getContactTooltip(item);
			}
		}
		
		return "";
	}
	
	private String getContactTooltip(CustomerItem item) {
		StringBuffer buffer  = new StringBuffer();
		buffer.append("手机：");
		buffer.append(StringUtils.isEmpty(item.getContactMobileNo()) ? "无" : item.getContactMobileNo());
		buffer.append("\n");
		buffer.append("固话：");
		buffer.append(StringUtils.isEmpty(item.getContactLandLineNumber()) ? "无" : item.getContactLandLineNumber());
		buffer.append("\n");
		buffer.append("电子邮箱：");
		buffer.append(StringUtils.isEmpty(item.getContactEmail()) ? "无" : item.getContactEmail());
		buffer.append("\n");
		return buffer.toString();
	}
	
	@Override
	public int getDecimal(Object element, int columnIndex){
		if (hasAuthForShowCredit) {
			switch (columnIndex) {
			case 1:
				return 2;
			case 2:
				return 0;
			case 4:
				return 2;
			case 5:
				return 2;
			case 6:
				return 0;
			}
		} else {
			switch (columnIndex) {
			case 1:
				return 2;
			case 2:
				return 0;
			case 4:
				return 2;
			}
		}
		return -1;
	}

	public String getText(Object element, int columnIndex) {
		CustomerItem item = (CustomerItem) element;
		if(hasAuthForShowCredit){
			switch (columnIndex) {
			case 0:
				return StableUtil.toLink("edit", "", item.getShortName());
			case 1:
				return String.valueOf(item.getTradeTotalAmount());
			case 2:
				return String.valueOf(item.getTradeTotalCount());
			case 3:
				return DateUtil.dateFromat(item.getRecentTradeDate());
			case 4:
				return String.valueOf(item.getBalanceAmount());
			case 5:
				return String.valueOf(item.getCreditAmount());
			case 6:
				String imgStr = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				if(item.isCreditExpired()){
					//已过账期 设置已过账期
					ImageDescriptor img = BaseImages.getImage("images/customer/saas_mark_red.png");
					imgStr = StableUtil.toImg(img.getDNAURI(), "已过账期", img.getWidth()-1); 
				}else if(item.isCreditTowards()){
					//临近账期
					ImageDescriptor img = BaseImages.getImage("images/customer/saas_mark_yollow.png");
					imgStr = StableUtil.toImg(img.getDNAURI(), "临近账期", img.getWidth()-1); 				
				}
				return String.valueOf(item.getAccountPeriod())+"  "+imgStr;
			case 7:
				return item.getContactName();
			case 8:
				return item.getSalesmanName();
			default:
				return "";
			}
	}else {
		switch (columnIndex) {
			case 0:
				return StableUtil.toLink("edit", "", item.getShortName());
			case 1:
				return String.valueOf(item.getTradeTotalAmount());
			case 2:
				return String.valueOf(item.getTradeTotalCount());
			case 3:
				return DateUtil.dateFromat(item.getRecentTradeDate());
			case 4:
				return String.valueOf(item.getBalanceAmount());
			case 5:
				return item.getContactName();
			case 6:
				return item.getSalesmanName();
			default:
				return "";
			}
		}
		
	}
}
