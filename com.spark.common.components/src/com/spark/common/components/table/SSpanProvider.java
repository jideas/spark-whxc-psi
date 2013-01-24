package com.spark.common.components.table;

/**
 * 表格单元格的行列合并信息提供器
 *
 */
public interface SSpanProvider {

	/**
	 * 指定单元格在行方向的合并数量
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public int getRowSpan(Object element, int columnIndex);
	
	
	/**
	 * 指定单元格在列方向的合并数量
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public int getColSpan(Object element, int columnIndex);
	
	/**
	 * 获取指定元素的最大行合并o
	 * @return
	 */
	public int getMaxRowSpan(Object element);
	
	public boolean isSpanAlready(Object element, int columnIndex);
	
}
