package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 启动或者停用指定仓库
 * 
 */
public class ChangeStoreStatusTask extends SimpleTask {

	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 仓库状态
	 */
	private StoreStatus StoreStatus;

	/**
	 * 构造函数
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
