package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得客户的可用信用额度</p>
 *


 *
 
 * @version 2012-3-13
 */
public class GetCustomerAvailableCreditAmountKey{
	
	private GUID customerId;
	
	public GetCustomerAvailableCreditAmountKey(GUID customerId){
		this.customerId = customerId;
	}

	public GUID getCustomerId(){
    	return customerId;
    }
	
	
	
}
