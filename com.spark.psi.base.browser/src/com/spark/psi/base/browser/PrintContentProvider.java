package com.spark.psi.base.browser;

public interface PrintContentProvider {
	
	/**
	 * ȡ����ʾ�ݵ�HTML
	 * @return
	 */
	public String getContentHtml();
	
	/**
	 * ȡ����ʾ���ݵ��ܸ߶�
	 * @return
	 */
	public int getHeight();
	
	/**
	 * ȡ����ʾ���ݵĿ��
	 * @return
	 */
	public int getWidth();
	
}
