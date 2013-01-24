/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.key
 * 修改记录：
 * 日期                作者           内容
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
	//查询仓库的状态，默认为启用和盘点中
	private StoreStatus[] storeStatus = {StoreStatus.ENABLE, StoreStatus.ONCOUNTING};
	/**
	 * 查询指定类型的员工关联的仓库对应的库存记录
	 * @param employeeGuid 员工GUID
	 * @param employyType 员工类型 EmployeeType.STOREMANAGER or EmployeeType.SALER or EmployeeType.PURCHASE
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
	 * 设置查询类型
	 * 默认为：StorageQueryType.COMMON 没有任务特殊条件
	 * 采购预警：StorageQueryType.BUY_PRE_WARNING
	 * @param queryType
	 */
	public void setQueryType(StorageQueryType queryType) {
		this.queryType = queryType;
	}
	/**
	 * 设置仓库状态
	 * 默认为启用和盘点中
	 * @param StoreStatus
	 */
	public void setStoreStatus(StoreStatus[] StoreStatus) {
		this.storeStatus = StoreStatus;
	}

	
	
	
	
}
