package com.spark.psi.base.browser;

public interface PrintLabelProvider {
	/**
	 * 文本信息
	 * @param element
	 * @param columnIndex
	 * @return
	 */
	public String getText(Object element, int columnIndex);
}
