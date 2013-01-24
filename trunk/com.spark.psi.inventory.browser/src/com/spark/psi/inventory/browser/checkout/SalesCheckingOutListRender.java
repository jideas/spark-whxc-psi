package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
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
public class SalesCheckingOutListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(SalesCheckingOutListProcessor.ID_BUTTON_NEW_LYCK);
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(SalesCheckingOutListProcessor.ID_BUTTON_NEW_GIFT);
		//
		new SSearchText2(headerRightArea).setID(SalesCheckingOutListProcessor.ID_TEXT_SEARCH);
		new Label(headerLeftArea).setText("��������������");
		new Label(headerLeftArea).setID(SalesCheckingOutListProcessor.ID_LABEL_CHECKOUTGINSHEET_COUNT);
	}
	
	public STableColumn[] getColumns() {
		//���ӱ�ͷ��ô����
		
		STableColumn[] columns = new STableColumn[5];
		
		//��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(SalesCheckingOutListProcessor.Columns.PlanOutDate.name(), 100, JWT.CENTER, "Ԥ�Ƴ�������");
		columns[0].setSortable(true);
		
		columns[1] = new STableColumn(SalesCheckingOutListProcessor.Columns.RelatedNumber.name(), 200, JWT.CENTER, "��ص���");//entity�ӿ��е���ƴд����getSeetNumber
		columns[1].setGrab(true);
		columns[1].setSortable(true);

		columns[2] =new STableColumn(SalesCheckingOutListProcessor.Columns.StoreName.name(), 200, JWT.CENTER, "�ֿ�");
		columns[2].setGrab(true);
		columns[2].setSortable(true);
		
		columns[3] = new STableColumn(SalesCheckingOutListProcessor.Columns.status.name(), 200, JWT.CENTER, "����״̬");
		columns[3].setGrab(true);
		columns[3].setSortable(true);
		
		columns[4] = new STableColumn(SalesCheckingOutListProcessor.Columns.CreateDate.name(), 200, JWT.CENTER, "����ʱ��");
		columns[4].setSortable(true);
		
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
			return item.getStoreName();
		case 3:
			return item.getStatus().getName();
		case 4:
			return DateUtil.dateFromat(item.getCreateDate());
		default:
			return "";
		}
	}
}