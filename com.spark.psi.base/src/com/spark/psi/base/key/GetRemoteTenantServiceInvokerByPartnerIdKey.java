package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>ͨ���ͻ���Ӧ��id���Զ�̷��������</p>
 * ��ѯ����
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
