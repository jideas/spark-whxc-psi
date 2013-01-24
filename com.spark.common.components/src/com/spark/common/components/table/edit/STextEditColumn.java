package com.spark.common.components.table.edit;

/**
 * 普通文本编辑列
 */
public class STextEditColumn extends SEditColumn {

	/**
	 * 最大可输入字符长度（不区分中文英文）
	 */
	private int maxLength;
	
	/**
	 * 
	 * @param name
	 * @param width
	 * @param align
	 * @param titles
	 */
	public STextEditColumn(String name, int width, int align, String... titles) {
		super(name, width, align, titles);
	}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	

}
