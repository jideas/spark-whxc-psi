/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸��û�����</p>
 *


 *
 
 * @version 2012-4-11
 */

public class UpdateUserPasswordTask extends SimpleTask{

	private GUID userId;

	private String newPwd,oldPwd;
	
	public UpdateUserPasswordTask(GUID userId, String newPwd,String oldPwd){
		this.userId = userId;
		this.newPwd = newPwd;
		this.oldPwd = oldPwd;
	}

	public GUID getUserId(){
    	return userId;
    }

	public String getNewPwd(){
    	return newPwd;
    }

	public String getOldPwd(){
    	return oldPwd;
    }

	
	
}
