package com.spark.common.components.table.edit;


/**
 * ��ֵ�༭��
 */
public class SNumberEditColumn extends SEditColumn {

	/**
	 * ��ʾ��С��λ��
	 */
	private int decimal;

	/**
	 * �Ƿ���ʾǧ��λ
	 */
	private boolean separator;
	
	
	/**
	 * ���������ַ�����
	 */
	private int maxLength;
	
	/**
	 * �Ƿ����д����
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
	 * �����Ƿ�����
	 * @param isMinusEnable
	 */
	public void setMinusEnable(boolean isMinusEnable) {
		this.isMinusEnable = isMinusEnable;
	}
	
	
}
