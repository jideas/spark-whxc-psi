/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-6    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>������в��ŵ�ְ��</p>
 *


 *
 
 * @version 2012-4-6
 */

public class RemoveAllDepartmentAuthTask extends SimpleTask{
		
	private GUID tenantId;
	
	public RemoveAllDepartmentAuthTask(GUID tenantId){
	    this.tenantId = tenantId;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
		
}
