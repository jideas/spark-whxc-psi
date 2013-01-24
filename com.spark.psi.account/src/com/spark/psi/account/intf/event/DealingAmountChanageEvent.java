package com.spark.psi.account.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�ͻ���������仯�¼�</p>
 */
public class DealingAmountChanageEvent extends Event{

	/**
	 * ������Ŀid
	 */
	private GUID dealingItemId;
	
	/**
	 * ��Ӧ�ͻ���Ӧ��id
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
