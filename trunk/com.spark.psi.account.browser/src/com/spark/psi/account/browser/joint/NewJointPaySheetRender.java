package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfoItem;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;

public class NewJointPaySheetRender extends SimpleSheetPageRender {

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0) {
			if (column == 0) {
				GridData gdShowLabel = new GridData();
				gdShowLabel.widthHint = 120;
				
				Label label = new Label(baseInfoArea);
				label.setText("销售总额： ");
				label = new Label(baseInfoArea);
				label.setID(NewJointPaySheetProcessor.ID_Label_TotalSaleAmount);
				label.setLayoutData(gdShowLabel);
				new Label(baseInfoArea).setText(" ");
				
				label = new Label(baseInfoArea);
				label.setText("提成总额： ");
				label = new Label(baseInfoArea);
				label.setID(NewJointPaySheetProcessor.ID_Label_TotalPercentageAmount);
				label.setLayoutData(gdShowLabel);
				
			} else if (column == 1) {
				new Label(baseInfoArea).setText("请选择供应商：");
				Text text = new Text(baseInfoArea, JWT.APPEARANCE3 | JWT.BUTTON);
				text.setButtonVisible(true);
				text.setID(NewJointPaySheetProcessor.ID_Text_Supplier);
				
				new Label(baseInfoArea).setText("    ");
				
				new Label(baseInfoArea).setText("开始日期：");
				new SDatePicker(baseInfoArea).setID(NewJointPaySheetProcessor.ID_Date_Begin);
				new Label(baseInfoArea).setText(" 结束日期：");
				new SDatePicker(baseInfoArea).setID(NewJointPaySheetProcessor.ID_Date_End);
				
				new Label(baseInfoArea).setText(" ");
				
				Button button = new Button(baseInfoArea, JWT.APPEARANCE3);
				button.setText(" 确定 ");
				button.setID(NewJointPaySheetProcessor.ID_Button_ConfirmSelect);
			}
		}
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		new Label(dataInfoArea).setText("调整金额：");
		new Text(dataInfoArea, JWT.APPEARANCE3).setID(NewJointPaySheetProcessor.ID_Text_AdjustAmount);
		new Label(dataInfoArea).setText("应付总额：");
		new Text(dataInfoArea, JWT.APPEARANCE3).setID(NewJointPaySheetProcessor.ID_Text_TotalPayingAmount);
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button button = new Button(sheetButtonArea, JWT.APPEARANCE3);
		button.setText(" 保 存 ");
		button.setID(NewJointPaySheetProcessor.ID_Button_Save);
		
		button = new Button(sheetButtonArea, JWT.APPEARANCE3);
		button.setText(" 提 交 ");
		button.setID(NewJointPaySheetProcessor.ID_Button_Submit);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		// TODO Auto-generated method stub

	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(NewJointPaySheetProcessor.ColumnName.materialName.name(), 120, 
				JWT.CENTER, NewJointPaySheetProcessor.ColumnName.materialName.getTitle());
		columns[1] = new STableColumn(NewJointPaySheetProcessor.ColumnName.materialCode.name(), 150, 
				JWT.LEFT, NewJointPaySheetProcessor.ColumnName.materialCode.getTitle());
		columns[2] = new STableColumn(NewJointPaySheetProcessor.ColumnName.materialNo.name(), 150, 
				JWT.LEFT, NewJointPaySheetProcessor.ColumnName.materialNo.getTitle());
		columns[3] = new STableColumn(NewJointPaySheetProcessor.ColumnName.price.name(), 100, 
				JWT.RIGHT, NewJointPaySheetProcessor.ColumnName.price.getTitle());
		columns[4] = new STableColumn(NewJointPaySheetProcessor.ColumnName.count.name(), 100, 
				JWT.RIGHT, NewJointPaySheetProcessor.ColumnName.count.getTitle());
		columns[5] = new STableColumn(NewJointPaySheetProcessor.ColumnName.amount.name(), 120, 
				JWT.RIGHT, NewJointPaySheetProcessor.ColumnName.amount.getTitle());
		columns[6] = new STableColumn(NewJointPaySheetProcessor.ColumnName.percentage.name(), 100, 
				JWT.RIGHT, NewJointPaySheetProcessor.ColumnName.percentage.getTitle());
		columns[7] = new STableColumn(NewJointPaySheetProcessor.ColumnName.percentageAmount.name(), 120, 
				JWT.RIGHT, NewJointPaySheetProcessor.ColumnName.percentageAmount.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof JointSettlementInfoItem) {
			JointSettlementInfoItem item = (JointSettlementInfoItem)element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsName();
			case 1:
				return item.getGoodsCode();
			case 2:
				return item.getGoodsNo();
			case 3:
				return DoubleUtil.getRoundStr(item.getGoodsPrice());
			case 4:
				return DoubleUtil.getRoundStr(item.getCount());
			case 5:
				return DoubleUtil.getRoundStr(item.getAmount());
			case 6:
				return DoubleUtil.getRoundStr(item.getPercentage());
			case 7:
				return DoubleUtil.getRoundStr(item.getPercentageAmount());
			}
		} else {
			RecordShowItem item = (RecordShowItem)element;
			switch(columnIndex) {
			case 0:
				return item.getGoodsName();
			case 1:
				return item.getGoodsCode();
			case 2:
				return item.getGoodsNo();
			case 3:
				return DoubleUtil.getRoundStr(item.getGoodsPrice());
			case 4:
				return DoubleUtil.getRoundStr(item.getCount());
			case 5:
				return DoubleUtil.getRoundStr(item.getAmount());
			case 6:
				return DoubleUtil.getRoundStr(item.getPercentage());
			case 7:
				return DoubleUtil.getRoundStr(item.getPercentageAmount());
			}
		}
		return null;
	}

}
