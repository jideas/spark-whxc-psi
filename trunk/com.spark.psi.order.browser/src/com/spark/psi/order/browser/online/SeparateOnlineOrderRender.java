package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseListPageRender;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;

public class SeparateOnlineOrderRender extends BaseListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("�ͻ����ƣ�");
		new Label(headerLeftArea).setID(SeparateOnlineOrderProcessor.ID_Label_CustomerName);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("��ϵ�绰�� ");
		new Label(headerLeftArea).setID(SeparateOnlineOrderProcessor.ID_Label_MobileNo);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("�µ����ڣ� ");
		new Label(headerLeftArea).setID(SeparateOnlineOrderProcessor.ID_Label_BookingDate);
		
		new Label(headerRightArea).setText("����ܽ�");
		Label label = new Label(headerRightArea);
		label.setText("0.00   ");
		label.setID(SeparateOnlineOrderProcessor.ID_Label_TotalAmount);
		
		Button saveButton = new Button(footerRightArea, JWT.APPEARANCE3);
		saveButton.setText(" ȷ����� ");
		saveButton.setID(SeparateOnlineOrderProcessor.ID_Button_Save);
		
		Button cancelButton = new Button(footerRightArea, JWT.APPEARANCE3);
		cancelButton.setText(" ȡ �� ");
		cancelButton.setID(SeparateOnlineOrderProcessor.ID_Button_Cancel);
		
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.goodsName.name(), 130, JWT.LEFT, "��Ʒ����");
		columns[1] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.goodsCode.name(), 120, JWT.LEFT, "��Ʒ���");
		columns[2] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.goodsSpec.name(), 120, JWT.LEFT, "���");
		columns[3] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.unit.name(), 120, JWT.CENTER, "��λ");
		columns[4] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.preCount.name(), 100, JWT.RIGHT, "ԭ��������");
		columns[5] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.price.name(), 100, JWT.RIGHT, "����");
		columns[6] = new SNumberEditColumn(SeparateOnlineOrderProcessor.ColumnName.separateCount.name(), 100, JWT.RIGHT, "�������");
		columns[7] = new STableColumn(SeparateOnlineOrderProcessor.ColumnName.amount.name(), 100, JWT.RIGHT, "��ֽ��");
		
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		OnlineOrderInfoItem item = (OnlineOrderInfoItem)element;
		switch(columnIndex) {
		case 0:
			return item.getGoodsName();
		case 1:
			return item.getGoodsCode();
		case 2:
			return item.getGoodsSpec();
		case 3:
			return item.getUnit();
		case 4:
			return DoubleUtil.getRoundStr(item.getCount());
		case 5:
			return DoubleUtil.getRoundStr(item.getPrice());
		}
		return null;
	}


}
