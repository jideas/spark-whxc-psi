/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-26    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-26    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * <p>�޸Ĳֿ�״̬</p>
 *


 *
 
 * @version 2012-3-26
 */

public class UpdateStoreStatusTask extends SimpleTask{
	
	private GUID id;
	
	private StoreStatus StoreStatus;
	
	public UpdateStoreStatusTask(GUID id,StoreStatus status){
		this.id = id;
		this.StoreStatus = status;
	}

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public StoreStatus getStoreStatus(){
    	return StoreStatus;
    }

	public void setStoreStatus(StoreStatus StoreStatus){
    	this.StoreStatus = StoreStatus;
    }
	
	
	
}
