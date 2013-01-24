package com.spark.psi.base.browser.start;

import com.spark.psi.publish.SysParamKey;

/**
 * <p>配置步骤节点</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-6
 */

public class WizardStepNode{

	/**系统参数key*/
	private SysParamKey key;

	/**上一页*/
	private String prevPageName;

	/**本页名字*/
	private String pageName;

	/**下一页*/
	private String nextPageName;

	/** 
	 *构造方法
	 *@param pageName 本页名字
	 *@param prevPageName 上一页
	 *@param nextPageName 下一页
	 */
	public WizardStepNode(SysParamKey key, String prevPageName, String pageName, String nextPageName){
		super();
		this.key = key;
		this.prevPageName = prevPageName;
		this.pageName = pageName;
		this.nextPageName = nextPageName;
	}

	public SysParamKey getKey(){
		return key;
	}

	public void setKey(SysParamKey key){
		this.key = key;
	}

	public String getPrevPageName(){
		return prevPageName;
	}

	public void setPrevPageName(String prevPageName){
		this.prevPageName = prevPageName;
	}

	public String getPageName(){
		return pageName;
	}

	public void setPageName(String pageName){
		this.pageName = pageName;
	}

	public String getNextPageName(){
		return nextPageName;
	}

	public void setNextPageName(String nextPageName){
		this.nextPageName = nextPageName;
	}
}
