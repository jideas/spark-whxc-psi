package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 *�ͻ���Ϣ�仯�¼�(ҵ�����˸ı��¼�)
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
