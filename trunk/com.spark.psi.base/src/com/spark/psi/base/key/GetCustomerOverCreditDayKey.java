package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得客户已超账期天数</p>
 *


 *
 
 * @version 2012-3-13
 */
public class GetCustomerOverCreditDayKey{

	private GUID customerId;
	
	public GetCustomerOverCreditDayKey(GUID customerId){
		this.customerId = customerId;
	}

	public GUID getCustomerId(){
    	return customerId;
    }
	

}
