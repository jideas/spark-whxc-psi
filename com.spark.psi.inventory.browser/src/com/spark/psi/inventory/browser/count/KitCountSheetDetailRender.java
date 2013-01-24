package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SFormula;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;

/**
 * 其他物品库存盘点单明细界面视图1
 * 
 */
public class KitCountSheetDetailRender extends SimpleSheetPageRender {
	
	private InventoryCountSheetInfo info;
	
	public void init(Situation context) {
		super.init(context);
		if(this.getArgument() != null) {
			info = (InventoryCountSheetInfo) this.getArgument();
		}
	}
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(KitCountSheetDetailProcessor.Columns.GoodsName.name(), 200,JWT.LEFT, "物品名称");
		columns[1] = new STableColumn(KitCountSheetDetailProcessor.Columns.GoodsProperties.name(),100, JWT.LEFT, "物品描述");
		columns[2] = new STableColumn(KitCountSheetDetailProcessor.Columns.GoodsUnit.name(), 100,JWT.CENTER, "单位");
		//财面数量 carryCount
		columns[3] = new STableColumn(KitCountSheetDetailProcessor.Columns.Count.name(), 100,JWT.RIGHT, "账面数量");
		//实盘数量 realCount
		columns[4] = new SNumberEditColumn(KitCountSheetDetailProcessor.Columns.AcutalCount.name(), 100,JWT.RIGHT, "实盘数量");//edit
		columns[5] = new STableColumn(KitCountSheetDetailProcessor.Columns.BalanceCount.name(),150, JWT.RIGHT, "差额");
		columns[6] = new STextEditColumn(KitCountSheetDetailProcessor.Columns.Memo.name(), 150,JWT.LEFT, "说明");//edit		
		//计算公式
		((SNumberEditColumn)columns[4]).setFormulas(new SFormula[] { 
				new SAsignFormula("[" + KitCountSheetDetailProcessor.Columns.AcutalCount.name()+ "]" 
				+ "-"
				+ "[#"+KitCountSheetDetailProcessor.Columns.Count.name()+"]", KitCountSheetDetailProcessor.Columns.BalanceCount.name())
		});		
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[6].setGrab(true);
		
		columns[0].setSearch(true);
		columns[1].setSearch(true);
		
		return columns;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if(row == 0) {
			if(column == 0) {
				new Label(baseInfoArea).setText("盘点仓库：");
				Label label = new Label(baseInfoArea);
				label.setID(KitCountSheetDetailProcessor.ID_Label_Store);
				Composite cmp = new Composite(baseInfoArea);
				GridData gdCmp = new GridData(GridData.FILL_VERTICAL);
				gdCmp.widthHint = 10;
				cmp.setLayoutData(gdCmp);
				cmp.setID(KitCountSheetDetailProcessor.ID_Composite_Export);
			} else if(column == 1) {
//				new Text(baseInfoArea, JWT.BUTTON | JWT.APPEARANCE3).setID(KitCountSheetDetailProcessor.ID_TEXT_SEARCH);
//				new SSearchText2(baseInfoArea).setID(KitCountSheetDetailProcessor.ID_TEXT_SEARCH);
			}
		}
	}
	
	@Override
	protected boolean isShowFind() {
		return true;
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		if(info == null || InventoryCountStatus.Counting.equals(info.getSheetstatus()))
		{
			Button finishButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
			finishButton.setText(" 结束盘点 ");
			finishButton.setID(KitCountSheetDetailProcessor.ID_Button_Finish);
			Button saveButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
			saveButton.setText(" 保存  ");
			saveButton.setID(KitCountSheetDetailProcessor.ID_Button_Save);
		}
		Button printButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		printButton.setID(GoodsCountSheetDetailProcessor.ID_Button_Print);
		printButton.setText("   打印   ");
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		//盘点未结束才允许添加材料
		if(!InventoryCountStatus.Counted.equals(info.getSheetstatus()))
		{
			Button btn = new Button(tableButtonArea,JWT.APPEARANCE2);
			btn.setText("添加物品");
			btn.setID(KitCountSheetDetailProcessor.ID_Button_AddKit);		
			Button btn2  = new Button(tableButtonArea,JWT.APPEARANCE2);
			btn2.setText("  导出  ");
			btn2.setID(KitCountSheetDetailProcessor.ID_Button_Export);
		}
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if(element instanceof Kit)
		{
			Kit item = (Kit) element;
			switch (columnIndex) {
			case 0:
				return item.getKitName();
			case 1:
				return item.getKitDesc();
			case 2:
				return item.getKitUnit();
			case 3:
				return DoubleUtil.getRoundStr(item.getCount());
			case 4:
				return DoubleUtil.getRoundStr(item.getActualCount());
			case 5:
				return DoubleUtil.getRoundStr(item.getActualCount()-item.getCount());
			case 6:
				return item.getRemark();
			}
		}
		return "";
	}
	
	@Override
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
			case 3:
				return 0;
			case 4:
				return 0;
			case 5:
				return 0;
			default:
				return -1;
		} 
	}
}