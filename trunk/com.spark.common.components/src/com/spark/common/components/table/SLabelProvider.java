package com.spark.common.components.table;

import com.jiuqi.dna.ui.wt.graphics.Color;


/**
 * 表格文本信息
 */
public interface SLabelProvider {
	/**
	 * 文本信息
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public String getText(Object element, int columnIndex);
	
	/**
	 * titile信息，可以为空
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public String getToolTipText(Object element, int columnIndex);
	
	/**
	 * 前景色
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public Color getForeground(Object element, int columnIndex);
	
	/**
	 * 背景色
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public Color getBackground(Object element, int columnIndex);
}
