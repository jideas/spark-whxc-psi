/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.pri
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-5    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.pri
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-5    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.pri;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ռ��α�</p>
 *


 *
 
 * @version 2012-4-5
 */

public class DeleteLevelTreeTask extends SimpleTask{
		
	private GUID tenantId;
	
	/**
	 * ���ָ���⻧��
	 * @param tenantId
	 */
	public DeleteLevelTreeTask(GUID tenantId){
	    this.tenantId = tenantId;
    }
	
	/**
	 * ���ȫ��
	 */
	public DeleteLevelTreeTask(){
		
	}

	public GUID getTenantId(){
    	return tenantId;
    }
	
	
}
