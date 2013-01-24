package com.spark.psi.inventory.browser.init;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SFormula;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.InitInventoryItem;

/**
 * ��ʼ���ֿ���Ͽ�������ͼ
 * 
 */
public class InitStoreGoodsListRender extends PSIListPageRender{

	protected void afterFooterRender(){
		super.afterFooterRender();
		new Label(headerLeftArea).setText("�ֿ����ƣ�");
		new Label(headerLeftArea).setID(InitStoreGoodsListProcessor.ID_Label_StoreName);
		new Label(headerLeftArea).setText("   ����� ");
		Label label = new Label(headerLeftArea);
		label.setID(InitStoreGoodsListProcessor.ID_Label_GoodsItemCount);
		label.setForeground(Color.COLOR_RED);
		label.setText("0");
		new Label(headerLeftArea).setText(" �ֲ���");

		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" ��Ӳ��� ");
		button.setID(InitStoreGoodsListProcessor.ID_Button_AddGoodsItem);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  �� ��  ");
		button.setID(InitStoreGoodsListProcessor.ID_Button_Save);
		new Label(footerRightArea).setText(" ");
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" ���òֿ� ");
		button.setID(InitStoreGoodsListProcessor.ID_Button_Active);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setID(InitStoreGoodsListProcessor.ID_Button_Import);
		button.setText(" ���벢���� ");
	}

	@Override
	public STableColumn[] getColumns(){
		STableColumn[] columns = new STableColumn[9];
		columns[0] = new STableColumn(InitStoreGoodsListProcessor.Columns.GoodsItemCode.name(), 150, JWT.LEFT, "���");
		columns[1] = new STableColumn(InitStoreGoodsListProcessor.Columns.GoodsItemNumber.name(), 150, JWT.LEFT, "����");
		columns[2] = new STableColumn(InitStoreGoodsListProcessor.Columns.GoodsItemName.name(), 120, JWT.LEFT, "����");
		columns[3] = new STableColumn(InitStoreGoodsListProcessor.Columns.GoodsItemSpec.name(), 150, JWT.CENTER, "���");
		columns[4] = new STableColumn(InitStoreGoodsListProcessor.Columns.GoodsItemUnit.name(), 70, JWT.CENTER, "��λ");
		columns[5] = new STableColumn(InitStoreGoodsListProcessor.Columns.shelfLife.name(), 70, JWT.CENTER, "������");
		columns[6] = new SNumberEditColumn(InitStoreGoodsListProcessor.Columns.Count.name(), 150, JWT.RIGHT, "��ʼ����");
		columns[7] = new SNumberEditColumn(InitStoreGoodsListProcessor.Columns.Cost.name(), 150, JWT.RIGHT, "ƽ�����ɱ�");
		columns[8] = new SNumberEditColumn(InitStoreGoodsListProcessor.Columns.Amount.name(), 150, JWT.RIGHT, "�����");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[4].setGrab(true);
		columns[5].setGrab(true);
		columns[6].setGrab(true);
		((SNumberEditColumn)columns[6]).setDecimal(2); // Ӧ���Ǻ;�������й�
		((SNumberEditColumn)columns[7]).setDecimal(2);
		((SNumberEditColumn)columns[8]).setDecimal(2);
		//
		String $count = "[" + InitStoreGoodsListProcessor.Columns.Count.name() + "]";
		String $cost = "[" + InitStoreGoodsListProcessor.Columns.Cost.name() + "]";
		String $amount = "[" + InitStoreGoodsListProcessor.Columns.Amount.name() + "]";

		((SNumberEditColumn)columns[6]).setFormulas(new SFormula[] {new SAsignFormula($count + "*" + $cost,
		        InitStoreGoodsListProcessor.Columns.Amount.name())});
		((SNumberEditColumn)columns[7]).setFormulas(new SFormula[] {new SAsignFormula($count + "*" + $cost,
		        InitStoreGoodsListProcessor.Columns.Amount.name())});
		((SNumberEditColumn)columns[8]).setFormulas(new SFormula[] {new SAsignFormula($amount + "/" + $count,
		        InitStoreGoodsListProcessor.Columns.Cost.name())});
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex){
		if(element instanceof InitInventoryItem){
			InitInventoryItem storeInitGoodsItem = (InitInventoryItem)element;
			switch(columnIndex){
				case 0:
					return storeInitGoodsItem.getsCode();
				case 1:
					return storeInitGoodsItem.getStockNo();
				case 2:
					return storeInitGoodsItem.getName();
				case 3:
					return storeInitGoodsItem.getSpec();
				case 4:
					return storeInitGoodsItem.getUnit();
				case 5:
					return storeInitGoodsItem.getShelfLife() + "��";
				case 6:
					return DoubleUtil.getRoundStr(storeInitGoodsItem.getCount());
				case 7:
					return DoubleUtil.getRoundStr(storeInitGoodsItem.getAverageCost());
				case 8:
					return DoubleUtil.getRoundStr(storeInitGoodsItem.getAmount());
			}
		}
		else if(element instanceof MaterialsItemInfo){
			MaterialsItemInfo item = (MaterialsItemInfo)element;
			switch(columnIndex){
				case 0:
					return item.getBaseInfo().getCode();
				case 1:
					return item.getItemData().getMaterialNo();
				case 2:
					return item.getBaseInfo().getName();
				case 3:
					return item.getItemData().getMaterialSpec();
				case 4:
					return item.getItemData().getUnit();
				case 5:
					return item.getBaseInfo().getShelfLife() + "";
				case 6:
					return "0.00";
				case 7:
					return "0.00";
				case 8:
					return "0.00";
			}
		}
		return "";
	}

	public int getDecimal(Object element, int columnIndex){
		if(element instanceof InitInventoryItem){
			switch(columnIndex){
				case 6:
					return 2; // ������ȷ������С��λ��
				case 7:
					return 2;
				case 8:
					return 2;
			}
		}
		else if(element instanceof MaterialsItemInfo){
			switch(columnIndex){
				case 6:
					return 2;
				case 7:
					return 2;
				case 8:
					return 2;
			}
		}
		return super.getDecimal(element, columnIndex);
	}
	
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
