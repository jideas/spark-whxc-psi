/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-18    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>���ò���ְ��</p>
 *


 *
 
 * @version 2012-4-18
 */

public class ResetDepartmentAuthTask extends SimpleTask{
	
	/**
	 * �Ƿ����Դ����ȡ�û�
	 */
	private boolean findByResource = true;
	
	private GUID tenantId;
	
	public ResetDepartmentAuthTask(boolean findByResource){
	    this.findByResource = findByResource;
    }
	
	public ResetDepartmentAuthTask(GUID tenantId){
	    this.tenantId = tenantId;
    }

	public boolean isFindByResource(){
    	return findByResource;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
	
}
