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

package com.spark.uac.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.UserStatus;

/**
 * <p>�޸��û�״̬</p>
 *	δ����״̬	
 *	������״̬
 *	�Ѽ���״̬


 *
 
 * @version 2012-4-12
 */

public class ChangeUserStatusTask extends SimpleTask{
	
	private UserStatus userstatus;
	
	private GUID userId;
	
	public ChangeUserStatusTask(GUID userId,UserStatus userstatus){
	    this.userId = userId;
	    this.userstatus = userstatus;
    }

	public UserStatus getUserStatus(){
    	return userstatus;
    }

	public GUID getUserId(){
    	return userId;
    }
	
	
	
}
