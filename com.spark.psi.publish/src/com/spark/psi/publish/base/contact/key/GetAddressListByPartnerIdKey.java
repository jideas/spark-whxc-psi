package com.spark.psi.publish.base.contact.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>查询客户供应商的地址列表</p>
 *


 *
 
 * @version 2012-3-13
 */
public class GetAddressListByPartnerIdKey{
	
	private GUID partnerId;

	/**
	 * 获得指定客户供应商的地址列表
	 * @param partnerId 客户供应商ID
	 */
	public GetAddressListByPartnerIdKey(GUID partnerId){
		this.partnerId = partnerId;
	}


	public GUID getPartnerId(){
    	return partnerId;
    }

}
