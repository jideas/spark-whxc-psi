package com.spark.psi.inventory.browser.checkin;

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
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SEditColumn;
import com.spark.common.components.table.edit.SFormula;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.inventory.browser.checkin.NewIrregularCheckingInProcessor.Column;
import com.spark.psi.inventory.browser.internal.InventoryImages;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;

public class NewIrregularCheckingInRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Button confirmBtn = new Button(footerRightArea, JWT.APPEARANCE3);
		confirmBtn.setID(NewIrregularCheckingInProcessor.ID_Button_Confirm);
		confirmBtn.setText(" 确认入库 ");
	}

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
		addGoodsBtn.setID(NewIrregularCheckingInProcessor.ID_Button_AddGoods);
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
		memoText.setID(NewIrregularCheckingInProcessor.ID_Text_Memo);

		label = new Label(tableFooterArea);
		label.setText("    入库金额：");
		label.setLayoutData(gdLabel);

		Label amountLabel = new Label(tableFooterArea);
		GridData gdAmount = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gdAmount.widthHint = 150;
		amountLabel.setLayoutData(gdAmount);
		amountLabel.setID(NewIrregularCheckingInProcessor.ID_Label_Amount);
	}

	@Override
	protected void beforeTableRender() {
		Composite tableHeaderArea = new Composite(contentArea);
		GridLayout glTableHeader = new GridLayout();
		glTableHeader.numColumns = 5;
		glTableHeader.horizontalSpacing = 3;
		glTableHeader.marginBottom = 10;
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData gdInput = new GridData(GridData.FILL_HORIZONTAL);

		SAsteriskLabel noLabel = new SAsteriskLabel(tableHeaderArea, "供应商：");
		noLabel.setLayoutData(gdLabel);
		Text providerText = new Text(tableHeaderArea, JWT.APPEARANCE3 | JWT.BUTTON);
		providerText.setLayoutData(gdInput);
		providerText.setImage(InventoryImages.getImage("/images/checkin/saas_mark_find.png"));
		providerText.setID(NewIrregularCheckingInProcessor.ID_Text_Provider);

		new Label(tableHeaderArea).setText("    ");

		SAsteriskLabel storeLabel = new SAsteriskLabel(tableHeaderArea, "入库仓库：");
		storeLabel.setLayoutData(gdLabel);
		LWComboList storeList = new LWComboList(tableHeaderArea, JWT.APPEARANCE3);
		storeList.setLayoutData(gdInput);
		storeList.setID(NewIrregularCheckingInProcessor.ID_List_Store);

		SAsteriskLabel purchaserLabel = new SAsteriskLabel(tableHeaderArea, "采购人：");
		purchaserLabel.setLayoutData(gdLabel);
		Text puchaserText = new Text(tableHeaderArea, JWT.APPEARANCE3);
		puchaserText.setLayoutData(gdInput);
		puchaserText.setID(NewIrregularCheckingInProcessor.ID_Text_Purchaser);

		new Label(tableHeaderArea).setText("    ");

		Label label = new Label(tableHeaderArea);
		label.setText("采购日期：");
		label.setLayoutData(gdLabel);
		SDatePicker datePicker = new SDatePicker(tableHeaderArea);
		datePicker.setLayoutData(gdInput);
		datePicker.setID(NewIrregularCheckingInProcessor.ID_Date_PurchaseDate);
		tableHeaderArea.setLayout(glTableHeader);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(NewIrregularCheckingInProcessor.Column.code.name(), 100, JWT.LEFT,
				NewIrregularCheckingInProcessor.Column.code.getTitle());
		columns[1] = new STableColumn(NewIrregularCheckingInProcessor.Column.number.name(), 100, JWT.LEFT,
				NewIrregularCheckingInProcessor.Column.number.getTitle());
		columns[2] = new STableColumn(NewIrregularCheckingInProcessor.Column.name.name(), 100, JWT.LEFT,
				NewIrregularCheckingInProcessor.Column.name.getTitle());
		columns[3] = new STableColumn(NewIrregularCheckingInProcessor.Column.property.name(), 200, JWT.LEFT,
				NewIrregularCheckingInProcessor.Column.property.getTitle());
		columns[4] = new STableColumn(NewIrregularCheckingInProcessor.Column.unit.name(), 100, JWT.CENTER,
				NewIrregularCheckingInProcessor.Column.unit.getTitle());
		SNumberEditColumn countEditColumn = new SNumberEditColumn(
				NewIrregularCheckingInProcessor.Column.checkingInCount.name(), 100, JWT.RIGHT,
				NewIrregularCheckingInProcessor.Column.checkingInCount.getTitle());
		countEditColumn.setDecimal(2);
		columns[5] = countEditColumn;
		columns[6] = new SNumberEditColumn(NewIrregularCheckingInProcessor.Column.price.name(), 100, JWT.RIGHT,
				NewIrregularCheckingInProcessor.Column.price.getTitle());
		columns[7] = new SNumberEditColumn(NewIrregularCheckingInProcessor.Column.amount.name(), 100, JWT.RIGHT,
				NewIrregularCheckingInProcessor.Column.amount.getTitle());
		((SNumberEditColumn) columns[6]).setDecimal(2);
		((SNumberEditColumn) columns[7]).setDecimal(2);

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[3].setGrab(true);
		columns[2].setGrab(true);
		columns[4].setGrab(true);
		columns[5].setGrab(true);
		columns[6].setGrab(true);

		addColumnsSAsignFormula(Column.checkingInCount, columns[5]);
		addColumnsSAsignFormula(Column.price, columns[6]);
		addColumnsSAsignFormula(Column.amount, columns[7]);
		return columns;
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
			return goodsItem.getItemData().getMaterialSpec();
		case 4:
			return goodsItem.getItemData().getUnit();
		case 5:
		case 6:
		case 7:
		default:
			return null;
		}
	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		if (columnIndex > 4) {
			return 2;
		}
		return super.getDecimal(element, columnIndex);
	}

	protected void addColumnsSAsignFormula(Column e, STableColumn c) {
		if (!(c instanceof SEditColumn)) {
			return;
		}
		SEditColumn column = (SEditColumn) c;
		final String $count = "[" + Column.checkingInCount.name() + "]";
		final String $price = "[" + Column.price.name() + "]";
		final String $amount = "[" + Column.amount.name() + "]";
		switch (e) {
		case checkingInCount:
			column.setFormulas(new SFormula[] { new SAsignFormula("(" + $count + "*" + $price + ")",
					NewIrregularCheckingInProcessor.Column.amount.name()) });// $amount)});
			break;
		case price:
			column.setFormulas(new SFormula[] { new SAsignFormula("(" + $count + "*" + $price + ")",
					NewIrregularCheckingInProcessor.Column.amount.name()) });// $amount)});
			break;
		case amount:
			column.setFormulas(new SFormula[] { new SAsignFormula("(" + $amount + "/" + $count + ")",
					NewIrregularCheckingInProcessor.Column.price.name()) });// $amount)});
			break;
		default:
			break;
		}
	}
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
