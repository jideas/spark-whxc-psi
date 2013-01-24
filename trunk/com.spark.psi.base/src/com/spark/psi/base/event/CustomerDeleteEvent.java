package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>客户删除事件</p>
 *


 *
 
 * @version 2012-5-11
 */
public class CustomerDeleteEvent extends Event{
	private GUID customerId;

	/**
     * @return the customerId
     */
    public GUID getCustomerId(){
    	return customerId;
    }

	/**
     * @param customerId the customerId to set
     */
    public void setCustomerId(GUID customerId){
    	this.customerId = customerId;
    }
    
    public CustomerDeleteEvent(GUID id){
    	this.customerId = id;
    }

}
