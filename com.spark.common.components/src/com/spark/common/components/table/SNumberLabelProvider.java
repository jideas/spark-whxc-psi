package com.spark.common.components.table;

/**
 * 表格文本信息
 */
public interface SNumberLabelProvider extends SLabelProvider {

	/**
	 * 数据小数位数
	 * 
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public int getDecimal(Object element, int columnIndex);
}
