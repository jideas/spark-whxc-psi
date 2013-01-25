package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;

/**
 * 更新指定商品库存阈值
 * 
 */
public class UpdateGoodsItemThresholdTask extends SimpleTask {

	public GoodsItemThresholdItem[] items;
	
	public UpdateGoodsItemThresholdTask(GoodsItemThresholdItem... items){
		this.items = items;
    }
	
	public static class GoodsItemThresholdItem{
	
	private Double storeUpper = -1d;
	private Double storeFloor = -1d;
	private Double storeAmountUpper = -1d;
	private GUID storeId;
	
	private InventoryWarningType inventoryWarningType;
	
	private GUID goodsItemId;
	
	/**
	 * 按各个仓库的数量预警
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,GUID storeId,double storeUpper,double storeFloor) {
		this.inventoryWarningType = InventoryWarningType.Store_Count;
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
		this.storeUpper = storeUpper;
		this.storeFloor = storeFloor;
	}
	
	/**
	 * 按各个仓库的金额预警
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,GUID storeId,double storeAmountUpper) {
		this.inventoryWarningType = InventoryWarningType.Store_Amount;
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
		this.storeAmountUpper = storeAmountUpper;
	}
	
	/**
	 * 按所有仓库总和数量预警
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,double storeUpper,double storeFloor){
		this.inventoryWarningType = InventoryWarningType.ALL_Count;
		this.goodsItemId = goodsItemId;
		this.storeUpper = storeUpper;
		this.storeFloor = storeFloor;
    }
	
	/**
	 * 按所有仓库总和金额预警
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,double storeAmountUpper) {
		this.inventoryWarningType = InventoryWarningType.ALL_Amount;
		this.goodsItemId = goodsItemId;
		this.storeAmountUpper = storeAmountUpper;
	}

	public Double getStoreUpper(){
    	return storeUpper;
    }

	public Double getStoreFloor(){
    	return storeFloor;
    }

	public Double getStoreAmountUpper(){
    	return storeAmountUpper;
    }

	public GUID getStoreId(){
    	return storeId;
    }

	public InventoryWarningType getInventoryWarningType() {
		return inventoryWarningType;
	}

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

		
	}

}
