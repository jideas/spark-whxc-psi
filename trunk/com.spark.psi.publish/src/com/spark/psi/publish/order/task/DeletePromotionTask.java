/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ɾ��һ������</p>
 *


 *
 
 * @version 2012-3-6
 */

public class DeletePromotionTask extends SimpleTask{
	
	private final GUID id;
	
	public DeletePromotionTask(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }
	
	
	
}
