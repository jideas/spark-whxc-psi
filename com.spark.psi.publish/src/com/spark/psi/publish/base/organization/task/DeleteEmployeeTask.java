/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.organization.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-7-13    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.organization.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-7-13    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.organization.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ɾ��Ա��</p>
 *


 *
 
 * @version 2012-7-13
 */

public class DeleteEmployeeTask extends SimpleTask{
	
	private GUID[] empId;
	
	public DeleteEmployeeTask(GUID... empId){
		this.empId = empId;
    }

	public GUID[] getEmpId(){
    	return empId;
    }
	
}
