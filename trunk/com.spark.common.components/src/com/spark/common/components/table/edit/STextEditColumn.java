package com.spark.common.components.table.edit;

/**
 * ��ͨ�ı��༭��
 */
public class STextEditColumn extends SEditColumn {

	/**
	 * ���������ַ����ȣ�����������Ӣ�ģ�
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
