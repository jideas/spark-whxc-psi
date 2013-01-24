package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;

/**
 * <p>新增或编辑调拔单视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-23
 */

public class EditAllocateSheetRender extends SimpleSheetPageRender{

	/**
	 * 按钮数据项
	 */
	protected static GridData buttonGridData;

	static{
		buttonGridData = new GridData();
		buttonGridData.heightHint = 28;
		buttonGridData.widthHint = 80;
	}

	/**
	 * 向指定容器填充组件(容器位于标题和表格之间)
	 * 
	 * @param baseInfoArea 容器
	 * @param row 第几行
	 * @param column　第几列(只支持两列，0：为左边部份；1：右边部份)
	 */
	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column){
		if(column == 0){ //左部区域
			//调出仓库
			new Label(baseInfoArea).setText("选择调出仓库：");
			LWComboList allocateOutStoreList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			allocateOutStoreList.setID(EditAllocateSheetProcesser.ID_ComboList_Out);
			allocateOutStoreList.setHint("请选择");
			//
			new Label(baseInfoArea).setText("　　");
			//调入仓库
			new Label(baseInfoArea).setText("选择调入仓库：");
			LWComboList allocateInStoreList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			allocateInStoreList.setID(EditAllocateSheetProcesser.ID_ComboList_In);
			allocateInStoreList.setHint("请选择");
		}
	}

	/**
	 * 表格底部区第一行，备注文本框后面的数据区
	 */
	@Override
	protected void fillDataInfoControl(Composite dataInfoArea){
		// TODO Auto-generated method stub

	}

	/**
	 * 表格底部区第二行
	 */
	@Override
	protected void fillStopCauseControl(Composite stopCauseArea){
		// TODO Auto-generated method stub

	}

	/**
	 * 标题和表格之间的布局行数
	 */
	@Override
	protected int getBaseInfoAreaRowCount(){
		return 1;
	}

	/**
	 * 页面底部右边按钮区
	 */
	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea){
		//提交申请
		Button submitButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		submitButton.setID(EditAllocateSheetProcesser.ID_Button_Submit);
		submitButton.setText("提交申请");
		submitButton.setLayoutData(buttonGridData);
		//保存
		Button saveButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		saveButton.setID(EditAllocateSheetProcesser.ID_Button_Save);
		saveButton.setText("保存");
		saveButton.setLayoutData(buttonGridData);
	}

	/**
	 * 表格底部按钮区，默认布局为5列的DataGrid，在备注之前
	 */
	@Override
	protected void renderTableButtonArea(Composite tableButtonArea){
		//添加材料
		Button addGoodsButton = new Button(tableButtonArea, JWT.APPEARANCE2);
		addGoodsButton.setID(EditAllocateSheetProcesser.ID_Button_AddGoods);
		addGoodsButton.setText("添加材料");
		addGoodsButton.setLayoutData(buttonGridData);
	}
	
	/**
	 * 获得列
	 */
	@Override
	public STableColumn[] getColumns(){
		//创建列
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(EditAllocateSheetProcesser.Columns.code.name(), 150, JWT.LEFT, "编号");
		columns[1] = new STableColumn(EditAllocateSheetProcesser.Columns.number.name(), 150, JWT.LEFT, "条码");
		columns[2] = new STableColumn(EditAllocateSheetProcesser.Columns.name.name(), 100, JWT.LEFT, "材料名称");
		columns[3] = new STableColumn(EditAllocateSheetProcesser.Columns.spec.name(), 100, JWT.LEFT, "材料规格");
		columns[4] = new STableColumn(EditAllocateSheetProcesser.Columns.unit.name(), 120, JWT.CENTER, "单位");
		columns[5] =
		        new STableColumn(EditAllocateSheetProcesser.Columns.availableCount.name(), 150, JWT.RIGHT, "可用库存");
		columns[6] =
		        new SNumberEditColumn(EditAllocateSheetProcesser.Columns.allocateCount.name(), 150, JWT.RIGHT, "调拨数量");
		//自适应
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}

	/**
	 * 获得精度
	 */
	public int getDecimal(Object element, int columnIndex) {
		if(element instanceof AllocateShowItem){
			AllocateShowItem item = (AllocateShowItem)element;
			switch(columnIndex){
				case 5:
				case 6:
					return item.getScale();
			}
		}
		return -1;
	}
	
	/**
	 * 单元格文本值
	 */
	@Override
	public String getText(Object element, int columnIndex){
		if(element instanceof AllocateShowItem){
			AllocateShowItem item = (AllocateShowItem)element;
			switch(columnIndex){
				case 0:
					return item.getStockItemCode();
				case 1:
					return item.getStockItemNumber();
				case 2:
					return item.getStockItemName();
				case 3:
					return item.getStockSpec();
				case 4:
					return item.getStockItemUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getAvailableCount());
				case 6:
					return DoubleUtil.getRoundStr(item.getAllocateCount());
			}
		}
		return "";
	}
	
	/**
	 * 获取表格风格
	 */
	public STableStyle getTableStyle(){
		return new STableStyle();
	}

}
