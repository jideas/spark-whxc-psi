/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * É¾³ý²É¹ºÇåµ¥
 * @author zhoulijun
 *
 */
public class DeletePurchaseGoodsTask extends SimpleTask {

	private GUID id;
	
	public DeletePurchaseGoodsTask(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }
	
}
