package com.spark.psi.base.browser.start;

import com.spark.psi.publish.SysParamKey;

/**
 * <p>���ò���ڵ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-6
 */

public class WizardStepNode{

	/**ϵͳ����key*/
	private SysParamKey key;

	/**��һҳ*/
	private String prevPageName;

	/**��ҳ����*/
	private String pageName;

	/**��һҳ*/
	private String nextPageName;

	/** 
	 *���췽��
	 *@param pageName ��ҳ����
	 *@param prevPageName ��һҳ
	 *@param nextPageName ��һҳ
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
