package com.spark.common.components.table;

import com.jiuqi.dna.ui.wt.graphics.Color;


/**
 * ����ı���Ϣ
 */
public interface SLabelProvider {
	/**
	 * �ı���Ϣ
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public String getText(Object element, int columnIndex);
	
	/**
	 * titile��Ϣ������Ϊ��
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public String getToolTipText(Object element, int columnIndex);
	
	/**
	 * ǰ��ɫ
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public Color getForeground(Object element, int columnIndex);
	
	/**
	 * ����ɫ
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public Color getBackground(Object element, int columnIndex);
}
