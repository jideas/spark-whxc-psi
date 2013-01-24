package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.JWTException;
import com.jiuqi.dna.ui.wt.widgets.Page;

/**
 * 
 */
public final class PageControllerInstance {
	
	/**
	 * 
	 */
	private Page instancePage;

	/**
	 * 
	 */
	private PageController pageController;

	/**
	 * 
	 */
	private Object argument;

	/**
	 * 
	 */
	private Object argument2;

	/**
	 * 
	 */
	private Object argument3;

	/**
	 * null
	 */
	private Object argument4;

	/**
	 * 
	 * @param saasPageName
	 */
	public PageControllerInstance(String saasPageName) {
		this(saasPageName, null);
	}

	/**
	 * 
	 * @param saasPageName
	 * @param argument
	 */
	public PageControllerInstance(String saasPageName, Object argument) {
		this(saasPageName, argument, null);
	}

	/**
	 * 
	 * @param saasPageName
	 * @param argument
	 * @param argument2
	 */
	public PageControllerInstance(String saasPageName, Object argument,
			Object argument2) {
		this(saasPageName, argument, argument2, null, null);
	}

	/**
	 * 
	 * @param saasPageName
	 * @param argument
	 * @param argument2
	 * @param argument3
	 */
	public PageControllerInstance(String saasPageName, Object argument,
			Object argument2, Object argument3) {
		this(saasPageName, argument, argument2, argument3, null);
	}

	/**
	 * 
	 * @param saasPageName
	 * @param argument
	 */
	public PageControllerInstance(String saasPageName, Object argument,
			Object argument2, Object argument3, Object argument4) {
		SaaSPageInfo pageInfo = SaaSPageGather.getPageInfo(saasPageName);
		if (pageInfo == null) {
			throw new JWTException("找不到指定的SaaS页面名称");
		}
		this.pageController = pageInfo.getController();
		this.argument = argument;
		this.argument2 = argument2;
		this.argument3 = argument3;
		this.argument4 = argument4;
	}

	/**
	 * 
	 * @param pageController
	 */
	public PageControllerInstance(PageController pageController) {
		this(pageController, null);
	}

	/**
	 * @param pageController
	 * @param argment
	 */
	public PageControllerInstance(PageController pageController, Object argument) {
		this(pageController, argument, null, null, null);
	}

	/**
	 * 
	 * @param pageController
	 * @param argument
	 * @param argument2
	 */
	public PageControllerInstance(PageController pageController,
			Object argument, Object argument2) {
		this(pageController, argument, argument2, null, null);
	}

	/**
	 * 
	 * @param pageController
	 * @param argument
	 * @param argument2
	 * @param argument3
	 */
	public PageControllerInstance(PageController pageController,
			Object argument, Object argument2, Object argument3) {
		this(pageController, argument, argument2, argument3, null);
	}

	/**
	 * 
	 * @param pageController
	 * @param argument
	 * @param argument2
	 * @param argument3
	 * @param argument4
	 */
	public PageControllerInstance(PageController pageController,
			Object argument, Object argument2, Object argument3,
			Object argument4) {
		super();
		this.pageController = pageController;
		this.argument = argument;
		this.argument = argument;
		this.argument2 = argument2;
		this.argument3 = argument3;
		this.argument4 = argument4;
	}

	/**
	 * @return the pageController
	 */
	public PageController getPageController() {
		return pageController;
	}

	/**
	 * @return the argument
	 */
	public Object getArgument() {
		return argument;
	}

	/**
	 * @return the argument2
	 */
	public Object getArgument2() {
		return argument2;
	}

	/**
	 * @return the argument3
	 */
	public Object getArgument3() {
		return argument3;
	}

	/**
	 * @return the argument4
	 */
	public Object getArgument4() {
		return argument4;
	}

	/**
	 * 
	 *
	 *@return
	 */
	public Page getInstancePage(){
    	return instancePage;
    }

	/**
	 * 
	 *
	 *@param instancePage
	 */
	public void setInstancePage(Page instancePage){
    	this.instancePage = instancePage;
    }

}
