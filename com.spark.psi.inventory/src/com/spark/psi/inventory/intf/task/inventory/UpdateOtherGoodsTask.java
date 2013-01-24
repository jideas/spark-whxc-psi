package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.internal.entity.OthersInventory;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 更新物品库存数量
 *
 */
public class UpdateOtherGoodsTask extends Task<Method> {
	
	private OthersInventory otherStorage;
	private GUID storeId;
	private OtherGoods otherGoods;
	
	private boolean isInit = false;
	/**
	 * 
	 * @param goodsName 物品名称
	 * @param goodsDescription 物品描述
	 * @param goodsNum 物品数量
	 */
	public UpdateOtherGoodsTask(GUID storeId, OtherGoods otherGoods) {
		this.storeId = storeId;
		this.otherGoods = otherGoods;
	}
	/**
	 * 用于删除时调用 
	 */
	public UpdateOtherGoodsTask () {
		
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	public OtherGoods getOtherGoods() {
		return otherGoods;
	}
	public boolean isInit() {
		return isInit;
	}
	/**
	 * 是否是更改初始化信息默认为否
	 * @param isInit
	 */
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	public void setOtherStorage(OthersInventory otherStorage) {
		this.otherStorage = otherStorage;
	}
	public OthersInventory getOtherStorage() {
		return otherStorage;
	}
	
	
}
