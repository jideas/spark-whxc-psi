package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.store.entity.StoreInitKitItem;

/**
 * 保存指定仓库的初始化其他物品列表
 * 
 */
public class SaveInitKitItemsTask extends SimpleTask {

	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 初始化物品列表
	 */
	private StoreInitKitItem[] items;

	/**
	 * 构造函数
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
