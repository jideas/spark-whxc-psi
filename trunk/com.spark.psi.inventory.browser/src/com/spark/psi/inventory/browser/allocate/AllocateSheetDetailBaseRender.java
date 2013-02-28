package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;

/**
 * <p>调拔单详情基类视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-23
 */

public class AllocateSheetDetailBaseRender extends SimpleSheetPageRender{

	/**
	 * 按钮数据项
	 */
	protected static GridData buttonGridData;
	
	static {
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
			new Label(baseInfoArea).setText("调出仓库：");
			new Label(baseInfoArea).setID(AllocateSheetDetailBaseProcessor.ID_Label_Out);
			//
			new Label(baseInfoArea).setText("　　");
			//调入仓库
			new Label(baseInfoArea).setText("调入仓库：");
			new Label(baseInfoArea).setID(AllocateSheetDetailBaseProcessor.ID_Label_In);
			//
			new Label(baseInfoArea).setText("　　");
			//申请时间
			new Label(baseInfoArea).setText("申请日期：");
			new Label(baseInfoArea).setID(AllocateSheetDetailBaseProcessor.ID_Label_SubmitDate);
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
		// TODO Auto-generated method stub

	}

	/**
	 * 表格底部按钮区，默认布局为5列的DataGrid
	 */
	@Override
	protected void renderTableButtonArea(Composite tableButtonArea){
		// TODO Auto-generated method stub

	}

	/**
	 * 获得列
	 */
	@Override
	public STableColumn[] getColumns(){
		//创建列
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.code.name(), 150, JWT.LEFT, "编号");
		columns[1] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.number.name(), 100, JWT.LEFT, "条码");
		columns[2] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.name.name(), 100, JWT.LEFT, "材料名称");
		columns[3] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.spec.name(), 100, JWT.LEFT, "规格");
		columns[4] = new STableColumn(AllocateSheetDetailBaseProcessor.Columns.unit.name(), 120, JWT.CENTER, "单位");
		columns[5] = new SNumberEditColumn(AllocateSheetDetailBaseProcessor.Columns.allocateCount.name(), 150, JWT.RIGHT, "调拨数量");
		//自适应
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		((SNumberEditColumn)columns[5]).setDecimal(2);
		return columns;
	}
	
	/**
	 * 获得精度
	 */
	public int getDecimal(Object element, int columnIndex) {
		if(element instanceof AllocateShowItem){
			switch(columnIndex){
				case 5:
					return 2;
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
					return DoubleUtil.getRoundStr(item.getAllocateCount());
//				case 6:
//					return DoubleUtil.getRoundStr(item.getAllocateCount());
			}
		}
		return "";
	}
	
	/**
	 * 获取表格风格
	 */
	public STableStyle getTableStyle(){
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

}
