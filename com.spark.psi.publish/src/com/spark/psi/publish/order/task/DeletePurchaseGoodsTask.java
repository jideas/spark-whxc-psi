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
 * ɾ���ɹ��嵥
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
