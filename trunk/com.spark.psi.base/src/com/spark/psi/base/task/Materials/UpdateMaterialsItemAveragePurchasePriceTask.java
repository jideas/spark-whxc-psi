package com.spark.psi.base.task.Materials;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改商品平均采购价格</p>
 *
 */

public class UpdateMaterialsItemAveragePurchasePriceTask extends SimpleTask{
	
	private GUID materialsItemId;
	
	private double price;
	
	public UpdateMaterialsItemAveragePurchasePriceTask(GUID MaterialsItemId,double price){
		this.materialsItemId = MaterialsItemId;
		this.price = price;
	}

	public GUID getMaterialsItemId(){
    	return materialsItemId;
    }

	public double getPrice(){
    	return price;
    }
	
	
	
}	
