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
import com.spark.psi.publish.Auth;

/**
 * <p>���Ӳ��ŵ�ְ��</p>
 *


 *
 
 * @version 2012-4-6
 */
@Deprecated
public class AddDepartmentAuthTask extends SimpleTask{
	
	private GUID deptId;
	
	private Auth[] auths;
	
	public AddDepartmentAuthTask(GUID deptId,Auth... auths){
		this.deptId = deptId;
		this.auths = auths;
	}

	public GUID getDeptId(){
    	return deptId;
    }

	public Auth[] getAuths(){
    	return auths;
    }
	
	
	
}
