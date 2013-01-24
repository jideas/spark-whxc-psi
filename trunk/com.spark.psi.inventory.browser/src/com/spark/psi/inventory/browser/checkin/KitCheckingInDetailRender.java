package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
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
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;

/**
 * 其他物品入库单明细列表视图
 * 
 */
public class KitCheckingInDetailRender extends PSIListPageRender {
	
	@Override
	public STableStyle getTableStyle() {
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setRowHeight(25);
		tableStyle.setAutoAddRow(true);
		tableStyle.setPageAble(false);
		return tableStyle;
	}
	
	@Override
	protected void afterFooterRender() {		
		super.afterFooterRender();		
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 确认入库 ");
		button.setID(KitCheckingInDetailProcessor.ID_Button_Check);
	}
	
	@Override
	protected void beforeTableRender() {		
		GridData gd4 = new GridData(GridData.FILL_HORIZONTAL);
		gd4.heightHint = 32;
		Composite composite = new Composite(contentArea);
		composite.setLayoutData(gd4);
		composite.setLayout(new GridLayout(4));			
		GridData gd1 = new GridData();
		gd1.widthHint = 70;		
		GridData gd2 = new GridData();
		gd2.widthHint = 230;		
		SAsteriskLabel lblBePutInStorage = new SAsteriskLabel(composite,"入库仓库：");
		lblBePutInStorage.setLayoutData(gd1);		
		LWComboList cmplstBePutInStorage = new LWComboList(composite,JWT.APPEARANCE3);
		cmplstBePutInStorage.setID(KitCheckingInDetailProcessor.ID_ComboList_StoreList);
		cmplstBePutInStorage.setLayoutData(gd2);		
		Label lblKitSource = new Label(composite,JWT.RIGHT);
		lblKitSource.setText("物品来源：");
		lblKitSource.setLayoutData(gd1);		
		Text txtKitSource = new Text(composite,JWT.APPEARANCE3);
		txtKitSource.setLayoutData(new GridData(GridData.FILL_HORIZONTAL|GridData.GRAB_HORIZONTAL));
		txtKitSource.setID(KitCheckingInDetailProcessor.ID_Text_KitSource);
		txtKitSource.setMaximumLength(500);
	}
	
	@Override
	protected void afterTableRender() {		
		super.afterTableRender();		
		Composite composite = new Composite(contentArea);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite.setBackground(Color.COLOR_WHITE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginLeft = gl.marginTop = gl.marginBottom = 3;
		composite.setLayout(gl);		
		GridData gd1 = new GridData();
		gd1.widthHint = 40;
		Label label = new Label(composite);
		label.setText("备注：");
		label.setLayoutData(gd1);		
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.heightHint = 35;
		Text txtRemark = new Text(composite,JWT.MULTI | JWT.VERTICAL | JWT.WRAP | JWT.APPEARANCE3);
		txtRemark.setLayoutData(gd2);
		txtRemark.setID(KitCheckingInDetailProcessor.ID_Text_Remark);	
		txtRemark.setMaximumLength(1000);
	}

	@Override
	public STableColumn[] getColumns() {		
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STextEditColumn(KitCheckingInDetailProcessor.Columns.kitName.name(), 0, JWT.LEFT, "物品名称");		
		columns[1] = new STextEditColumn(KitCheckingInDetailProcessor.Columns.kitDescription.name(), 0, JWT.LEFT, "物品描述");		
		columns[2] = new STextEditColumn(KitCheckingInDetailProcessor.Columns.unit.name(), 110, JWT.CENTER, "单位");
		columns[3] = new SNumberEditColumn(KitCheckingInDetailProcessor.Columns.count.name(), 110, JWT.RIGHT, "入库数量");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {			
		if (element instanceof String) {
			return "";
		}
		CheckKitItem item = (CheckKitItem) element;
		switch (columnIndex) {
		case 0:
			return item.getKitName();
		case 1:
			return item.getKitDescription();
		case 2:
			return item.getUnit();
		case 3:
			return DoubleUtil.getRoundStr(Double.valueOf(item.getCount()));
		default:
			return "";
		}
	}
}