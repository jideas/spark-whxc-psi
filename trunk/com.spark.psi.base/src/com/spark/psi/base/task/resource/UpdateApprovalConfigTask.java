/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-9    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-9    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig;

/**
 * <p>�����⻧��������</p>
 *


 *
 
 * @version 2012-4-9
 */

public class UpdateApprovalConfigTask extends SimpleTask{
	
	public ApprovalConfig config;
	
	public GUID tenantId;
	
	public UpdateApprovalConfigTask(ApprovalConfig config,GUID tenantId){
		this.config = config;
		this.tenantId = tenantId;
	}

	public ApprovalConfig getConfig(){
    	return config;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
	
}
