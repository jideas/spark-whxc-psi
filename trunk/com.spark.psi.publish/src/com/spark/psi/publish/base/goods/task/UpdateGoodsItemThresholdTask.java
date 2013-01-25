package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;

/**
 * ����ָ����Ʒ�����ֵ
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
	 * �������ֿ������Ԥ��
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,GUID storeId,double storeUpper,double storeFloor) {
		this.inventoryWarningType = InventoryWarningType.Store_Count;
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
		this.storeUpper = storeUpper;
		this.storeFloor = storeFloor;
	}
	
	/**
	 * �������ֿ�Ľ��Ԥ��
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,GUID storeId,double storeAmountUpper) {
		this.inventoryWarningType = InventoryWarningType.Store_Amount;
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
		this.storeAmountUpper = storeAmountUpper;
	}
	
	/**
	 * �����вֿ��ܺ�����Ԥ��
	 */
	public GoodsItemThresholdItem(GUID goodsItemId,double storeUpper,double storeFloor){
		this.inventoryWarningType = InventoryWarningType.ALL_Count;
		this.goodsItemId = goodsItemId;
		this.storeUpper = storeUpper;
		this.storeFloor = storeFloor;
    }
	
	/**
	 * �����вֿ��ܺͽ��Ԥ��
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
