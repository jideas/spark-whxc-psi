package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;

/**
 * �����˻���ⵥ�б���ͼ
 */
public class PurchaseReturnCheckingOutListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(PurchaseReturnCheckingOutListProcessor.ID_TEXT_SEARCH);
//		new Button(headerRightArea).setID(PurchaseReturnCheckingOutListProcessor.ID_BUTTON_SEARCH);	
		//
		new Label(headerLeftArea).setText("���ⵥ������");
		new Label(headerLeftArea).setID(PurchaseReturnCheckingOutListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
	}

	
	public STableColumn[] getColumns() {
		//���ӱ�ͷ��ô����
		
		STableColumn[] columns = new STableColumn[4];
		
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(PurchaseReturnCheckingOutListProcessor.Columns.CreateDate.name(), 100, JWT.CENTER, "�Ƶ�����");
		columns[0].setSortable(true);

		columns[1] =new STableColumn(PurchaseReturnCheckingOutListProcessor.Columns.RelatedNumber.name(), 0, JWT.CENTER, "��ص���");
		columns[1].setGrab(true);	
		columns[1].setSortable(true);
		
		columns[2] = new STableColumn(PurchaseReturnCheckingOutListProcessor.Columns.StoreName.name(), 0, JWT.CENTER, "�ֿ�");
		columns[2].setGrab(true);
		columns[2].setSortable(true);
		
		columns[3] = new STableColumn(PurchaseReturnCheckingOutListProcessor.Columns.status.name(), 200, JWT.CENTER, "����״̬");
		columns[3].setSortable(true); 
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		CheckingOutItem item = (CheckingOutItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCreateDate()); 
		case 1:
			return item.getRelaBillsNo();
		case 2:
			return item.getStoreName();
		case 3:
			return item.getStatus().getName(); 
		default:
			return "";
		}
	}
}