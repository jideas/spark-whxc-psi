package com.spark.psi.publish.base.contact.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得客户供应商的所有联系人列表</p>
 * 


 *
 
 * @version 2012-3-13
 */
public class GetContactListByPartnerIdKey{
	
	private GUID partnerId;

	/**
	 * 获得指定客户供应商的联系人列表
	 * @param partnerId 客户供应商ID
	 */
	public GetContactListByPartnerIdKey(GUID partnerId){
		this.partnerId = partnerId;
	}


	public GUID getPartnerId(){
    	return partnerId;
    }
	
	
	
}
