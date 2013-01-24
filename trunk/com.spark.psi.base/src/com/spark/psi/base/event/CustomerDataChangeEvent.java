package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 *客户信息变化事件(业务负责人改变事件)
 * @author Administrator
 */
public class CustomerDataChangeEvent extends Event {
 
	private GUID customerId;

	public CustomerDataChangeEvent(GUID id){
		this.customerId = id;
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
