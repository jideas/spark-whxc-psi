package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Goods;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;

public class NewGoodsSplitDetailRender extends AbstractGoodsSplitOrderRender {

	@Override
	protected void renderTable(Composite tableArea) {
		SEditTable goodsTable = null;
		SEditTable materialTable = null;
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		// if (ProduceOrderStatus.Producing.equals(orderInfo.getStatus())) {
		goodsTable = new SEditTable(tableArea, getEditableGoodsTableColumns(),
				tableStyle, null);
		goodsTable.setLabelProvider(new EditableGoodsTableLabelProvider());
		materialTable = new SEditTable(tableArea,
				getEditableMaterialTableColumns(), tableStyle, null);
		// materialTable.setLabelProvider(new
		// EditableMaterialTableLabelProvider());
		// } else if (ProduceOrderStatus.Finished.equals(orderInfo.getStatus()))
		// {
		// goodsTable = new SEditTable(tableArea, getGoodsTableColumns(),
		// tableStyle, null);
		// goodsTable.setLabelProvider(new GoodsTableLabelProvider());
		// materialTable = new SEditTable(tableArea,
		// getEditableMaterialTableColumns(), tableStyle, null);
		// materialTable.setLabelProvider(new
		// EditableMaterialTableLabelProvider());
		// } else {
		// goodsTable = new SEditTable(tableArea, getGoodsTableColumns(),
		// tableStyle, null);
		// goodsTable.setLabelProvider(new GoodsTableLabelProvider());
		// materialTable = new SEditTable(tableArea, getMaterialTableColumns(),
		// tableStyle, null);
		// materialTable.setLabelProvider(new MaterialTableLabelProvider());
		// }

		goodsTable.setID(NewGoodsSplitDetailProcessor.ID_Table_Goods);
		materialTable.setID(NewGoodsSplitDetailProcessor.ID_Table_Material);

		goodsTable.setLayoutData(GridData.INS_FILL_BOTH);
		materialTable.setLayoutData(GridData.INS_FILL_BOTH);
	}

	@Override
	protected void fillFooter() {
		GridData gd = new GridData();
		gd.widthHint = 150;
		Label lb = new Label(footerLeftArea);
		lb.setID(NewGoodsSplitDetailProcessor.ID_Label_Info);
		lb.setLayoutData(gd);

		Button button = null;
//		button = new Button(footerLeftArea,JWT.APPEARANCE2);
//		button.setText("�����Ʒ");
//		button.setID(NewGoodsSplitDetailProcessor.ID_Button_AddGoods);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  �ύ  ");
		button.setID(NewGoodsSplitDetailProcessor.ID_Button_Submit);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  ����  ");
		button.setID(NewGoodsSplitDetailProcessor.ID_Button_Save);

	}

	private STableColumn[] getGoodsTableColumns() {
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.goodsName.name(),
				200, JWT.LEFT, "��Ʒ����");
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.spec.name(), 140,
				JWT.LEFT, "���");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.count.name(), 140,
				JWT.RIGHT, "����");
		columns[0].setGrab(true);
		return columns;
	}

	private STableColumn[] getMaterialTableColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.materialName
						.name(), 200, JWT.LEFT, "��������");
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.spec.name(),
				140, JWT.RIGHT, "���");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.scount
						.name(), 100, JWT.CENTER, "�ο�����");
		columns[3] = new SNumberEditColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.count
						.name(), 100, JWT.CENTER, "����");
		columns[0].setGrab(true);
		return columns;
	}

	private STableColumn[] getEditableGoodsTableColumns() {
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.goodsName.name(),
				150, JWT.LEFT, "��Ʒ����");
		columns[0].setGrab(true);
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.spec.name(), 90,
				JWT.LEFT, "���");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.count.name(), 90,
				JWT.RIGHT, "����");
		return columns;
	}

	private STableColumn[] getEditableMaterialTableColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.materialName
						.name(), 150, JWT.LEFT, "��������");
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.spec.name(),
				90, JWT.RIGHT, "���");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.scount.name(),
				90, JWT.RIGHT, "�ο�����");
		columns[3] = new SNumberEditColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.count.name(),
				90, JWT.RIGHT, "����");
		columns[0].setGrab(true);
		return columns;
	}

	private class GoodsTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			GoodsSplitDet_Goods item = (GoodsSplitDet_Goods) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsName();
			case 1:
				return item.getGoodsSpec();
			case 2:
				return DoubleUtil.getRoundStr(item.getGcount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			if (2 == columnIndex)
				return 2;
			return 0;
		}
	}

	private class MaterialTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			GoodsSplitDet_Material item = (GoodsSplitDet_Material) element;
			switch (columnIndex) {
			case 0:
				return item.getMname();
			case 1:
				return item.getMspec();
			case 2:
				return DoubleUtil.getRoundStr(item.getScount());
			case 3:
				return DoubleUtil.getRoundStr(item.getMcount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}

	}

	private class EditableGoodsTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			GoodsSplitDet_Goods item = (GoodsSplitDet_Goods) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsName();
			case 1:
				return item.getGoodsSpec();
			case 2:
				return DoubleUtil.getRoundStr(item.getGcount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}

	}

	private class EditableMaterialTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			GoodsSplitDet_Material item = (GoodsSplitDet_Material) element;
			switch (columnIndex) {
			case 0:
				return item.getMname();
			case 1:
				return item.getMspec();
			case 2:
				return DoubleUtil.getRoundStr(item.getScount());
			case 3:
				return DoubleUtil.getRoundStr(item.getMcount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}

	}
}
