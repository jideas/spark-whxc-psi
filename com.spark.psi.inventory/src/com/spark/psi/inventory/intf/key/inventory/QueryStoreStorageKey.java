/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-15       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * @author zhongxin
 *
 */
public class QueryStoreStorageKey {
//	public final static GUID allStore = GUID.valueOf("2222222222222222222222222222222");
	private GUID tenantsGuid;
	private GUID storeGuid;
	private InventoryType storageType;
	//�Ƿ��ʼ��
	private Boolean isInit = null;
	//ָ����Ʒ
	private GUID goodsGuid;
	
	private boolean isShowZero = true;
	
	/**
	 * ��ѯָ���ֿ�Ŀ����Ϣ
	 * @param tenantsGuid �⻧GUID
	 * @param storeGuid Ҫ��ѯ�Ĳֿ�GUID
	 * @param storageType ������ͣ�StorageType.GOODSSTORAGE or StorageType.OTHERSTORAGE��
	 */
	public QueryStoreStorageKey(GUID tenantsGuid, GUID storeGuid, InventoryType storageType) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.storageType = storageType;
	}
	/**
	 * ��ѯָ���ֿ�Ŀ����Ϣ
	 * @param tenantsGuid �⻧GUID
	 * @param storeGuid Ҫ��ѯ�Ĳֿ�GUID
	 */
	public QueryStoreStorageKey(GUID tenantsGuid, GUID storeGuid) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
	}
	/**
	 * ��ѯָ���ֿ�Ŀ����Ϣ
	 * @param tenantsGuid �⻧GUID
	 * @param storeGuid Ҫ��ѯ�Ĳֿ�GUID
	 * @param isInit �Ƿ��ʼ��
	 */
	public QueryStoreStorageKey(GUID tenantsGuid, GUID storeGuid, boolean isInit) {
		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.isInit = isInit;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public InventoryType getStorageType() {
		return storageType;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public Boolean isInit() {
		return isInit;
	}
	public void setInit(Boolean isInit) {
		this.isInit = isInit;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	/**
	 * ���ò�ѯָ����Ʒ��GUID
	 * @param goodsGuid
	 */
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public boolean isShowZero() {
		return isShowZero;
	}
	/**
	 * �����Ƿ񷵻ؿ������Ϊ0����Ʒ�����Ϣ
	 * @param isShowZero
	 */
	public void setShowZero(boolean isShowZero) {
		this.isShowZero = isShowZero;
	}
	
	
}
