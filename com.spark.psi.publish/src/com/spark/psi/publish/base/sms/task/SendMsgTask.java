/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-12    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.sms.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>���Ͷ���Ϣ</p>
 *


 *
 
 * @version 2012-4-12
 */

public class SendMsgTask extends SimpleTask{
	
	private String mobileNumber;
	
	private String msg;
	
	private GUID tenantId;

	public SendMsgTask(String mobile,String msg,GUID tenantId){
	    this.mobileNumber = mobile;
	    this.msg = msg;
	    this.tenantId = tenantId;
    }
	
	public String getMobileNo(){
    	return mobileNumber;
    }

	public void setMobileNo(String mobileNumber){
    	this.mobileNumber = mobileNumber;
    }

	public String getMsg(){
    	return msg;
    }

	public void setMsg(String msg){
    	this.msg = msg;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }
	
	
	
}
