package com.spark.uac.publish.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����⻧��ҵ���Ƽ��</p>
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
