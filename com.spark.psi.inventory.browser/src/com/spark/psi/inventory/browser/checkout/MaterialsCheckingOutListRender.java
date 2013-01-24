package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;

/**
 * ���۳��ⵥ�б���ͼ
 */
public class MaterialsCheckingOutListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea).setID(MaterialsCheckingOutListProcessor.ID_TEXT_SEARCH);
		new Label(headerLeftArea).setText("��������������");
		new Label(headerLeftArea).setID(MaterialsCheckingOutListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
	}
	
	public STableColumn[] getColumns() {
		//���ӱ�ͷ��ô����
		
		STableColumn[] columns = new STableColumn[6];
		
		//��Ҫ��SheetId ��ȡ������ID   
		columns[0] = new STableColumn(MaterialsCheckingOutListProcessor.Columns.PlanCheckoutDate.name(), 100, JWT.CENTER, "Ԥ�Ƴ�������");
		columns[0].setSortable(true);
		
		columns[1] = new STableColumn(MaterialsCheckingOutListProcessor.Columns.RelatedNumber.name(), 200, JWT.CENTER, "��ص���");//entity�ӿ��е���ƴд����getSeetNumber
		columns[1].setGrab(true);
		columns[1].setSortable(true);

		columns[2] =new STableColumn(MaterialsCheckingOutListProcessor.Columns.CheckoutType.name(), 200, JWT.CENTER, "��������");
		columns[2].setGrab(true);
		columns[2].setSortable(true);
		columns[3] =new STableColumn(MaterialsCheckingOutListProcessor.Columns.StoreName.name(), 200, JWT.CENTER, "�ֿ�");
		columns[3].setGrab(true);
		columns[3].setSortable(true);
		
		columns[4] = new STableColumn(MaterialsCheckingOutListProcessor.Columns.status.name(), 200, JWT.CENTER, "����״̬");
		columns[4].setGrab(true);
		columns[4].setSortable(true);
		
		columns[5] = new STableColumn(MaterialsCheckingOutListProcessor.Columns.CreateDate.name(), 200, JWT.CENTER, "����ʱ��");
		columns[5].setSortable(true);
		
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		CheckingOutItem item = (CheckingOutItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getPlanCheckoutDate());
		case 1:
			return item.getRelaBillsNo();
		case 2:
			return item.getType().getName();
		case 3:
			return item.getStoreName();
		case 4:
			return item.getStatus().getName();
		case 5:
			return DateUtil.dateFromat(item.getCreateDate());
		default:
			return "";
		}
	}
}