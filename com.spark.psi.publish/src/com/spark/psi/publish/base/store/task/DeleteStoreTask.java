package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 删除指定仓库
 * 
 */
public class DeleteStoreTask extends SimpleTask {

	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 构造函数
	 * @param storeId
	 */
	public DeleteStoreTask(GUID storeId) {
		super();
		this.storeId = storeId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

}
