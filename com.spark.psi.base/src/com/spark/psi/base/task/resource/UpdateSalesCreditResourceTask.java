/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-24    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-24    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.resource;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����������Ȩ��Դ</p>
 *


 *
 
 * @version 2012-4-24
 */

public class UpdateSalesCreditResourceTask extends Task<UpdateSalesCreditResourceTask.Method>{

	public enum Method{
		Put,
		Modify,
		Remove
	}

	private GUID id;
	
	public UpdateSalesCreditResourceTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
}

