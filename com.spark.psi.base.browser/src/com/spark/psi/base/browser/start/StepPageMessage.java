package com.spark.psi.base.browser.start;

import com.spark.common.components.pages.PageControllerInstance;

/**
 * <p>打开页面消息对像</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-27
 */

public class StepPageMessage{

	/**窗口标题*/
	private String pageTitle;

	/**页面控制实例*/
	private PageControllerInstance pageControllerInstance;

	/** 
	 *构造方法
	 */
	public StepPageMessage(){
		super();
	}

	/** 
	 *构造方法
	 *@param pageTitle
	 *@param pageName
	 */
	public StepPageMessage(String pageTitle, PageControllerInstance pageControllerInstance){
		super();
		this.pageTitle = pageTitle;
		this.pageControllerInstance = pageControllerInstance;
	}

	public String getPageTitle(){
		return pageTitle;
	}

	public void setPageTitle(String pageTitle){
		this.pageTitle = pageTitle;
	}

	public PageControllerInstance getPageControllerInstance(){
		return pageControllerInstance;
	}

	public void setPageControllerInstance(PageControllerInstance pageControllerInstance){
		this.pageControllerInstance = pageControllerInstance;
	}

}
