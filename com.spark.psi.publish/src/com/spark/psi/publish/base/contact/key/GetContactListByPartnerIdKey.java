package com.spark.psi.publish.base.contact.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ÿͻ���Ӧ�̵�������ϵ���б�</p>
 * 


 *
 
 * @version 2012-3-13
 */
public class GetContactListByPartnerIdKey{
	
	private GUID partnerId;

	/**
	 * ���ָ���ͻ���Ӧ�̵���ϵ���б�
	 * @param partnerId �ͻ���Ӧ��ID
	 */
	public GetContactListByPartnerIdKey(GUID partnerId){
		this.partnerId = partnerId;
	}


	public GUID getPartnerId(){
    	return partnerId;
    }
	
	
	
}
