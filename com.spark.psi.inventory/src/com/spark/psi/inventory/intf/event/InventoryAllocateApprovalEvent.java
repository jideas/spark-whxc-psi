package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����������ͨ���¼�</p>
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
