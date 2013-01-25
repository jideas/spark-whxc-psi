/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-20    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-20    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.partner.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * <p>��ʶ�ͻ���Ӧ����ʹ�ù���</p>
 * 
 * @version 2012-4-20
 */

public class MarkPartnerUsedTask extends SimpleTask{
	
	private GUID id;
	private PartnerType type; 
	
	public MarkPartnerUsedTask(GUID id,PartnerType type){
	    this.id = id;
	    this.type = type;
    }
	 
	public GUID getId(){
    	return id;
    }
	
	public PartnerType getType(){
		return type;
	}
 
}
