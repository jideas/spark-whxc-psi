package com.spark.psi.base.browser.start;

/**
 * <p>�Ƿ����ñ��水ť��Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-16
 */

public class EnabledSaveButtonMessage{

	/**�Ƿ�����*/
	private boolean enabled;
	
	/** 
     *���췽��
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
