package com.spark.psi.base.task.Materials;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.materials.entity.MaterialsTraderLogItem.TraderType;

/**
 * <p>更新商品交易记录</p>
 *
 */

public class UpdateMaterialsTraderLogTask extends SimpleTask{
	
	private GUID materialsItemId,pratnerId;
	
	private double price;
	
	private double count;
	
	private TraderType type;
	
	public UpdateMaterialsTraderLogTask(TraderType type,GUID MaterialsItemId,GUID partnerId,double count,double price){
		this.count = count;
		this.pratnerId = partnerId;
		this.materialsItemId = MaterialsItemId;
		this.price = price;
		this.type =type;
    }

	public GUID getMaterialsItemId(){
    	return materialsItemId;
    }

	public GUID getPratnerId(){
    	return pratnerId;
    }

	public double getCount(){
    	return count;
    }

	public double getPrice(){
    	return price;
    }

	public TraderType getType(){
    	return type;
    }
	
	
	
}
