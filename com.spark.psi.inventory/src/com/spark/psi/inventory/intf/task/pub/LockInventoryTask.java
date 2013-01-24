/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �������������(����,����)
 *
 */
public class LockInventoryTask extends SimpleTask {
	
	private GUID storeId,goodsId;
	/**
	 * ������������ֵ��������ֵ����
	 */
	private double count;
	/**
	 * 
	 * @param storeGuid �ֿ�ID
	 * @param goodsGuid ��ƷID
	 */
	public LockInventoryTask(GUID storeId,GUID goodsId)
	{
		this.storeId = storeId;
		this.goodsId = goodsId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}

	public GUID getGoodsId() {
		return goodsId;
	}

	public void setCount(double count) {
		this.count = count;
	}
	public double getCount() {
		return count;
	}
	
}
