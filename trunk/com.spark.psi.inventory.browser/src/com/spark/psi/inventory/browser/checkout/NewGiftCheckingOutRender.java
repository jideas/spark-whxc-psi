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
import com.spark.psi.inventory.browser.checkout.NewGiftCheckingOutProcessor.Column;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;

public class NewGiftCheckingOutRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Button confirmBtn = new Button(footerRightArea, JWT.APPEARANCE3);
		confirmBtn.setID(NewGiftCheckingOutProcessor.ID_Button_Confirm);
		confirmBtn.setText(" 确认出库 ");
	}

	private LWComboList storeList;

	@Override
	protected void afterTableRender() {
		super.afterTableRender();
		Composite tableFooterArea = new Composite(contentArea);
		GridLayout glTableFooter = new GridLayout();
		glTableFooter.numColumns = 5;
		glTableFooter.marginTop = 10;
		glTableFooter.marginLeft = 10;
		glTableFooter.marginRight = 10;
		glTableFooter.marginBottom = 10;
		glTableFooter.horizontalSpacing = 0;
		tableFooterArea.setLayout(glTableFooter);
		tableFooterArea.setBackground(Color.COLOR_WHITE);
		tableFooterArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));
		GridData gdTableFooter = new GridData(GridData.FILL_HORIZONTAL);
		gdTableFooter.verticalIndent = -1;
		tableFooterArea.setLayoutData(gdTableFooter);

		Button addGoodsBtn = new Button(tableFooterArea, JWT.APPEARANCE2);
		addGoodsBtn.setText(" 添加材料 ");
		addGoodsBtn.setID(NewGiftCheckingOutProcessor.ID_Button_AddGoods);
		GridData gdBtn = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gdBtn.heightHint = 30;
		addGoodsBtn.setLayoutData(gdBtn);

		Label label = new Label(tableFooterArea);
		label.setText("    备注：");
		GridData gdLabel = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
		label.setLayoutData(gdLabel);

		Text memoText = new Text(tableFooterArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		GridData gdMemoText = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoText.heightHint = 50;
		memoText.setLayoutData(gdMemoText);
		memoText.setID(NewGiftCheckingOutProcessor.ID_Text_Memo);
	}

	@Override
	protected void beforeTableRender() {
		Composite tableHeaderArea = new Composite(contentArea);
		GridLayout glTableHeader = new GridLayout();
		glTableHeader.numColumns = 8;
		glTableHeader.horizontalSpacing = 3;
		glTableHeader.marginBottom = 10;
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData gdInput = new GridData(GridData.FILL_HORIZONTAL);

		SAsteriskLabel storeLabel = new SAsteriskLabel(tableHeaderArea, "出库仓库：");
		storeLabel.setLayoutData(gdLabel);
		storeList = new LWComboList(tableHeaderArea, JWT.APPEARANCE3);
		storeList.setLayoutData(gdInput);
		storeList.setID(NewGiftCheckingOutProcessor.ID_List_Store);
		tableHeaderArea.setLayout(glTableHeader);
		new Label(tableHeaderArea);
		new Label(tableHeaderArea);
		new Label(tableHeaderArea);
		new Label(tableHeaderArea);
		new Label(tableHeaderArea);
		new Label(tableHeaderArea);

		Label label = new Label(tableHeaderArea);
		label.setText("提货人：");
		label.setLayoutData(gdLabel);
		Text t = new Text(tableHeaderArea, JWT.APPEARANCE3);
		t.setID(NewGiftCheckingOutProcessor.ID_Text_DeliveryPerson);
		new Label(tableHeaderArea).setText("    提货单位：");
		t = new Text(tableHeaderArea, JWT.APPEARANCE3);
		t.setID(NewGiftCheckingOutProcessor.ID_Text_DeliveryUnit);

		new Label(tableHeaderArea).setText("    凭证号：");
		t = new Text(tableHeaderArea, JWT.APPEARANCE3);
		t.setID(NewGiftCheckingOutProcessor.ID_Text_VoucherNumber);

	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(NewGiftCheckingOutProcessor.Column.code.name(), 100, JWT.LEFT,
				NewGiftCheckingOutProcessor.Column.code.getTitle());
		columns[1] = new STableColumn(NewGiftCheckingOutProcessor.Column.number.name(), 100, JWT.LEFT,
				NewGiftCheckingOutProcessor.Column.number.getTitle());
		columns[2] = new STableColumn(NewGiftCheckingOutProcessor.Column.name.name(), 100, JWT.LEFT,
				NewGiftCheckingOutProcessor.Column.name.getTitle());
		columns[3] = new STableColumn(NewGiftCheckingOutProcessor.Column.property.name(), 200, JWT.LEFT,
				NewGiftCheckingOutProcessor.Column.property.getTitle());
		columns[4] = new STableColumn(NewGiftCheckingOutProcessor.Column.unit.name(), 100, JWT.CENTER,
				NewGiftCheckingOutProcessor.Column.unit.getTitle()); 
		columns[5] = new SNumberEditColumn(NewGiftCheckingOutProcessor.Column.count.name(), 100, JWT.RIGHT,
				NewGiftCheckingOutProcessor.Column.count.getTitle());

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		columns[5].setGrab(true); 
		return columns;
	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		if (columnIndex > 4) {
			return 2;
		}
		return -1;
	}
	@Override
	public String getText(Object element, int columnIndex) {
		MaterialsItemInfo goodsItem = (MaterialsItemInfo) element;
		switch (columnIndex) {
		case 0:
			return goodsItem.getBaseInfo().getCode();
		case 1:
			return goodsItem.getItemData().getMaterialNo();
		case 2:
			return goodsItem.getBaseInfo().getName();
		case 3:
			return goodsItem.getItemData().getPropertiesWithoutUnit();
		case 4:
			return goodsItem.getItemData().getUnit();
		default:
			return null;
		}
	}

	protected void addColumnsSAsignFormula(Column e, STableColumn c) {
		return;
	}
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
