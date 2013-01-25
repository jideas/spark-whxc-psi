package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * ��������ͣ��ָ���ֿ�
 * 
 */
public class ChangeStoreStatusTask extends SimpleTask {

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * �ֿ�״̬
	 */
	private StoreStatus StoreStatus;

	/**
	 * ���캯��
	 * 
	 * @param storeId
	 * @param turnOnOrOff
	 */
	public ChangeStoreStatusTask(GUID storeId, StoreStatus StoreStatus) {
		super();
		this.storeId = storeId;
		this.StoreStatus = StoreStatus;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * 
	 * @return
	 */
	public StoreStatus getStoreStatus() {
		return this.StoreStatus;
	}
}
