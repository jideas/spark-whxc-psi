/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-21       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.intf.inventoryenum.StorageQueryType;
import com.spark.psi.inventory.intf.inventoryenum.StoreDetailItem;
import com.spark.psi.publish.StoreStatus;

/**
 * @author zhongxin
 *
 */
public class QueryStorageWithLimitKey {
	public enum QuerObjType {
		/** ��˾ */
		COMPANY,
		/** ���� */
		DEPARTMENT,
		/** Ա�� */
		EMPLOYEE
	}
	private GUID tenantsGuid;
	private GUID queryObjGuid;
	private StoreDetailItem employeeType;
	private StorageQueryType queryType;
	//��ѯ�ֿ��״̬��Ĭ��Ϊ���ú��̵���
	private StoreStatus[] storeStatus = {StoreStatus.ENABLE, StoreStatus.ONCOUNTING};
	private QuerObjType queryObjType; 
	/**
	 * ��ѯָ�����͵�Ա�������Ĳֿ��Ӧ�Ŀ���¼
	 * @param employeeGuid Ա��GUID
	 * @param employyType Ա������ StoreDetailItem.STOREMANAGER or StoreDetailItem.SALER or StoreDetailItem.PURCHASE
	 */
	public QueryStorageWithLimitKey(GUID tenantsGuid, StoreDetailItem employyType) {
		this.tenantsGuid = tenantsGuid;
		this.employeeType = employyType;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public GUID getQueryObjGuid() {
		return queryObjGuid;
	}

	public StoreDetailItem getEmployeeType() {
		return employeeType;
	}

	public StorageQueryType getQueryType() {
		return queryType;
	}
	public StoreStatus[] getStoreStatus() {
		return storeStatus;
	}
	public QuerObjType getQueryObjType() {
		return queryObjType;
	}

	/**
	 * ���ò�ѯ����
	 * QueryType.SALE_PRE_WARNING
	 * @param queryType
	 */
	public void setQueryType(StorageQueryType queryType) {
		this.queryType = queryType;
	}
	/**
	 * ���òֿ�״̬
	 * Ĭ��Ϊ���ú��̵���
	 * @param StoreStatus
	 */
	public void setStoreStatus(StoreStatus... storeStatus) {
		this.storeStatus = storeStatus;
	}
	/**
	 * ���ò�ѯ��������
	 * @param queryObjType
	 */
	public void setQueryObjType(QuerObjType queryObjType) {
		this.queryObjType = queryObjType;
	}
	/**
	 * ���ò�ѯ�����GUID
	 * @param queryObjGuid
	 */
	public void setQueryObjGuid(GUID queryObjGuid) {
		this.queryObjGuid = queryObjGuid;
	}
	
}
