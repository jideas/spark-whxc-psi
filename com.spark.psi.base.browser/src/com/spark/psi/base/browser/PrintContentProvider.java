package com.spark.psi.base.browser;

public interface PrintContentProvider {
	
	/**
	 * 取得显示容的HTML
	 * @return
	 */
	public String getContentHtml();
	
	/**
	 * 取得显示内容的总高度
	 * @return
	 */
	public int getHeight();
	
	/**
	 * 取得显示内容的宽度
	 * @return
	 */
	public int getWidth();
	
}
