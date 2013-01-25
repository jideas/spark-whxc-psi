package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ɾ��ָ���ֿ�
 * 
 */
public class DeleteStoreTask extends SimpleTask {

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * ���캯��
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
