package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ÿͻ��Ŀ������ö��</p>
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
