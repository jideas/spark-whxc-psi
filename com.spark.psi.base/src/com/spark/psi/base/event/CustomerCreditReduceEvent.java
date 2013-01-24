package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�ͻ����ü����¼�</p>
 *


 *
 
 * @version 2012-5-18
 */
public class CustomerCreditReduceEvent extends Event{
	
	private GUID customerId;
	
	public CustomerCreditReduceEvent(GUID customerId){
		this.customerId = customerId;
    }

	public GUID getCustomerId(){
    	return customerId;
    }

	public void setCustomerId(GUID customerId){
    	this.customerId = customerId;
    }
	
	
	
}