package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

public class IrregularCheckingInDetailRender extends PSIListPageRender {

	@Override
	protected void beforeTableRender() {
		Composite tableHeaderArea = new Composite(contentArea);
		GridLayout glTableHeader = new GridLayout();
		glTableHeader.numColumns = 6;
		glTableHeader.horizontalSpacing = 3;
		glTableHeader.marginBottom = 10;
		glTableHeader.verticalSpacing = 10;
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		GridData gdInput = new GridData(GridData.FILL_HORIZONTAL);
		tableHeaderArea.setLayout(glTableHeader);

		Label label = new Label(tableHeaderArea);
		label.setLayoutData(gdLabel);
		label.setText("入库单号：");
		label = new Label(tableHeaderArea);
		label.setLayoutData(gdInput);
		label.setID(IrregularCheckingInDetailProcessor.ID_Label_SheetNumber);

		label = new Label(tableHeaderArea);
		label.setLayoutData(gdLabel);
		label.setText("        入库类型：");
		label = new Label(tableHeaderArea);
		label.setLayoutData(gdInput);
		label.setID(IrregularCheckingInDetailProcessor.ID_Label_SheetType);

		label = new Label(tableHeaderArea);
		label.setLayoutData(gdLabel);
		label.setText("        入库仓库：");
		label = new Label(tableHeaderArea);
		label.setLayoutData(gdInput);
		label.setID(IrregularCheckingInDetailProcessor.ID_Label_StoreName);

		label = new Label(tableHeaderArea);
		label.setLayoutData(gdLabel);
		label.setText("供应商：");
		label = new Label(tableHeaderArea);
		label.setLayoutData(gdInput);
		label.setID(IrregularCheckingInDetailProcessor.ID_Label_Supplier);

		label = new Label(tableHeaderArea);
		label.setLayoutData(gdLabel);
		label.setText("        采购人：");
		label = new Label(tableHeaderArea);
		label.setLayoutData(gdInput);
		label.setID(IrregularCheckingInDetailProcessor.ID_Label_Purchaser);

		label = new Label(tableHeaderArea);
		label.setLayoutData(gdLabel);
		label.setText("        采购日期：");
		label = new Label(tableHeaderArea);
		label.setLayoutData(gdInput);
		label.setID(IrregularCheckingInDetailProcessor.ID_Label_PurchaseDate);
	}

	@Override
	protected void afterTableRender() {
		super.afterTableRender();
		Composite tableFooterArea = new Composite(contentArea);
		GridLayout glTableFooter = new GridLayout();
		glTableFooter.numColumns = 4;
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

		Label label = new Label(tableFooterArea);
		label.setText("备注：");
		GridData gdLabel = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
		label.setLayoutData(gdLabel);

		Text memoText = new Text(tableFooterArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		GridData gdMemoText = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoText.heightHint = 50;
		memoText.setLayoutData(gdMemoText);
		memoText.setEnabled(false);
		memoText.setID(IrregularCheckingInDetailProcessor.ID_Text_Memo);

		label = new Label(tableFooterArea);
		label.setText("    入库金额：");
		label.setLayoutData(gdLabel);

		Label amountLabel = new Label(tableFooterArea);
		GridData gdAmount = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gdAmount.widthHint = 150;
		amountLabel.setLayoutData(gdAmount);
		amountLabel.setID(IrregularCheckingInDetailProcessor.ID_Label_Amount);
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		Composite tempArea = new Composite(footerLeftArea);
		tempArea.setLayoutData(GridData.INS_FILL_BOTH);
		GridLayout glTemp = new GridLayout();
		glTemp.numColumns = 4;
		glTemp.marginTop = 5;
		glTemp.horizontalSpacing = 0;
		tempArea.setLayout(glTemp);

		new Label(tempArea).setText("确认入库：");
		new Label(tempArea).setID(IrregularCheckingInDetailProcessor.ID_Label_CheckinInfo); 
	}

	@Override
	public String getText(Object element, int columnIndex) {
		CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
		switch (columnIndex) {
		case 0:
			return item.getGoodsCode();
		case 1:
			return item.getGoodsNo();
		case 2:
			return item.getGoodsName();
		case 3:
			return item.getGoodsSpec();
		case 4:
			return item.getUnit();
		case 5:
			return DoubleUtil.getRoundStr(item.getRealCount());
		case 6:
			return DoubleUtil.getRoundStr(item.getPrice());
		case 7:
			return DoubleUtil.getRoundStr(item.getAmount());
		default:
			return null;
		}
	}

	@Override
	public STableColumn[] getColumns() {
		// STableColumn column[]
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
		columns[5] = new SNumberEditColumn(NewIrregularCheckingInProcessor.Column.checkingInCount.name(), 100, JWT.RIGHT,
				NewIrregularCheckingInProcessor.Column.checkingInCount.getTitle());
		columns[6] = new SNumberEditColumn(NewIrregularCheckingInProcessor.Column.price.name(), 100, JWT.RIGHT,
				NewIrregularCheckingInProcessor.Column.price.getTitle());
		columns[7] = new SNumberEditColumn(NewIrregularCheckingInProcessor.Column.amount.name(), 100, JWT.RIGHT,
				NewIrregularCheckingInProcessor.Column.amount.getTitle());

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[4].setGrab(true);
		columns[5].setGrab(true);
		columns[6].setGrab(true);
		columns[7].setGrab(true);

		return columns;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
		switch (columnIndex) {
		case 1:
			return item.getGoodsName();
		case 2:
			return item.getGoodsSpec();
		default:
			return null;
		}
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

}
