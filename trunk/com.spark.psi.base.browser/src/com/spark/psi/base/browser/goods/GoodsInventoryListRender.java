package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * ָ����Ʒ�Ŀ������б���ͼ
 */
public class GoodsInventoryListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("���/���룺");
		new Label(headerLeftArea).setID(GoodsInventoryListProcessor.ID_Label_Number);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("��Ʒ���ƣ�");
		new Label(headerLeftArea).setID(GoodsInventoryListProcessor.ID_Label_Name);
		new Label(headerRightArea).setText("��ֵ���Ʒ�Χ��");
		new ComboList(headerRightArea).setID(GoodsInventoryListProcessor.ID_List_Scope);
		new Label(headerRightArea).setText("  ");
		new Label(headerRightArea).setText("�������ͣ�");
		new ComboList(headerRightArea).setID(GoodsInventoryListProcessor.ID_List_Type);
		
		Button saveButton = new Button(footerRightArea);
		saveButton.setID(GoodsInventoryListProcessor.ID_Button_Save);
		saveButton.setText("���淧ֵ");
	}
	
	@Override
	protected void beforeTableRender() {
		super.beforeTableRender();
		GridData h_gd = new GridData(GridData.FILL_HORIZONTAL);
		h_gd.heightHint = 24;
		
		Composite beforeTabelCmp = new Composite(contentArea);
		beforeTabelCmp.setLayout(new GridLayout(8));
		beforeTabelCmp.setLayoutData(h_gd);
		
		new Label(beforeTabelCmp).setText("��Ʒ���ԣ�");
		new Label(beforeTabelCmp).setID(GoodsInventoryListProcessor.ID_Label_Property);
		new Label(beforeTabelCmp).setText("    ");
		new Label(beforeTabelCmp).setText("��λ��");
		new Label(beforeTabelCmp).setID(GoodsInventoryListProcessor.ID_Label_Unit);
		new Label(beforeTabelCmp).setText("    ");
		new Label(beforeTabelCmp).setText("״̬��");
		new Label(beforeTabelCmp).setID(GoodsInventoryListProcessor.ID_Label_status);
	}
	
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn("salesmanName", 150, JWT.LEFT, "�ֿ�����");
		columns[1] = new STableColumn("departmentInfo", 200, JWT.LEFT, "���������");
		columns[2] = new STextEditColumn("customerCreditLimit", 100, JWT.LEFT, "�ɹ��������");
		columns[3] = new STextEditColumn("availableTotalCreditLimit", 150, JWT.CENTER, "�ɹ�������");
		columns[4] = new STableColumn("customerCountUsed", 180, JWT.CENTER, "��������");

		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		InventoryInfo item = (InventoryInfo) element;
		switch (columnIndex) {
		case 0:
			// TODO ȡ�ֿ�����
//			return item.getStoreId();
		case 1:
			return String.valueOf(item.getUpperLimitAmount());
		case 2:
			return String.valueOf(item.getCount());
		case 3:
			return String.valueOf(item.getPurchasingCount());
		case 4:
			return String.valueOf(item.getDeliveryingCount());
		default:
			return "";
		}
	}
}
