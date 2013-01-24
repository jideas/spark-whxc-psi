package com.spark.common.components.table.edit;


/**
 * 数值编辑列
 */
public class SNumberEditColumn extends SEditColumn {

	/**
	 * 显示的小数位数
	 */
	private int decimal;

	/**
	 * 是否显示千分位
	 */
	private boolean separator;
	
	
	/**
	 * 最大可输入字符长度
	 */
	private int maxLength;
	
	/**
	 * 是否可以写负数
	 */
	private boolean isMinusEnable;

	/**
	 * 
	 * @param name
	 * @param width
	 * @param align
	 * @param titles
	 */
	public SNumberEditColumn(String name, int width, int align, String... titles) {
		super(name, width, align, titles);
	}

	/**
	 * 
	 * @return
	 */
	public int getDecimal() {
		return decimal;
	}

	/**
	 * 
	 * @param decimal
	 */
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSeparator() {
		return separator;
	}

	/**
	 * 
	 * @param separator
	 */
	public void setSeparator(boolean separator) {
		this.separator = separator;
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

	public boolean isMinusEnable() {
		return isMinusEnable;
	}
	
	/**
	 * 设置是否可填负数
	 * @param isMinusEnable
	 */
	public void setMinusEnable(boolean isMinusEnable) {
		this.isMinusEnable = isMinusEnable;
	}
	
	
}
