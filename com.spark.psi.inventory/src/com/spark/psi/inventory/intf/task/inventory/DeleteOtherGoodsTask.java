package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>删除一个仓库下的所有初始化物品信息</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-4-16
 */
public class DeleteOtherGoodsTask extends SimpleTask {

	private GUID storeId;
	
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * 删除一个仓库下的所有初始化物品信息
	 * @param storeId
	 */
	public DeleteOtherGoodsTask(GUID storeId)
	{
		this.storeId = storeId;
	}
}
