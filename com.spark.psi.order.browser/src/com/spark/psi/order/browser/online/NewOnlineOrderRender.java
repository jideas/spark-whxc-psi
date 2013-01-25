package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;

public class NewOnlineOrderRender extends SimpleSheetPageRender {

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		GridData gdInput = new GridData();
		gdInput.widthHint = 120;
		if (0 == row) {
			if (0 == column) {
				new Label(baseInfoArea).setText("    ��Ա��");
				Text text = new Text(baseInfoArea, JWT.APPEARANCE3);
				text.setID(NewOnlineOrderProcessor.ID_Text_CustomerName);
				text.setLayoutData(gdInput);
				
				new Label(baseInfoArea).setText("   ");
				
				new Label(baseInfoArea).setText("�ֻ����룺");
				text = new Text(baseInfoArea, JWT.APPEARANCE3);
				text.setRegExp(TextRegexp.MOBILE);
				text.setID(NewOnlineOrderProcessor.ID_Text_CustomerPhone);
				text.setLayoutData(gdInput);
				
				new Label(baseInfoArea).setText("   ");
				
				new Label(baseInfoArea).setText("�� �� �ˣ�");
				text = new Text(baseInfoArea, JWT.APPEARANCE3);
				text.setID(NewOnlineOrderProcessor.ID_Text_Consignee);
				text.setLayoutData(gdInput);
			}
		} else if (1 == row) {
			if (0 == column) {
				new Label(baseInfoArea).setText("�������ڣ�");
				SDatePicker date = new SDatePicker(baseInfoArea);
				date.setID(NewOnlineOrderProcessor.ID_Date_Delivery);
				date.setLayoutData(gdInput);
				
				new Label(baseInfoArea).setText("   ");
				
				new Label(baseInfoArea).setText("����վ�㣺");
				LWComboList list = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
				list.setID(NewOnlineOrderProcessor.ID_List_Station);
				list.setLayoutData(gdInput);
				
				new Label(baseInfoArea).setText("   ");
				
				new Label(baseInfoArea).setText("���͵�ַ��");
				Text text = new Text(baseInfoArea, JWT.APPEARANCE3);
				text.setID(NewOnlineOrderProcessor.ID_Text_Address);
				GridData gdAddress = new GridData();
				gdAddress.widthHint = 200;
				text.setLayoutData(gdAddress);
			}
		}
		
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button button = new Button(sheetButtonArea, JWT.APPEARANCE3);
		button.setText(" �� �� ");
		button.setID(NewOnlineOrderProcessor.ID_Button_Save);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		Button button = new Button(tableButtonArea, JWT.APPEARANCE2);
		button.setText(" �����Ʒ ");
		button.setID(NewOnlineOrderProcessor.ID_Button_AddGoods);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[9];
		columns[0] = new STableColumn(NewOnlineOrderProcessor.ColumnName.code.name(), 150, JWT.LEFT, "���");
		columns[1] = new STableColumn(NewOnlineOrderProcessor.ColumnName.name.name(), 200, JWT.LEFT, "��Ʒ����");
		columns[2] = new STableColumn(NewOnlineOrderProcessor.ColumnName.spec.name(), 100, JWT.CENTER, "���");
		columns[3] = new STableColumn(NewOnlineOrderProcessor.ColumnName.unit.name(), 100, JWT.CENTER, "��λ");
		columns[4] = new STableColumn(NewOnlineOrderProcessor.ColumnName.unitCost.name(), 100, JWT.RIGHT, "����");
		columns[5] = new SNumberEditColumn(NewOnlineOrderProcessor.ColumnName.count.name(), 100, JWT.RIGHT, "����");
		columns[6] = new SNumberEditColumn(NewOnlineOrderProcessor.ColumnName.discountRate.name(), 100, JWT.RIGHT, "�ۿ���");
		columns[7] = new SNumberEditColumn(NewOnlineOrderProcessor.ColumnName.discountAmount.name(), 100, JWT.RIGHT, "�ۿ۶�");
		columns[8] = new SNumberEditColumn(NewOnlineOrderProcessor.ColumnName.amount.name(), 100, JWT.RIGHT, "���");

		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		GoodsItemInfo item = (GoodsItemInfo)element;
		switch(columnIndex) {
		case 1:
			return item.getBaseInfo().getName();
		case 0:
			return item.getBaseInfo().getCode();
		case 2:
			return item.getItemData().getSpec();
		case 3:
			return item.getItemData().getUnit();
		case 4:
			return DoubleUtil.getRoundStr(item.getItemData().getSalePrice(),2);
		}
		return null;
	}


}
