package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;

/**
 * 其他物品入库单明细列表视图
 * 
 */
public class KitCheckingOutDetailRender extends PSIListPageRender {

	@Override
	public STableColumn[] getColumns() {		
		STableColumn[] columns = new STableColumn[5];		
		columns[0] = new STableColumn(KitCheckingOutDetailProcessor.Columns.kitName.name(), 0, JWT.LEFT, "物品名称");		
		columns[1] = new STableColumn(KitCheckingOutDetailProcessor.Columns.kitDescription.name(), 0, JWT.LEFT, "物品描述");		
		columns[2] = new STableColumn(KitCheckingOutDetailProcessor.Columns.unit.name(), 110, JWT.CENTER, "单位");
		columns[3] = new STableColumn(KitCheckingOutDetailProcessor.Columns.inventory.name(), 110, JWT.RIGHT, "库存数量");
		columns[4] = new SNumberEditColumn(KitCheckingOutDetailProcessor.Columns.count.name(), 110, JWT.RIGHT, "出库数量");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {		
		if (element instanceof Kit) {			
			Kit item = (Kit)element;			
			switch (columnIndex) {
			case 0:
				return item.getKitName();
			case 1:
				return item.getKitDesc();
			case 2:
				return item.getKitUnit();
			case 3:
				return String.valueOf(item.getCount());//DoubleUtil.getRoundStr(item.getCount());
			case 4:
				return "";
			}
		} 
		return "";
	}	
	
	@Override
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
			case 3:
				return 0;
			default:
				return -1;
		} 
	}
	
	@Override
	protected void beforeTableRender() {		
		GridData gd4 = new GridData(GridData.FILL_HORIZONTAL);
		gd4.heightHint = 60;
		Composite composite = new Composite(contentArea);
		composite.setLayoutData(gd4);
		composite.setLayout(new GridLayout(4));		
		GridData gd1 = new GridData();
		gd1.widthHint = 70;		
		GridData gd2 = new GridData();
		gd2.widthHint = 230;		
		SAsteriskLabel lblBePutInStorage = new SAsteriskLabel(composite,"出库仓库：");
		lblBePutInStorage.setLayoutData(gd1);		
		LWComboList comboListStore = new LWComboList(composite,JWT.APPEARANCE3);
		comboListStore.setLayoutData(gd2);
		comboListStore.setID(KitCheckingOutDetailProcessor.ID_ComboList_Store);		
		Label lblSource = new Label(composite,JWT.RIGHT);
		lblSource.setText("物品来源：");
		lblSource.setLayoutData(gd1);		
		Text txtSource = new Text(composite,JWT.APPEARANCE3);
		txtSource.setLayoutData(new GridData(GridData.FILL_HORIZONTAL|GridData.GRAB_HORIZONTAL));
		txtSource.setID(KitCheckingOutDetailProcessor.ID_Text_Source);
		txtSource.setMaximumLength(1000);
		Label lblPurpose = new Label(composite,JWT.RIGHT);
		lblPurpose.setText("用　　途：");
		lblPurpose.setLayoutData(gd1);
		GridData gd3 = new GridData();
		gd3.horizontalSpan = 3;
		gd3.grabExcessHorizontalSpace = true;
		gd3.horizontalAlignment = JWT.FILL;		
		Text txtPurpose = new Text(composite,JWT.APPEARANCE3);
		txtPurpose.setLayoutData(gd3);
		txtPurpose.setID(KitCheckingOutDetailProcessor.ID_Text_Purpose);
		txtPurpose.setMaximumLength(1000);
	}
	
	@Override
	protected void afterTableRender() {		
		super.afterTableRender();		
		CBorder border = new CBorder();
		border.setColor(0x78a9bf);
		Composite composite = new Composite(contentArea);
		GridData gdCmp = new GridData(GridData.FILL_HORIZONTAL);
		gdCmp.verticalIndent = -1;
		gdCmp.heightHint = 52;
		composite.setLayoutData(gdCmp);
		composite.setBackground(Color.COLOR_WHITE);
		composite.setBorder(border);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		gl.marginLeft = gl.marginTop = gl.marginBottom = 2;
		composite.setLayout(gl);		
		
		GridData layoutData = new GridData();
		layoutData.verticalAlignment = JWT.TOP;
		Button btnAddDetail = new Button(composite,JWT.APPEARANCE2);
		btnAddDetail.setText(" 添加明细 ");
		btnAddDetail.setID(KitCheckingOutDetailProcessor.ID_Button_AddDetail);	
		btnAddDetail.setLayoutData(layoutData);
		GridData gd1 = new GridData();
		gd1.widthHint = 50;
		gd1.verticalAlignment = JWT.TOP;
		gd1.verticalIndent = 5;
		Label label = new Label(composite,JWT.RIGHT);
		label.setText("备注：");
		label.setLayoutData(gd1);		
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.heightHint = 50;
		Text txtRemark = new Text(composite,JWT.MULTI | JWT.VERTICAL | JWT.WRAP | JWT.APPEARANCE3);
		txtRemark.setLayoutData(gd2);	
		txtRemark.setID(KitCheckingOutDetailProcessor.ID_Text_Remark);
		txtRemark.setMaximumLength(1000);		
		GridLayout g2 = new GridLayout();
		g2.numColumns = 6;
		g2.marginTop = 3;
		Composite cmp2 = new Composite(contentArea);
		cmp2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmp2.setLayout(g2);		
		GridData gd3 = new GridData();
		gd3.widthHint = 60;
		Label lblDeliveryPerson = new Label(cmp2,JWT.RIGHT);
		lblDeliveryPerson.setText("提 货 人：");
		lblDeliveryPerson.setLayoutData(gd3);		
		GridData gd5 = new GridData();
		gd5.widthHint = 160;
		Text txtDeliveryPerson = new Text(cmp2, JWT.APPEARANCE3);
		txtDeliveryPerson.setLayoutData(gd5);
		txtDeliveryPerson.setID(KitCheckingOutDetailProcessor.ID_Text_DeliveryPerson);
		txtDeliveryPerson.setMaximumLength(30);		
		GridData gd4 = new GridData();
		gd4.widthHint = 70;
		Label lblDeliveryDepartment = new Label(cmp2,JWT.RIGHT);
		lblDeliveryDepartment.setText("提货单位：");
		lblDeliveryDepartment.setLayoutData(gd4);		
		Text txtDeliveryDepartment = new Text(cmp2, JWT.APPEARANCE3);
		txtDeliveryDepartment.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtDeliveryDepartment.setID(KitCheckingOutDetailProcessor.ID_Text_DeliveryDepartment);
		txtDeliveryDepartment.setMaximumLength(100);		
		Label lblVoucherNumber = new Label(cmp2,JWT.RIGHT);
		lblVoucherNumber.setText("凭 证 号：");
		lblVoucherNumber.setLayoutData(gd4);		
		Text txtVoucherNumber = new Text(cmp2, JWT.APPEARANCE3);
		txtVoucherNumber.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtVoucherNumber.setID(KitCheckingOutDetailProcessor.ID_Text_VoucherNumber);
		txtVoucherNumber.setMaximumLength(50);
	}
	
	@Override
	protected void afterFooterRender() {		
		super.afterFooterRender();		
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 确认出库 ");
		button.setID(KitCheckingOutDetailProcessor.ID_Button_Check);
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}