/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * ����ָ������Ϣ��ѯ���
 * @author zhongxin
 *
 */
public class QueryStorageKey {
	private GUID tenantsGuid;
	private GUID storeGuid;
	private GUID goodsGuid;
	
	/** ��ѯ��������  */
	private boolean isInit;
	
	/**
	 * ��ѯָ����Ʒ��ָ���ֿ�Ŀ����Ϣ
	 * @param tenantsGuid �⻧GUID
	 * @param goodsGuid ��ѯ��Ʒ��GUID
	 * @param storeGuid ��ѯ�ֿ��GUID
	 */
	public QueryStorageKey(GUID tenantsGuid, GUID goodsGuid, GUID storeGuid) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.goodsGuid = goodsGuid;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public boolean isInit() {
		return isInit;
	}
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	
	
}
