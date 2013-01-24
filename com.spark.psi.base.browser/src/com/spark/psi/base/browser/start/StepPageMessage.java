package com.spark.psi.base.browser.start;

import com.spark.common.components.pages.PageControllerInstance;

/**
 * <p>��ҳ����Ϣ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-27
 */

public class StepPageMessage{

	/**���ڱ���*/
	private String pageTitle;

	/**ҳ�����ʵ��*/
	private PageControllerInstance pageControllerInstance;

	/** 
	 *���췽��
	 */
	public StepPageMessage(){
		super();
	}

	/** 
	 *���췽��
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
