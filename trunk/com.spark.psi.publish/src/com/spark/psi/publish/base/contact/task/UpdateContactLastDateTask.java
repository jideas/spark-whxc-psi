/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.contact.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-7-24    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.contact.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-7-24    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸���ϵ�˻��ջ���ַ�����һ��ʱ��ʱ��</p>
 *


 *
 
 * @version 2012-7-24
 */

public class UpdateContactLastDateTask extends SimpleTask{
	
	private GUID id;
	
	public UpdateContactLastDateTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
	
}
