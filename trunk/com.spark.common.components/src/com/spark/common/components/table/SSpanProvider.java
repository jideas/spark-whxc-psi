package com.spark.common.components.table;

/**
 * ���Ԫ������кϲ���Ϣ�ṩ��
 *
 */
public interface SSpanProvider {

	/**
	 * ָ����Ԫ�����з���ĺϲ�����
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public int getRowSpan(Object element, int columnIndex);
	
	
	/**
	 * ָ����Ԫ�����з���ĺϲ�����
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public int getColSpan(Object element, int columnIndex);
	
	/**
	 * ��ȡָ��Ԫ�ص�����кϲ�o
	 * @return
	 */
	public int getMaxRowSpan(Object element);
	
	public boolean isSpanAlready(Object element, int columnIndex);
	
}
