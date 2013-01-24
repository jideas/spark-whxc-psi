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
 * ����һ���������б�Ĵ�����
 */
public abstract class SimpleListPageProcessor<TItem> extends PageProcessor
		implements SEditContentProvider, SActionListener {

	final static String ID_MAIN_TABLE = "STable_MainTable";

	/**
	 * ���ؼ�����
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
	 * ��ȡ�����б�
	 */
	public abstract Object[] getElements(Context context, STableStatus tablestatus);

	/**
	 * ��ȡָ�������ID
	 */
	public abstract String getElementId(Object element);

	/**
	 * ��ȡָ���еı༭ֵ
	 */
	public String getValue(Object element, String columnName) {
		return null;
	}

	/**
	 * �����Զ�����ʱ��ȡ�µ������ж���
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
	 * ��ȡ���ԶԱ�����ݽ��е����в���
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
	 * ��ȡָ���ж�����Խ��еĲ����б�
	 * 
	 * @param element
	 * @param actionId
	 * @return
	 */
	protected String[] getElementActionIds(Object element) {
		return this.getTableActionIds();
	}

	/**
	 * ָ����������ʱ���������¼�
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
