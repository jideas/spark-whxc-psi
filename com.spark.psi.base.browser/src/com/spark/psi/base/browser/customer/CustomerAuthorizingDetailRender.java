package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * 客户授权明细界面处理器
 */
public class CustomerAuthorizingDetailRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("客户名称：");
		new Label(headerLeftArea).setID(CustomerAuthorizingDetailProcessor.ID_Label_Name);
		new Label(headerLeftArea).setText("  ");
		Label findLabel = new Label(headerLeftArea);
		findLabel.setID(CustomerAuthorizingDetailProcessor.ID_Label_Find);
		findLabel.setForeground(Color.COLOR_BLUE);
		findLabel.setText("重新选择客户");
		
		Button newGoodsButton = new Button(footerLeftArea);
		newGoodsButton.setID(CustomerAuthorizingDetailProcessor.ID_Button_AddGoods);
		newGoodsButton.setText("新增商品");
		
		Button doneButton = new Button(footerRightArea);
		doneButton.setID(CustomerAuthorizingDetailProcessor.ID_Button_Save);
		doneButton.setText("完成授权");
	}
	
	@Override
	protected void beforeTableRender() {
		super.beforeTableRender();
		
		Composite row1Cmp = new Composite(contentArea);
		Composite row2Cmp = new Composite(contentArea);
		row1Cmp.setLayout(new GridLayout(13));
		row2Cmp.setLayout(new GridLayout(4));
		GridData h_gd = new GridData(GridData.FILL_HORIZONTAL);
		h_gd.heightHint = 24;
		row1Cmp.setLayoutData(h_gd);
		row2Cmp.setLayoutData(h_gd);
		
		new Label(row1Cmp).setText("被授权人：");
		//TODO 先用ComboList代替LWComboList
		new ComboList(row1Cmp);
		
		GridData imgData = new GridData();
		imgData.widthHint = 16;
		
		Label editContactLabel = new Label(row1Cmp);
		editContactLabel.setLayoutData(imgData);
		editContactLabel.setID(CustomerAuthorizingDetailProcessor.ID_Label_EditContactImg);
		editContactLabel.setBackimage(BaseImages
				.getImage("images/customer/ico_modify.png"));
		Label newContactLabel = new Label(row1Cmp);
		newContactLabel.setLayoutData(imgData);
		newContactLabel.setID(CustomerAuthorizingDetailProcessor.ID_Label_NewContactImg);
		newContactLabel.setBackimage(BaseImages
				.getImage("images/customer/saas_add_enum.png"));
		new Label(row1Cmp).setText("  ");
		new Label(row1Cmp).setText("部门(职务)：");
		new Label(row1Cmp).setID(CustomerAuthorizingDetailProcessor.ID_Label_SalerInfo);
		new Label(row1Cmp).setText("  ");
		Label mobileLabel = new Label(row1Cmp);
		mobileLabel.setBackimage(BaseImages
				.getImage("images/customer/saas_mark_tel.png"));
		mobileLabel.setLayoutData(imgData);
		new Label(row1Cmp).setID(CustomerAuthorizingDetailProcessor.ID_Label_Mobile);
		new Label(row1Cmp).setText("  ");
		Label phoneLabel = new Label(row1Cmp);
		phoneLabel.setLayoutData(imgData);
		phoneLabel.setBackimage(BaseImages
				.getImage("images/customer/saas_mark_phone.png"));
		new Label(row1Cmp).setID(CustomerAuthorizingDetailProcessor.ID_Label_LandPhoneNumber);
		
		
		new Label(row2Cmp).setText("授权商品：");
		Label goodsModelLabel = new Label(row2Cmp);
		goodsModelLabel.setID(CustomerAuthorizingDetailProcessor.ID_Label_ModelGoods);
		goodsModelLabel.setForeground(Color.COLOR_BLUE);
		goodsModelLabel.setText("使用已有模板");
		new Label(row2Cmp).setText("  ");
		Label saveModelLabel = new Label(row2Cmp);
		saveModelLabel.setID(CustomerAuthorizingDetailProcessor.ID_Label_SaveModelGoods);
		saveModelLabel.setForeground(Color.COLOR_BLUE);
		saveModelLabel.setText("将下表保存为模板");
		
		row1Cmp.layout();
		row2Cmp.layout();
	}
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn("salesmanName", 150, JWT.LEFT, "商品名称");
		columns[1] = new STableColumn("departmentInfo", 200, JWT.LEFT, "商品属性");
		columns[2] = new STextEditColumn("customerCreditLimit", 100, JWT.LEFT, "单位");
		columns[3] = new STextEditColumn("availableTotalCreditLimit", 150, JWT.CENTER, "授权价格");
		columns[4] = new STableColumn("customerCountUsed", 180, JWT.CENTER, "说明");
		columns[5] = new STableColumn("customerCreditUsed", 150, JWT.CENTER, "授权状态");

		columns[4].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.Multi);
		return tableStyle;
	}

	public String getText(Object element, int columnIndex) {
//		SalesmanCreditItem item = (SalesmanCreditItem) element;
//		switch (columnIndex) {
//		case 0:
//			return item.getSalesmanName();
//		case 1:
//			return item.getDepartmentInfo();
//		case 2:
//			return String.valueOf(item.getCustomerCreditLimit());
//		case 3:
//			return String.valueOf(item.getAvailableTotalCreditLimit());
//		case 4:
//			return String.valueOf(item.getCustomerCountUsed());
//		case 5:
//			return String.valueOf(item.getCustomerCreditUsed());
//		case 6:
//			return String.valueOf(item.getCustomerCreditDayLimit());
//		case 7:
//			return String.valueOf(item.getOrderApprovalLimit());
//		default:
//			return super.getText(element, columnIndex);
//		}
		return "";
	}
}
