package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.checkout.entity.CheckoutSheetItem;

/**
 * �Ѵ�����ɵĳ��ⵥ�б���ͼ
 */
public class ProcessedCheckingOutListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(ProcessedCheckingOutListProcessor.ID_TEXT_SEARCH);
//		new Button(headerRightArea).setID(ProcessedCheckingOutListProcessor.ID_BUTTON_SEARCH);
		//
		new LWComboList(headerLeftArea,JWT.APPEARANCE3).setID(ProcessedCheckingOutListProcessor.ID_COMBOLIST_DATEITEM);		
		new Label(headerLeftArea).setText("  ���ⵥ������");
		new Label(headerLeftArea).setID(ProcessedCheckingOutListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
	}

	public STableColumn[] getColumns() {
		//���ӱ�ͷ��ô����
		
		STableColumn[] columns = new STableColumn[6];
		
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(ProcessedCheckingOutListProcessor.Columns.LastCheckoutDate.name(), 120, JWT.CENTER, "��������");
		
		columns[1] = new STableColumn(ProcessedCheckingOutListProcessor.Columns.SheetNumber.name(), 120, JWT.CENTER, "���ⵥ��");
		columns[1].setGrab(true);

		columns[2] = new STableColumn(ProcessedCheckingOutListProcessor.Columns.Type.name(), 120, JWT.CENTER, "��������");
		columns[2].setGrab(true);

		columns[3] =new STableColumn(ProcessedCheckingOutListProcessor.Columns.RelatedNumber.name(), 120, JWT.CENTER, "��ص���");
		columns[3].setGrab(true);		
		
		columns[4] = new STableColumn(ProcessedCheckingOutListProcessor.Columns.StoreName.name(), 120, JWT.CENTER, "�ֿ�");
		columns[4].setGrab(true); 
		
		columns[5] = new STableColumn(ProcessedCheckingOutListProcessor.Columns.CheckoutEmployees.name(), 120, JWT.CENTER, "ȷ�ϳ���");
		columns[5].setGrab(true);
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		CheckoutSheetItem item = (CheckoutSheetItem) element;
		switch (columnIndex) {
		case 5:
			return item.getCheckoutPersonName();
		default:
			return "";
		}
	}

	public String getText(Object element, int columnIndex) {
		CheckoutSheetItem item = (CheckoutSheetItem) element;
		switch (columnIndex) {
		case 0:
			if(CheckIsNull.isEmpty(item.getCheckoutPersonName()))
			{
				return "-";
			}
			else
			{
				return DateUtil.dateFromat(item.getCheckoutDate());
			}
		case 1:
			return StableUtil.toLink( ProcessedCheckingOutListProcessor.ID_ACTION_EDIT, "", item.getBillsNo());
		case 2:
			return item.getSheetType().getName();
		case 3:
			return item.getRelaBillsNo();
		case 4:
			return item.getStoreName(); 
		case 5:
			return item.getCheckoutPersonName();
		default:
			return "";
		}
	}
}