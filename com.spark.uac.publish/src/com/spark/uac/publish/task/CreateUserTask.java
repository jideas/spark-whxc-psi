package com.spark.uac.publish.task;
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


import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>������¼�û�</p>
 *


 *
 
 * @version 2012-4-11
 */

public class CreateUserTask extends SimpleTask{
	
	private String mobile;
	
	private GUID tenantId;
	
	private GUID userId;
	/**
	 * �Ƿ���Ч
	 * ����������򵼴����û������û���Ч��Ϊfalse����Ҫ�ȵ�����ϵͳʱͳһ�޸��û�����Ч��Ϊtrue
	 */
	private boolean valid = true;
	
	public CreateUserTask(GUID tenantId,GUID userId,String mobile){
		this.mobile = mobile;
		this.tenantId = tenantId;
		this.userId = userId;
	}
	
	public CreateUserTask(GUID tenantId,GUID userId,String mobile,boolean valid){
		this.mobile = mobile;
		this.tenantId = tenantId;
		this.userId = userId;
		this.valid = valid;
	}
	
	
	

	public boolean isValid() {
		return valid;
	}

	public String getMobile(){
    	return mobile;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public GUID getUserId(){
    	return userId;
    }
	
	
	
	
	
}
