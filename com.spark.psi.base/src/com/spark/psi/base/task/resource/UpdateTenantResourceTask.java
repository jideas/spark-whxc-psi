/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����⻧������Ϣ��Դ</p>
 *


 *
 
 * @version 2012-6-8
 */

public class UpdateTenantResourceTask extends Task<UpdateTenantResourceTask.Method>{
	public enum Method{
		Put,
		Modify
	}
	
	private GUID id;
	
	public UpdateTenantResourceTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

	
	
}
