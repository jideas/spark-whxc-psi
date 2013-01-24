package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.bom.entity.BomItem;

public class BOM_ApprovingListRender  extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setID(BOM_SubmitingListProcessor.ID_LABEL_COUNT);
		//
		Text text = new SSearchText2(headerRightArea);
		text.setHint("������������");
		text.setID(BOM_SubmitingListProcessor.ID_TEXT_SEARCHTEXT);
	}

	@Override
	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(BOM_SubmitingListProcessor.Columns.createDate.name(), 100, JWT.CENTER, "��������");
		columns[1] = new STableColumn(BOM_SubmitingListProcessor.Columns.bomNo.name(), 100, JWT.LEFT, "BOM���");
		columns[2] = new STableColumn(BOM_SubmitingListProcessor.Columns.goodsCode.name(), 100, JWT.LEFT, "��Ʒ���");
		columns[3] = new STableColumn(BOM_SubmitingListProcessor.Columns.goodsNo.name(), 200, JWT.LEFT, "��Ʒ����");
		columns[4] = new STableColumn(BOM_SubmitingListProcessor.Columns.goodsName.name(), 100, JWT.LEFT, "��Ʒ����");
		columns[4].setGrab(true);
		columns[5] = new STableColumn(BOM_SubmitingListProcessor.Columns.goodsSpec.name(), 80, JWT.LEFT, "��Ʒ���");
		columns[6] = new STableColumn(BOM_SubmitingListProcessor.Columns.goodsUnit.name(), 100, JWT.RIGHT, "��Ʒ��λ");
		columns[7] = new STableColumn(BOM_SubmitingListProcessor.Columns.creator.name(), 100, JWT.RIGHT, "������");
		
		columns[1].setSortable(true);
        columns[2].setSortable(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		BomItem item = (BomItem) element;
		switch (columnIndex) {
		case 1:
			return item.getBomNo();
		case 0:
			return item.getCreateDate();
		case 2:
			return item.getGoodsCode();
		case 3:
			return item.getGoodsNo();
		case 4:
			return item.getGoodsName();
		case 5:
			return item.getGoodsSpec();
		case 6:
			return item.getGoodsUnit();
		case 7:
			return item.getCreator();
		}
		return null;
	}
}
