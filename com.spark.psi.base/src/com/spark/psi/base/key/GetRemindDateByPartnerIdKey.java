package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询客户的账期预警日期</p>
 * 获得最早一张已出库未付款的出库单的确认出库日期


 *
 
 * @version 2012-3-29
 */
public class GetRemindDateByPartnerIdKey{
	
	private GUID customerId;
	
	public GetRemindDateByPartnerIdKey(GUID customerId){
		this.customerId = customerId;
	}

	public GUID getCustomerId(){
    	return customerId;
    }
	
	
	
}
