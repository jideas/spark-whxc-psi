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
 * ��ʽ�ͻ��б���ͼ
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
		new Label(headerLeftArea).setText("�ͻ�������");
		new Label(headerLeftArea)
				.setID(FormalCustomerListProcessor.ID_LABEL_COUNT);
		//
		new SSearchText2(headerRightArea).setID(FormalCustomerListProcessor.ID_TEXT_SEARCHTEXT);
		// new
		// Button(headerRightArea).setID(FormalCustomerListProcessor.ID_BUTTON_SEARCH);
		// Button detailButton = new Button(headerRightArea);
		// detailButton.setID(FormalCustomerListProcessor.ID_BUTTON_SEARCH);
		// detailButton.setText("�߼�����");
		//
		Button button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" ����ͻ� ");
		button.setID(FormalCustomerListProcessor.ID_BUTTON_ALLOCATE);
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" ��Ϊ���� ");
		button.setID(FormalCustomerListProcessor.ID_BUTTON_SHARE);
		new Label(footerLeftArea).setText(" ");
		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" �������� ");
		button.setID(FormalCustomerListProcessor.ID_BUTTON_CHANGECREDIT);
	}

	public STableColumn[] getColumns() {

		List<STableColumn> columns = new ArrayList<STableColumn>();
		LoginInfo login = getContext().find(LoginInfo.class);
		STableColumn column = new STableColumn(
				FormalCustomerListProcessor.Columns.Name.name(), 100, JWT.LEFT,
				"�ͻ�����");
		column.setGrab(true);
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.TotalTradeAmount.name(),
				100, JWT.RIGHT, "�����ܶ�");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.TotalTradeCount.name(),
				100, JWT.RIGHT, "���۴���");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.RecentTradeDate.name(),
				100, JWT.CENTER, "�������");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.BalanceAmount.name(), 100,
				JWT.RIGHT, "Ӧ�����");
		column.setSortable(true);
		columns.add(column);
		if(login.hasAuth(Auth.SubFunction_PartnerMange_ShowCredit)){
			column = new STableColumn(
					FormalCustomerListProcessor.Columns.CreditAmount.name(), 100,
					JWT.RIGHT, "���ö��");
			column.setSortable(true);
			columns.add(column);
			column = new STableColumn(
					FormalCustomerListProcessor.Columns.CreditDay.name(), 55,
					JWT.RIGHT, "����");
			column.setSortable(true);
			columns.add(column);
		}
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.ContactPerson.name(), 100,
				JWT.CENTER, "��ϵ��");
		column.setSortable(true);
		columns.add(column);
		column = new STableColumn(
				FormalCustomerListProcessor.Columns.SalesName.name(), 100,
				JWT.CENTER, "����");
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
		buffer.append("�ֻ���");
		buffer.append(StringUtils.isEmpty(item.getContactMobileNo()) ? "��" : item.getContactMobileNo());
		buffer.append("\n");
		buffer.append("�̻���");
		buffer.append(StringUtils.isEmpty(item.getContactLandLineNumber()) ? "��" : item.getContactLandLineNumber());
		buffer.append("\n");
		buffer.append("�������䣺");
		buffer.append(StringUtils.isEmpty(item.getContactEmail()) ? "��" : item.getContactEmail());
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
					//�ѹ����� �����ѹ�����
					ImageDescriptor img = BaseImages.getImage("images/customer/saas_mark_red.png");
					imgStr = StableUtil.toImg(img.getDNAURI(), "�ѹ�����", img.getWidth()-1); 
				}else if(item.isCreditTowards()){
					//�ٽ�����
					ImageDescriptor img = BaseImages.getImage("images/customer/saas_mark_yollow.png");
					imgStr = StableUtil.toImg(img.getDNAURI(), "�ٽ�����", img.getWidth()-1); 				
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
