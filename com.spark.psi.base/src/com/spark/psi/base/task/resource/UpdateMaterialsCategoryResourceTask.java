/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��Ʒ������Դά��Task</p>
 *


 *
 
 * @version 2012-3-27
 */

public class UpdateMaterialsCategoryResourceTask extends Task<UpdateMaterialsCategoryResourceTask.Method>{
	
	public enum Method{
		Put,
		Modify,
		Remove
	}
	
	public GUID id;
	
	public UpdateMaterialsCategoryResourceTask(GUID id){
		this.id = id;
	}

}
