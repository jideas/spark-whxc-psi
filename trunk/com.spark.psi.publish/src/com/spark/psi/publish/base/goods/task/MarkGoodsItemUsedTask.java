/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.goods.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.goods.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ʶ��Ʒ��Ŀ��ʹ��</p>
 *


 *
 
 * @version 2012-4-22
 */

public class MarkGoodsItemUsedTask extends SimpleTask{
	
	private GUID id;
	
	public MarkGoodsItemUsedTask(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

}
