package com.spark.psi.publish.base.contact.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ�ͻ���Ӧ�̵ĵ�ַ�б�</p>
 *


 *
 
 * @version 2012-3-13
 */
public class GetAddressListByPartnerIdKey{
	
	private GUID partnerId;

	/**
	 * ���ָ���ͻ���Ӧ�̵ĵ�ַ�б�
	 * @param partnerId �ͻ���Ӧ��ID
	 */
	public GetAddressListByPartnerIdKey(GUID partnerId){
		this.partnerId = partnerId;
	}


	public GUID getPartnerId(){
    	return partnerId;
    }

}
