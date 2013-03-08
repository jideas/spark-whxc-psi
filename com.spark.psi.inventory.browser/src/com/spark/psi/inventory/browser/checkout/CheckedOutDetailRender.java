package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.inventory.browser.checkin.ExtendSimpleSheetPageRender;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfoItem;

/**
 * 入库单明细列表视图
 */
public class CheckedOutDetailRender extends ExtendSimpleSheetPageRender {

	private CheckOutBaseInfo info;

	public void init(Situation context) {
		super.init(context);
		this.info = ((CheckedOutDetailProcessor) this.processor).info;
	}

	@Override
	public STableColumn[] getColumns() {
		int columnsSize = 6;
		
		String goodsTilte  = "材料";
		if(info.getCheckoutType().equals(CheckingOutType.RealGoods.getCode())||CheckingOutType.GoodsSplit.getCode().equals(info.getCheckoutType())){
			goodsTilte  = "商品";
		}

		STableColumn[] columns = new STableColumn[columnsSize];
		columns[0] = new STableColumn(CheckedOutDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, goodsTilte+"编号");
		columns[1] = new STableColumn(CheckedOutDetailProcessor.Columns.GoodsNo.name(), 150, JWT.LEFT, goodsTilte+"条码");
		columns[2] = new STableColumn(CheckedOutDetailProcessor.Columns.GoodsName.name(), 100, JWT.LEFT, goodsTilte+"名称");
		columns[2].setGrab(true);

		columns[3] = new STableColumn(CheckedOutDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.LEFT, goodsTilte+"规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(CheckedOutDetailProcessor.Columns.GoodsUnit.name(), 120, JWT.CENTER, "单位");

		columns[5] = new STableColumn(CheckedOutDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT, "出库数量");

		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof CheckOutBaseInfoItem) {
			CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) element;
			if (CheckingOutType.RealGoods.getCode().equals(info.getCheckoutType())||CheckingOutType.GoodsSplit.getCode().equals(info.getCheckoutType())) {
				GoodsItemInfo goods = getContext().find(GoodsItemInfo.class, item.getGoodsId());
				switch (columnIndex) {
				case 0:
					return goods.getBaseInfo().getCode();
				case 1:
					return goods.getItemData().getGoodsItemNo();
				case 2:
					return goods.getBaseInfo().getName();
				case 3:
					return goods.getItemData().getSpec();
				case 4:
					return goods.getItemData().getUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getRealCount());
				}
			} else {
				MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsId());
				switch (columnIndex) {
				case 0:
					return goods.getBaseInfo().getCode();
				case 1:
					return goods.getItemData().getMaterialNo();
				case 2:
					return goods.getBaseInfo().getName();
				case 3:
					return goods.getItemData().getMaterialSpec();
				case 4:
					return goods.getItemData().getUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getRealCount());
				}
			}

		}
		return "";
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (0 == row) {
			if (0 == column) {

				Label label = new Label(baseInfoArea);
				label.setID(CheckedOutDetailProcessor.ID_Label_Customer);

				label = new Label(baseInfoArea);
				label.setID(CheckedOutDetailProcessor.ID_Label_Store);
				if (CheckingOutType.Sales.equals(info.getCheckoutType())) {
					label = new Label(baseInfoArea);
					label.setID(CheckedOutDetailProcessor.ID_Label_PlanDate);
				}
				label = new Label(baseInfoArea);
				label.setID(CheckedOutDetailProcessor.ID_Label_RelatedNumber);
			}
		}
		if (1 == row) {
			if (0 == column) {
				if (!CheckingOutType.RealGoods.getCode().equals(info.getCheckoutType())&&!CheckingOutType.GoodsSplit.getCode().equals(info.getCheckoutType())) {
					new Label(baseInfoArea).setText("提货人：");
					Label t = new Label(baseInfoArea);
					t.setID(CheckedOutDetailProcessor.ID_Text_DeliveryPerson);
					new Label(baseInfoArea).setText("    提货单位：");
					t = new Label(baseInfoArea);
					t.setID(CheckedOutDetailProcessor.ID_Text_DeliveryUnit);

					new Label(baseInfoArea).setText("    凭证号：");
					t = new Label(baseInfoArea);
					t.setID(CheckedOutDetailProcessor.ID_Text_VoucherNumber);
				}

			} else {
				addProcessing(baseInfoArea);
			}

		}

	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		sheetButtonArea.setID(CheckedOutDetailProcessor.ID_FooterRightArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
	}

}
