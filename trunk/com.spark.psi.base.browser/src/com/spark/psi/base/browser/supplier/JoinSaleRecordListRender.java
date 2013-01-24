package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordItem;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;

public class JoinSaleRecordListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("供应商名称：");
		new Label(headerLeftArea).setID(JoinSaleRecordListProcessor.ID_Label_SupplierName);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("记录数量：");
		new Label(headerLeftArea).setID(JoinSaleRecordListProcessor.ID_Label_Count);
		
		new SSearchText2(headerRightArea).setID(JoinSaleRecordListProcessor.ID_Search);
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, JoinSaleRecordListProcessor.ColumnName.sheetNo.getTitle());
		columns[1] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.materialName.name(), 150, 
				JWT.CENTER, JoinSaleRecordListProcessor.ColumnName.materialName.getTitle());
		columns[2] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.materialCode.name(), 150, 
				JWT.LEFT, JoinSaleRecordListProcessor.ColumnName.materialCode.getTitle());
		columns[3] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.materialNo.name(), 150, 
				JWT.LEFT, JoinSaleRecordListProcessor.ColumnName.materialNo.getTitle());
		columns[4] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.price.name(), 150, 
				JWT.RIGHT, JoinSaleRecordListProcessor.ColumnName.price.getTitle());
		columns[5] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.count.name(), 150, 
				JWT.RIGHT, JoinSaleRecordListProcessor.ColumnName.count.getTitle());
		columns[6] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.amount.name(), 150, 
				JWT.RIGHT, JoinSaleRecordListProcessor.ColumnName.amount.getTitle());
		columns[7] = new STableColumn(JoinSaleRecordListProcessor.ColumnName.isAlreadySettlement.name(), 150, 
				JWT.CENTER, JoinSaleRecordListProcessor.ColumnName.isAlreadySettlement.getTitle());
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		JointVentureRecordItem item = (JointVentureRecordItem)element;
		switch (columnIndex) {
		case 0:
			return item.getSheetNo();
		case 1:
			return item.getGoodsName();
		case 2:
			return item.getGoodsCode();
		case 3:
			return item.getGoodsNo();
		case 4:
			return DoubleUtil.getRoundStr(item.getGoodsPrice());
		case 5:
			return DoubleUtil.getRoundStr(item.getCount());
		case 6:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 7:
			return item.isAlreadySettlement() ? "已结算" : "未结算";
		default:
			break;
		}
		return null;
	}

}
