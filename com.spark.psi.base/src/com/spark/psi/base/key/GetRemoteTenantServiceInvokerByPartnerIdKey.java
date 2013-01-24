package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>通过客户供应商id获得远程服务调用器</p>
 * 查询方法
 * context.find(ServiceInvoker.class,GetRemoteTenantServiceInvokerByPartnerIdKey);


 *
 
 * @version 2012-3-12
 */
public class GetRemoteTenantServiceInvokerByPartnerIdKey {

	private GUID partnerId;
	
	public GetRemoteTenantServiceInvokerByPartnerIdKey(GUID partnerId){
	   this.partnerId = partnerId;
    }

	public GUID getTenantId(){
    	return partnerId;
    }	
		
}
