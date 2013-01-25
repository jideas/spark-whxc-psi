package com.spark.uac.publish.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得租户企业名称简称</p>
 *


 *
 
 * @version 2012-7-2
 */
public class GetCompanyInfoKey{

	private GUID tenantId;
	
	public GetCompanyInfoKey(GUID tenantId){
		this.tenantId = tenantId;
    }

	public GUID getTenantId(){
    	return tenantId;
    }
	
}
