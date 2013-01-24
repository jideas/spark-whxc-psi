package com.spark.psi.base.task.goods;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸���Ʒƽ���ɹ��۸�</p>
 *
 */

public class UpdateGoodsItemAveragePurchasePriceTask extends SimpleTask{
	
	private GUID goodsItemId;
	
	private double price;
	
	public UpdateGoodsItemAveragePurchasePriceTask(GUID goodsItemId,double price){
		this.goodsItemId = goodsItemId;
		this.price = price;
	}

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

	public double getPrice(){
    	return price;
    }
	
	
	
}	
