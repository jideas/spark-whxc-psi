/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.order.browser.util.OrderDetailRender;
import com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns;
import com.spark.psi.order.browser.util.OrderDetailProcessor.View;
import com.spark.psi.publish.order.entity.PurchaseReturnGoodsItem;

/**
 * �ɹ��˻�����ϸ��ͼ
 * 
 */
public class PurchaseReturnSheetDetailRender extends OrderDetailRender {

	@Override
	protected Cloumns[] getColumnsEnumList() {
		return new Cloumns[] { Cloumns.GoodsItemCode,Cloumns.GoodsNo, Cloumns.GoodsName,
				Cloumns.GoodsProperties, Cloumns.GoodsUnit,
				Cloumns.ReturnCount, Cloumns.ReturnPrice, Cloumns.ReturnAmount };
	}

	/**
	 * 
	 */
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 5:
			PurchaseReturnGoodsItem item = (PurchaseReturnGoodsItem) element;
			return item.getScale();
		case 6:
			return 2;
		case 7:
			return 2;
		}
		return -1;
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0 && column == 0) {
			if(viewEnum == View.Look) {
				baseInfoArea.setID(PurchaseReturnSheetDetailProcessor.ID_SupplierInfo_Area);
			} else {
				baseInfoArea.setLayout(new GridLayout(2));
				//��Ӧ����Ϣ����ϵ����Ϣ����
				new Composite(baseInfoArea).setID(PurchaseReturnSheetDetailProcessor.ID_SupplierInfo_Area);
				Label selectButton = new Label(baseInfoArea);
				selectButton.setID(PurchaseReturnSheetDetailProcessor.ID_SupplierSelect_Button);
				selectButton.setText("  ����ѡ��Ӧ��");
				selectButton.setCursor(Cursor.HAND);
				selectButton.setForeground(Color.COLOR_BLUE);	
			}
		} else if (row == 1 && column == 0) {
			//�ջ���ַ����
			baseInfoArea.setID(PurchaseReturnSheetDetailProcessor.ID_DeliveryInfo_Area);
		} else if (row == 1 && column == 1) {
			if (viewEnum != View.Look) {
				//�˻��ֿ�����
				baseInfoArea.setLayout(new GridLayout(2));
				new Label(baseInfoArea).setText("   �˻��ֿ⣺");
				new LWComboList(baseInfoArea, JWT.APPEARANCE3)
						.setID(PurchaseReturnSheetDetailProcessor.ID_StoreList);
			} else {
				// ״̬������¼
				if (View.Look == viewEnum) {
					baseInfoArea.setLayout(new GridLayout(2));
					Label label = new Label(baseInfoArea);
					label.setID(PurchaseReturnSheetDetailProcessor.ID_OrderStatusLabel);
					label  = new Label(baseInfoArea);
					label.setID(PurchaseReturnSheetDetailProcessor.ID_CheckInfoLabel);
					label.setCursor(Cursor.HAND);
					label.setForeground(new Color(0x77A3DD));
				}	
			}
		}
	}

	@Override
	protected String getOrderTotalAmountTitle() {
		return "�˻��ܶ�";
	}
	
}
