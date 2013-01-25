package com.spark.psi.publish.base.partner.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询客户已超帐期情况</p> 
 */
public class GetCustomerOverCreditDayKey{
	
	private GUID id;
	
	public GetCustomerOverCreditDayKey(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }

	
}
