/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.organization
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-6-11    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.organization
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-6-11    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.organization;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����⻧����</p>
 *  ����guid = GUID.MD5OF(�⻧����+�������ʣ�
 *


 *
 
 * @version 2012-6-11
 */

public class CreateDepartmentTask extends SimpleTask{
	/**
	 * �⻧ID
	 */
	private GUID tenantId;
	
	private String config;
	
	public CreateDepartmentTask(GUID tenantId,String config){
	    this.tenantId = tenantId;
	    this.config = config;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public String getConfig(){
    	return config;
    }
	

}
