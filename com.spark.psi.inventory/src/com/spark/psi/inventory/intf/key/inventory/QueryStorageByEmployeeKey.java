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
public class QueryStorageByEmployeeKey {

	private GUID tenantsGuid;
	private GUID employeeGuid;
	private StoreDetailItem employeeType;
	private StorageQueryType queryType = StorageQueryType.COMMON;
	//��ѯ�ֿ��״̬��Ĭ��Ϊ���ú��̵���
	private StoreStatus[] storeStatus = {StoreStatus.ENABLE, StoreStatus.ONCOUNTING};
	/**
	 * ��ѯָ�����͵�Ա�������Ĳֿ��Ӧ�Ŀ���¼
	 * @param employeeGuid Ա��GUID
	 * @param employyType Ա������ EmployeeType.STOREMANAGER or EmployeeType.SALER or EmployeeType.PURCHASE
	 */
	public QueryStorageByEmployeeKey(GUID tenantsGuid, GUID employeeGuid, StoreDetailItem employyType) {
		this.tenantsGuid = tenantsGuid;
		this.employeeGuid = employeeGuid;
		this.employeeType = employyType;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public GUID getEmployeeGuid() {
		return employeeGuid;
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
	/**
	 * ���ò�ѯ����
	 * Ĭ��Ϊ��StorageQueryType.COMMON û��������������
	 * �ɹ�Ԥ����StorageQueryType.BUY_PRE_WARNING
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
	public void setStoreStatus(StoreStatus[] StoreStatus) {
		this.storeStatus = StoreStatus;
	}

	
	
	
	
}
