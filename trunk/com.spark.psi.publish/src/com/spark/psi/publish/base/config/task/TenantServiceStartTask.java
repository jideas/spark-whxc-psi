/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.config.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-13    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.config.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-13    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�⻧����</p>
 *


 *
 
 * @version 2012-3-13
 */

public class TenantServiceStartTask extends SimpleTask{
	
	private GUID tenantId;
	
	public TenantServiceStartTask(GUID tenantId){
		this.tenantId = tenantId;
	}

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
}
