/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-22    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerStatus;

/**
 * <p>���ÿͻ�״̬Ϊ��ʽ�ͻ�</p>
 *


 *
 
 * @version 2012-4-22
 */

public class UpdatePartnerStatusTask extends SimpleTask{
	
	private GUID partnerId;
	
	private PartnerStatus PartnerStatus;
	
	public UpdatePartnerStatusTask(GUID partnerId){
	    this.partnerId = partnerId;
	    this.PartnerStatus = PartnerStatus.Official;
    }
	
	public UpdatePartnerStatusTask(GUID partnerId,PartnerStatus status){
	    this.partnerId = partnerId;
	    this.PartnerStatus = status;
    }

	public GUID getPartnerId(){
    	return partnerId;
    }

	public PartnerStatus getPartnerStatus(){
    	return PartnerStatus;
    }
	
	
}
