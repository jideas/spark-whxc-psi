package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ʒ���ɱ�
 */
public class AdjustGoodsItemCostTask extends SimpleTask {

	/**
	 * ��Ʒ��ĿID
	 */
	private GUID goodsItemId;

	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * �³ɱ��۸�
	 */
	private double newCost;

	/**
	 * ���캯��
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
