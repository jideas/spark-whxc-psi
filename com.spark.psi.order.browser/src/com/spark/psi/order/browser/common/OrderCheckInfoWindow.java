package com.spark.psi.order.browser.common;

import java.util.List;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.SDelayLoadMenuWindow;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.order.entity.OrderInfo;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;

/**
 * ����������������
 * 
 */
public class OrderCheckInfoWindow extends SDelayLoadMenuWindow {

	private Browser browser;

	private OrderInfo orderInfo;

	private String checkName;
	private String orderName;

	private List<OrderCheckSheetItem> itemList;

	public OrderCheckInfoWindow(Control owner, OrderInfo orderInfo,
			List<OrderCheckSheetItem> itemList) {
		super(owner, Direction.Down, ActiveMode.Hover);
		this.itemList = itemList;
		this.orderInfo = orderInfo;
		GridLayout gl = new GridLayout();
		gl.marginLeft = gl.marginRight = gl.marginTop = gl.marginBottom = 6;
		this.getContentArea().setLayout(gl);

		Label baseInfoLabel = new Label(this.getContentArea());
		StringBuffer baseInfoBuffer = new StringBuffer();
		if (orderInfo instanceof SalesOrderInfo) {
			baseInfoBuffer.append("����");
			checkName = "����";
			orderName = "����";
		} else if (orderInfo instanceof SalesReturnInfo) {
			baseInfoBuffer.append("�����˻�");
			checkName = "���";
			orderName = "�˻�";
		} else if (orderInfo instanceof PurchaseOrderInfo) {
			baseInfoBuffer.append("�ɹ�");
			checkName = "���";
			orderName = "�ɹ�";
		} else if (orderInfo instanceof PurchaseReturnInfo) {
			baseInfoBuffer.append("�ɹ��˻�");
			checkName = "����";
			orderName = "�˻�";
		}
		baseInfoBuffer.append("���ţ�");
		baseInfoBuffer.append(orderInfo.getOrderNumber());
		baseInfoBuffer.append("   " + checkName + "�����");
		baseInfoBuffer.append(orderInfo.getOrderStatus().getName());
		baseInfoLabel.setText(baseInfoBuffer.toString());
		baseInfoLabel.setLayoutData(GridData.INS_FILL_HORIZONTAL);

		Composite composite = new Composite(this.getContentArea());
		composite.setLayout(new FillLayout());
		GridData gd = new GridData();
		gd.widthHint = 570;
		composite.setLayoutData(gd);
		browser = new Browser(composite);
		browser.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID,
				SSeparator.color.getColor()));
		// browser.setHTML("<p style='font-size:12'>���ڼ���...</p>");
	}

	@Override
	protected void updateWindowContent() {
		int tableHeight = 0;
		StringBuffer htmlBuffer = new StringBuffer();
		htmlBuffer
				.append("<body leftMargin=0 rightMargin=0 topMargin=0 bottomMargin=0 scroll=no>");
		htmlBuffer
				.append("<table style='font-size:12' cellpadding=0 cellspacing=0>");
		for (OrderCheckSheetItem sheetItem : itemList) {
			htmlBuffer.append("<tr style='height:24px;background-color:#"
					+ Color.COLOR_LIGHTCYAN.toString() + "'>");
			htmlBuffer.append("<td colspan=4>");
			htmlBuffer.append(checkName + "�ֿ⣺");
			if(CheckIsNull.isEmpty(sheetItem.getStoreName()))
			{
				htmlBuffer.append("ֱ��");
			}
			else
			{
				htmlBuffer.append(sheetItem.getStoreName());
				htmlBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				htmlBuffer.append(checkName + "���ţ�");
				htmlBuffer.append(sheetItem.getCheckedSerialNos());
				if (orderInfo instanceof SalesOrderInfo
						|| orderInfo instanceof PurchaseOrderInfo) {
					htmlBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					htmlBuffer.append("Ԥ��" + checkName + "���ڣ�");
					htmlBuffer.append(DateUtil.dateFromat(sheetItem
							.getPlayCheckDate()));
				}
			}
			htmlBuffer.append("</td>");
			htmlBuffer.append("</tr>");
			tableHeight += 24;
			for (int i = 0; i < sheetItem.getItems().length; i++) {
				OrderCheckSheetItem.Item item = sheetItem.getItems()[i];
				htmlBuffer.append("<tr style='height:24px'>");
				htmlBuffer.append("<td style='width:180px;'>" + (i + 1) + "��"
						+ item.getGoodsInfo() + "</td>");
				htmlBuffer.append("<td style='width:150px;'>" + orderName
						+ "�۸�" + DoubleUtil.getRoundStr(item.getPrice())
						+ "</td>");
				htmlBuffer.append("<td style='width:120px;'>"
						+ orderName
						+ "������"
						+ DoubleUtil.getRoundStr(item.getOrderCount(),
								item.getScale()) + "</td>");
				htmlBuffer.append("<td style='width:120px;'>"
						+ checkName
						+ "������"
						+ DoubleUtil.getRoundStr(item.getCheckedCount(),
								item.getScale()) + "</td>");
				htmlBuffer.append("</tr>");
				tableHeight += 24;
			}

			htmlBuffer.append("<tr style='height:1px;background-color:#"
					+ SSeparator.color.toString() + "'>");
			htmlBuffer.append("<td colspan=4></td>");
			htmlBuffer.append("</tr>");
			tableHeight += 1;
		}
		htmlBuffer.append("</table></body>");
		browser.applyHTML(htmlBuffer.toString());
		GridData gd = (GridData) browser.getParent().getLayoutData();
		tableHeight += 1;
		gd.heightHint = tableHeight;
		browser.getParent().setLayoutData(gd);
	}
}
