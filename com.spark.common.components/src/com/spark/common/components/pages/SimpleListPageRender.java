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
 * ����һ���������б�ҳ��<br>
 * ������Ҫʵ����ط��������ṩ������ʽ������
 */
public abstract class SimpleListPageRender extends AbstractBoxPageRender
		implements SLabelProvider, SNumberLabelProvider {
	/**
	 * ���ؼ�
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
	 * �ڱ�񴴽�֮ǰִ�еĲ���
	 */
	protected abstract void beforeTableRender();

	/**
	 * �ڱ�񴴽�֮����еĲ���
	 */
	protected abstract void afterTableRender();

	/**
	 * ��ȡ�����
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
	 * ��ȡ�����
	 * 
	 * @return
	 */
	public abstract STableColumn[] getColumns();

	/**
	 * ��ȡָ���ж�����ָ���е�����
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
	 * ����actionId���ضԸò����������������
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
