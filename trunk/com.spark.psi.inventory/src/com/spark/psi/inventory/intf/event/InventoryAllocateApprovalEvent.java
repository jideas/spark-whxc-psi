package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>库存调拨审批通过事件</p>
 *


 *
 
 * @version 2012-4-27
 */
public class InventoryAllocateApprovalEvent extends Event{
	
	private GUID id;
	
	public InventoryAllocateApprovalEvent(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
}
