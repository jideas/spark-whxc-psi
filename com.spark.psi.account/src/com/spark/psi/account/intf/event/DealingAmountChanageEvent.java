package com.spark.psi.account.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>客户往来款发生变化事件</p>
 */
public class DealingAmountChanageEvent extends Event{

	/**
	 * 往来条目id
	 */
	private GUID dealingItemId;
	
	/**
	 * 对应客户供应商id
	 */
	private GUID partnerId;

	public DealingAmountChanageEvent(GUID dealingItemId,GUID partnerId){
	    this.dealingItemId = dealingItemId;
	    this.partnerId = partnerId;
    }
	
	/**
     * @return the dealingItemId
     */
    public GUID getDealingItemId(){
    	return dealingItemId;
    }


	public GUID getPartnerId(){
    	return partnerId;
    }
    
    
}
