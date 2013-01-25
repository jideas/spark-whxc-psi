package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountType;

/**
 * 开始盘点
 */
public class StartInventoryCountTask extends SimpleTask {

	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 盘点人
	 */
	private String counter;

	/**
	 * 盘点类型
	 */
	private InventoryCountType countType;

	/**
	 * 构造函数
	 * 
	 * @param storeId
	 * @param counter
	 * @param countType
	 */
	public StartInventoryCountTask(GUID storeId, String counter,
			InventoryCountType countType) {
		super();
		this.storeId = storeId;
		this.counter = counter;
		this.countType = countType;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the counter
	 */
	public String getCounter() {
		return counter;
	}

	/**
	 * @return the countType
	 */
	public InventoryCountType getCountType() {
		return countType;
	}

}
