package com.spark.psi.base.task.Materials;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸Ĳ�������ɹ�����</p>
 * 
 */
public class UpdateMaterialsItemRecentPurchasePriceTask extends SimpleTask{
	
	private GUID id;
	
	private double price;
	
	public UpdateMaterialsItemRecentPurchasePriceTask(GUID id,double price){
	    this.id = id;
	    this.price = price;
    }

	public GUID getId(){
    	return id;
    }

	public double getPrice(){
    	return price;
    }
	
	
	
}
