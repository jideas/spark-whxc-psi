/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-1-5       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * @author zhongxin
 *
 */
public class UpdateOtherStorageTask extends SimpleTask {
	private GUID tenantsGuid;
	private GUID storeGuid;

	public UpdateOtherStorageTask(GUID tenantsGuid, GUID storeGuid) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
	}

	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}

	public GUID getStoreGuid() {
		return storeGuid;
	}

	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}
	
	
}
