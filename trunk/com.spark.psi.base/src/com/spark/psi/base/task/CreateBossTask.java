/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.organization.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.organization.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ͨ���⻧�����ļ�����Ա��</p>
 *


 *
 
 * @version 2012-3-27
 */

public class CreateBossTask extends SimpleTask{

	/**
	 * �⻧ID
	 */
	private GUID tenantId;
	
	private String config;
	
	public CreateBossTask(GUID tenantId,String config){
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
