/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.base.internal.entity.TenantSysParam;

/**
 * <p>�����⻧ϵͳ����</p>
 *


 *
 
 * @version 2012-4-11
 */

public class UpdateTenantSysParamTask extends SimpleTask{
		
	private TenantSysParam sysParam;
	
	public UpdateTenantSysParamTask(TenantSysParam sysParam){
		this.sysParam = sysParam;
	}

	public TenantSysParam getSysParam(){
    	return sysParam;
    }
	
}
