package com.spark.psi.publish.base.store.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * ���������Ա�����Ĳֿ�key,����StoreInfo
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-21
 */
public class GetSalesEmployeeStoreKey {
	private final GUID employeeId;
	
	private StoreStatus[] StoreStatus;

	/**
	 * ȡ��ָ������Ա���Ĺ����ֿ⣬�޷���null��Ĭ��Ϊ��ǰ�û�
	 */
	public GetSalesEmployeeStoreKey() {
		employeeId = null;
	}

	/**
	 * ȡ��ָ������Ա���Ĺ����ֿ⣬�޷���null��Ĭ��Ϊ��ǰ�û�
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
