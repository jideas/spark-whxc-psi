/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-20       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * ����⻧��ǰ�Ŀ����
 * @author zhongxin
 *
 */
public class GetTenantsStoreAmountKey {
	private GUID tenantsGuid;
	public GetTenantsStoreAmountKey(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
}
