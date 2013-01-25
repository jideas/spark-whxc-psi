package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.store.entity.StoreInitKitItem;

/**
 * ����ָ���ֿ�ĳ�ʼ��������Ʒ�б�
 * 
 */
public class SaveInitKitItemsTask extends SimpleTask {

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * ��ʼ����Ʒ�б�
	 */
	private StoreInitKitItem[] items;

	/**
	 * ���캯��
	 * 
	 * @param storeId
	 * @param items
	 */
	public SaveInitKitItemsTask(GUID storeId, StoreInitKitItem[] items) {
		super();
		this.storeId = storeId;
		this.items = items;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the items
	 */
	public StoreInitKitItem[] getItems() {
		return items;
	}

}
