package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * ������Ȩ��Ʒ�б���ͼ
 */
public class B2BGoodsListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();

		new Label(headerLeftArea).setText("��Ȩ��Ʒ������");
		new Label(headerLeftArea).setID(B2BGoodsListProcessor.ID_LABEL_B2BCOUNT);
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("δ������Ʒ������");
		new Label(headerLeftArea).setID(B2BGoodsListProcessor.ID_LABEL_UNB2BCOUNT);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[8];

		columns[0] = new STableColumn("", 200, JWT.CENTER, "��Ȩ��Ӧ��");
		columns[1] = new STableColumn("", 150, JWT.LEFT, "��Ʒ����");
		columns[2] = new STableColumn("", 150, JWT.LEFT, "��Ʒ����");
		columns[3] = new STableColumn("", 150, JWT.CENTER, "�۸�");
		columns[4] = new STableColumn("", 150, JWT.CENTER, "���&frasl; ����","������Ʒ");// ������Ʒ
		columns[5] = new STableColumn("", 200, JWT.CENTER, "��Ʒ����","������Ʒ");
		columns[6] = new STableColumn("", 150, JWT.LEFT, "��Ʒ����","������Ʒ");
		columns[7] = new STableColumn("", 200, JWT.CENTER, "��λ","������Ʒ");
		
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		// AuthorizedGoodsItemItem item = (AuthorizedGoodsItemItem) element;
		switch (columnIndex) {
		case 0:
			return "";
		default:
			return "";
		}
	}
}
