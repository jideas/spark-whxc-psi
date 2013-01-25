package com.spark.psi.publish.base.store.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 获得销售人员关联的仓库key,返回StoreInfo
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-21
 */
public class GetSalesEmployeeStoreKey {
	private final GUID employeeId;
	
	private StoreStatus[] StoreStatus;

	/**
	 * 取得指定销售员工的关联仓库，无返回null。默认为当前用户
	 */
	public GetSalesEmployeeStoreKey() {
		employeeId = null;
	}

	/**
	 * 取得指定销售员工的关联仓库，无返回null。默认为当前用户
	 * @param employeeId
	 */
	public GetSalesEmployeeStoreKey(GUID employeeId) {
		this.employeeId = employeeId;
	}
	
	public GUID getEmployeeId() {
		return employeeId;
	}

	public StoreStatus[] getStoreStatus() {
		return StoreStatus;
	}

	public void setStoreStatus(StoreStatus... StoreStatus) {
		this.StoreStatus = StoreStatus;
	}
	
}
