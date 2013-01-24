package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 *客户创建事件
 * @author Administrator
 */
public class CustomerCreateEvent extends Event {
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
    
    public CustomerCreateEvent(GUID id){
    	this.customerId = id;
    }
}
