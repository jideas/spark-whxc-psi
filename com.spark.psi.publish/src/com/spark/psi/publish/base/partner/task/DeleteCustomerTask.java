/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.intf.partner.task.supplier
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.intf.partner.task.supplier
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ɾ����Ӧ��</p>
 *


 *
 
 * @version 2012-3-2
 */

public class DeleteCustomerTask extends SimpleTask{
	
	private GUID id;
	
	public DeleteCustomerTask(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }

	
}
