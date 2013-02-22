package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.split.entity.GoodsSplitItem;

/**
 * �Ѵ�����ɵĳ��ⵥ�б���ͼ
 */
public class NewGoodsSplitListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
//		new SSearchText2(headerRightArea).setID(NewGoodsSplitListProcessor.ID_TEXT_SEARCH);
////		new Button(headerRightArea).setID(NewGoodsSplitListProcessor.ID_BUTTON_SEARCH);
//		//
//		new LWComboList(headerLeftArea,JWT.APPEARANCE3).setID(NewGoodsSplitListProcessor.ID_COMBOLIST_DATEITEM);		
		new Label(headerLeftArea).setText("  ����������");
		new Label(headerLeftArea).setID(NewGoodsSplitListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
		
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" ���� ");
		button.setID(NewGoodsSplitListProcessor.ID_Button_Add); 
	}

	public STableColumn[] getColumns() {
		//���ӱ�ͷ��ô����
		
		STableColumn[] columns = new STableColumn[4];
		
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(NewGoodsSplitListProcessor.Columns.CreateDate.name(), 120, JWT.CENTER, "�Ƶ�����");
		columns[1] = new STableColumn(NewGoodsSplitListProcessor.Columns.SheetNumber.name(), 120, JWT.CENTER, "���ݱ��");
		columns[1].setGrab(true);
		columns[2] = new STableColumn(NewGoodsSplitListProcessor.Columns.Creator.name(), 120, JWT.CENTER, "�Ƶ�");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(NewGoodsSplitListProcessor.Columns.Status.name(), 120, JWT.CENTER, "״̬");
		columns[3].setGrab(true);
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		
			return "";
		
	}

	public String getText(Object element, int columnIndex) {
		GoodsSplitItem item = (GoodsSplitItem) element;
		switch (columnIndex) {
		case 0:
				return DateUtil.dateFromat(item.getCreateDate());
			
		case 1:
			return  StableUtil.toLink( NewGoodsSplitListProcessor.ID_ACTION_EDIT, "", item.getBillNo());
		case 2:
			return item.getCreator();
		case 3:
			return item.getStatus();
		
		default:
			return "";
		}
	}
}