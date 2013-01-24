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
public class QueryStorageWithLimitKey {
	public enum QuerObjType {
		/** 公司 */
		COMPANY,
		/** 部门 */
		DEPARTMENT,
		/** 员工 */
		EMPLOYEE
	}
	private GUID tenantsGuid;
	private GUID queryObjGuid;
	private StoreDetailItem employeeType;
	private StorageQueryType queryType;
	//查询仓库的状态，默认为启用和盘点中
	private StoreStatus[] storeStatus = {StoreStatus.ENABLE, StoreStatus.ONCOUNTING};
	private QuerObjType queryObjType; 
	/**
	 * 查询指定类型的员工关联的仓库对应的库存记录
	 * @param employeeGuid 员工GUID
	 * @param employyType 员工类型 StoreDetailItem.STOREMANAGER or StoreDetailItem.SALER or StoreDetailItem.PURCHASE
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
	 * 设置查询类型
	 * QueryType.SALE_PRE_WARNING
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
	public void setStoreStatus(StoreStatus... storeStatus) {
		this.storeStatus = storeStatus;
	}
	/**
	 * 设置查询对象类型
	 * @param queryObjType
	 */
	public void setQueryObjType(QuerObjType queryObjType) {
		this.queryObjType = queryObjType;
	}
	/**
	 * 设置查询对象的GUID
	 * @param queryObjGuid
	 */
	public void setQueryObjGuid(GUID queryObjGuid) {
		this.queryObjGuid = queryObjGuid;
	}
	
}
