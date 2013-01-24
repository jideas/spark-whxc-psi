package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 *客户转正事件
 * @author Administrator
 */
public class CustomerTurnOfficalEvent extends Event{
	private GUID customerId;

	public CustomerTurnOfficalEvent(GUID partnerId){
		this.customerId = partnerId;
    }

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
}
