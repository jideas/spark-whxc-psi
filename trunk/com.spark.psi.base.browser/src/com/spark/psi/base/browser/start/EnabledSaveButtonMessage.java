package com.spark.psi.base.browser.start;

/**
 * <p>是否启用保存按钮消息</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-16
 */

public class EnabledSaveButtonMessage{

	/**是否启用*/
	private boolean enabled;
	
	/** 
     *构造方法
     *@param enabled
     */
    public EnabledSaveButtonMessage(boolean enabled){
	    super();
	    this.enabled = enabled;
    }

	public boolean isEnabled(){
		return enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

}
