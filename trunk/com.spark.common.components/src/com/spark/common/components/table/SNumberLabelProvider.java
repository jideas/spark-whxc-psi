package com.spark.common.components.table;

/**
 * ����ı���Ϣ
 */
public interface SNumberLabelProvider extends SLabelProvider {

	/**
	 * ����С��λ��
	 * 
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public int getDecimal(Object element, int columnIndex);
}
