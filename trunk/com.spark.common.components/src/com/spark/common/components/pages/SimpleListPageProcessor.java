package com.spark.common.components.pages;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;

/**
 * 带有一个表格基本列表的处理器
 */
public abstract class SimpleListPageProcessor<TItem> extends PageProcessor
		implements SEditContentProvider, SActionListener {

	final static String ID_MAIN_TABLE = "STable_MainTable";

	/**
	 * 表格控件对象
	 */
	protected SEditTable table;

	public void init(Situation context) {

	}

	@Override
	public void process(Situation context) {
		table = this.createControl(ID_MAIN_TABLE, SEditTable.class, JWT.NONE);
		table.setContentProvider(this);
		table.addActionListener(this);
	}

	public void postProcess(Situation context) {
		table.render();
	}

	/**
	 * 获取对象列表
	 */
	public abstract Object[] getElements(Context context, STableStatus tablestatus);

	/**
	 * 获取指定对象的ID
	 */
	public abstract String getElementId(Object element);

	/**
	 * 获取指定列的编辑值
	 */
	public String getValue(Object element, String columnName) {
		return null;
	}

	/**
	 * 处理自动增行时获取新的数据行对象
	 */
	public Object getNewElement() {
		return null;
	}

	/**
	 * 
	 */
	public SNameValue[] getExtraData(Object element) {
		return null;
	}

	/**
	 * 
	 */
	public boolean isSelectable(Object element) {
		return true;
	}

	/**
	 * 
	 */
	public boolean isSelected(Object element) {
		return false;
	}

	/**
	 * 获取可以对表格数据进行的所有操作
	 * 
	 * @return
	 */
	public String[] getTableActionIds() {
		return null;
	}

	/**
	 * 
	 */
	public final String[] getActionIds(Object element) {
		return this.getElementActionIds(element);
	}

	/**
	 * 获取指定行对象可以进行的操作列表
	 * 
	 * @param element
	 * @param actionId
	 * @return
	 */
	protected String[] getElementActionIds(Object element) {
		return this.getTableActionIds();
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {

	}

	/**
	 * 
	 * @param rowCount
	 * @return
	 */
	protected String validateRowCount(int rowCount) {
		return null;
	}

	/**
	 * 
	 * @param rowId
	 * @param columnName
	 * @return
	 */
	protected String validateCell(String rowId, String columnName) {
		return null;
	}

	/**
	 * 
	 */
	protected class TableDataValidatorImpl extends TableDataValidator {

		public TableDataValidatorImpl() {
			super(SimpleListPageProcessor.this.table);
		}

		@Override
		protected String validateRowCount(int rowCount) {
			return SimpleListPageProcessor.this.validateRowCount(rowCount);
		}

		@Override
		protected String validateCell(String rowId, String columnName) {
			return SimpleListPageProcessor.this.validateCell(rowId, columnName);
		}

	}

}
