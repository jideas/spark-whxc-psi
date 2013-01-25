package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 调整商品库存成本
 */
public class AdjustGoodsItemCostTask extends SimpleTask {

	/**
	 * 商品条目ID
	 */
	private GUID goodsItemId;

	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 新成本价格
	 */
	private double newCost;

	/**
	 * 构造函数
	 * 
	 * @param goodsItemId
	 * @param storeId
	 * @param newCost
	 */
	public AdjustGoodsItemCostTask(GUID goodsItemId, GUID storeId,
			double newCost) {
		super();
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
		this.newCost = newCost;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * @return the newCost
	 */
	public double getNewCost() {
		return newCost;
	}

}
