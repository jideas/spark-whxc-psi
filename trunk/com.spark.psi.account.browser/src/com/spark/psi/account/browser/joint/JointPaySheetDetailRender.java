package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfo;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfoItem;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.JointSettlementStatus;

public class JointPaySheetDetailRender extends SimpleSheetPageRender {

	private JointSettlementInfo info = null;
	
	@Override
	public void init(Situation context) {
		if (getArgument() != null) {
			GUID id = (GUID) getArgument();
			info = getContext().find(JointSettlementInfo.class, id);
		}
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0) {
			if (column == 0) {
				new Label(baseInfoArea).setText("供应商： " + info.getSupplierName());
//				new Label(baseInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_Supplier);
				
				new Label(baseInfoArea).setText("    开始日期： " + DateUtil.dateFromat(info.getBeginDate()));
//				new Label(baseInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_Begin);
				
				new Label(baseInfoArea).setText("    结束日期： " + DateUtil.dateFromat(info.getEndDate()));
//				new Label(baseInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_End);
				
				new Label(baseInfoArea).setText("    销售总额： " + DoubleUtil.getRoundStr(info.getSalesAmount()));
//				new Label(baseInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_TotalSaleAmount);
				
				new Label(baseInfoArea).setText("    提成总额： " + DoubleUtil.getRoundStr(info.getPercentageAmount()));
//				new Label(baseInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_TotalPercentageAmount);
				
			} else if (column == 1) {
				if (JointSettlementStatus.Paying == info.getStatus()) {
					new Label(baseInfoArea).setText("已付金额： " + DoubleUtil.getRoundStr(info.getPaidAmount()));
					new Label(baseInfoArea).setText("     已抹零金额： " + DoubleUtil.getRoundStr(info.getMolingAmount()));
				}
			}
		}

	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		new Label(dataInfoArea).setText("调整金额： " );
		new Label(dataInfoArea).setText(DoubleUtil.getRoundStr(info.getAdjustAmount()));
//		new Label(dataInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_AdjustAmount);
		
		new Label(dataInfoArea).setText("应付总额： ");
		new Label(dataInfoArea).setText(DoubleUtil.getRoundStr(info.getAmount()));
//		new Label(dataInfoArea).setID(JointPaySheetDetailProcessor.ID_Label_TotalPayingAmount);
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button button = null;
		if (JointSettlementStatus.Submitted == info.getStatus()) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 批 准 ");
			button.setID(JointPaySheetDetailProcessor.ID_Button_Approval);
			
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 退 回  ");
			button.setID(JointPaySheetDetailProcessor.ID_Button_Reject);
		} else if (JointSettlementStatus.Paying == info.getStatus()) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 付 款  ");
			button.setID(JointPaySheetDetailProcessor.ID_Button_Pay);
		}

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
		return null;
	}

}
