package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;

/**
 * 更新指定材料库存阈值
 * 
 */
public class UpdateMaterialsItemThresholdTask extends SimpleTask {

	public MaterialsItemThresholdItem[] items;
	
	public UpdateMaterialsItemThresholdTask(MaterialsItemThresholdItem... items){
		this.items = items;
    }
	
	public static class MaterialsItemThresholdItem{
	
	private Double storeUpper = -1d;
	private Double storeFloor = -1d;
	private Double storeAmountUpper = -1d;
	private GUID storeId;
	
	private InventoryWarningType inventoryWarningType;
	
	private GUID materialsItemId;
	
	/**
	 * 按各个仓库的数量预警
	 */
	public MaterialsItemThresholdItem(GUID MaterialsItemId,GUID storeId,double storeUpper,double storeFloor) {
		this.inventoryWarningType = InventoryWarningType.Store_Count;
		this.materialsItemId = MaterialsItemId;
		this.storeId = storeId;
		this.storeUpper = storeUpper;
		this.storeFloor = storeFloor;
	}
	
	/**
	 * 按各个仓库的金额预警
	 */
	public MaterialsItemThresholdItem(GUID MaterialsItemId,GUID storeId,double storeAmountUpper) {
		this.inventoryWarningType = InventoryWarningType.Store_Amount;
		this.materialsItemId = MaterialsItemId;
		this.storeId = storeId;
		this.storeAmountUpper = storeAmountUpper;
	}
	
	/**
	 * 按所有仓库总和数量预警
	 */
	public MaterialsItemThresholdItem(GUID MaterialsItemId,double storeUpper,double storeFloor){
		this.inventoryWarningType = InventoryWarningType.ALL_Count;
		this.materialsItemId = MaterialsItemId;
		this.storeUpper = storeUpper;
		this.storeFloor = storeFloor;
    }
	
	/**
	 * 按所有仓库总和金额预警
	 */
	public MaterialsItemThresholdItem(GUID MaterialsItemId,double storeAmountUpper) {
		this.inventoryWarningType = InventoryWarningType.ALL_Amount;
		this.materialsItemId = MaterialsItemId;
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

	public GUID getMaterialsItemId(){
    	return materialsItemId;
    }

		
	}

}
