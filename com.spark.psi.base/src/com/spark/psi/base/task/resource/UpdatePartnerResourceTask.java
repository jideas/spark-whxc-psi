/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-28    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-28    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ά���ͻ���Ӧ����Դ</p>
 *


 *
 
 * @version 2012-3-28
 */

public class UpdatePartnerResourceTask extends Task<UpdatePartnerResourceTask.Method>{
	
	public enum Method{
		Put,
		Modify,
		Remove
	}

	
	public GUID id;
	
	public UpdatePartnerResourceTask(GUID id){
		this.id = id;
	}

	
}
