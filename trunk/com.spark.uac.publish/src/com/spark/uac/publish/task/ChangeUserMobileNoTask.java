/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.publish.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-19    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.publish.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-19    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸��ֻ�����</p>
 *


 *
 
 * @version 2012-4-19
 */

public class ChangeUserMobileNoTask extends SimpleTask{
	
	private GUID userId;
	
	private String mobileNumber;
	
	public ChangeUserMobileNoTask(GUID userId,String mobileNumber){
	    this.userId = userId;
	    this.mobileNumber = mobileNumber;
    }

	public GUID getUserId(){
    	return userId;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }
	
	
}
