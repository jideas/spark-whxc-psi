/**
 * 
 */
package com.spark.psi.order.browser.distribute;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.order.browser.internal.OrderImages;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.order.entity.SalesDistributeOrderItem;
import com.spark.psi.publish.order.key.GetSalesDistributeOrderListKey;

/**
 * ��������б���ͼ
 * 
 */
public class DistributingSalesOrderListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();

		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea)
				.setID(DistributingSalesOrderListProcessor.ID_LABEL_COUNT);

		new SSearchText2(headerRightArea)
				.setID(DistributingSalesOrderListProcessor.ID_TEXT_SEARCHTEXT);

		// new
		// Button(headerRightArea).setID(DistributingSalesOrderListProcessor.ID_BUTTON_SEARCHBUTTON);
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(GetSalesDistributeOrderListKey.SortField.DeliveryDate.name(), 200, JWT.CENTER, "��������");
		columns[0].setSortable(true);
		columns[1] = new STableColumn(GetSalesDistributeOrderListKey.SortField.OrderNumber.name(), 200, JWT.LEFT, "�������");
		columns[1].setSortable(true);
		columns[2] = new STableColumn(GetSalesDistributeOrderListKey.SortField.PartnerShortName.name(), 300, JWT.LEFT, "�ͻ�����");
		columns[2].setGrab(true);
		columns[2].setSortable(true);
		columns[3] = new STableColumn(GetSalesDistributeOrderListKey.SortField.Address.name(), 150, JWT.LEFT, "�ջ���ַ");
		columns[3].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		SalesDistributeOrderItem item = (SalesDistributeOrderItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getDeliveryDate());
		case 1:
			if (item.getActions().length == 0) {
				ImageDescriptor image = OrderImages
						.getImage("images/saas_mark_processing.png");
				return StableUtil.toImg(image.getDNAURI(), "���ڴ���",
						image.getWidth() - 1)
						+ item.getOrderNumber();
			}
			return StableUtil.toLink(
					Action.Allocate.name(),
					"",
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ item.getOrderNumber());
		case 2:
			return item.getCustomerName();
		case 3:
			return item.getAddress();
		default:
			return "";
		}
	}
}
