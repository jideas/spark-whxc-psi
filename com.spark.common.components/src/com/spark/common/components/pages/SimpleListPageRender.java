package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;

/**
 * 带有一个表格基本列表页面<br>
 * 子类需要实现相关方法，已提供表格的样式和数据
 */
public abstract class SimpleListPageRender extends AbstractBoxPageRender
		implements SLabelProvider, SNumberLabelProvider {
	/**
	 * 表格控件
	 */
	protected SEditTable table;

	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	protected final void beforeFooterRender() {
		beforeTableRender();
		//
		String[] actionIds = ((SimpleListPageProcessor) processor)
				.getTableActionIds();
		SActionInfo[] actions = null;
		if (actionIds != null) {
			actions = new SActionInfo[actionIds.length];
			for (int i = 0; i < actionIds.length; i++) {
				actions[i] = this.getActionInfo(actionIds[i]);
			}
		}
		//
		table = new SEditTable(contentArea, getColumns(), getTableStyle(),
				actions);
		table.setID(SimpleListPageProcessor.ID_MAIN_TABLE);
		table.setLabelProvider(this);
		table.setLayoutData(GridData.INS_FILL_BOTH);
		//
		afterTableRender();
	}

	/**
	 * 在表格创建之前执行的操作
	 */
	protected abstract void beforeTableRender();

	/**
	 * 在表格创建之后进行的操作
	 */
	protected abstract void afterTableRender();

	/**
	 * 获取表格风格
	 * 
	 * @return
	 */
	public STableStyle getTableStyle() {
		return new SEditTableStyle();
	}

	/**
	 * 
	 * @return
	 */
	public final SEditTable getTable() {
		return this.table;
	}

	/**
	 * 获取表格列
	 * 
	 * @return
	 */
	public abstract STableColumn[] getColumns();

	/**
	 * 获取指定行对象在指定列的数据
	 */
	public abstract String getText(Object element, int columnIndex);

	/**
	 *  
	 */
	public String getToolTipText(Object element, int columnIndex) {
		return null;
	}

	public Color getBackground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getForeground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据actionId返回对该操作的外观描述对象
	 * 
	 * @param actionId
	 * @return
	 */
	public SActionInfo getActionInfo(String actionId) {
		return new SActionInfo(actionId, actionId, null, null);
	}

	/**
	 * 
	 */
	public int getDecimal(Object element, int columnIndex) {
		return -1;
	}

}
