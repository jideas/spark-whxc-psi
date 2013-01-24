/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.allocate.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-30       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.allocateinventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * query the count of the specified goods in the store 
 * @author zhongxin
 *
 */
public class QueryToOutCountKey {
	private GUID tenantsGuid;
	private GUID storeGuid;
	private GUID goodsGuid;
	
	public QueryToOutCountKey(GUID tenantsGuid, GUID storeGuid, GUID goodsGuid) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.goodsGuid = goodsGuid;
	}

	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public GUID getStoreGuid() {
		return storeGuid;
	}

	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	
}
