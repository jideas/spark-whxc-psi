package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ�ͻ�������Ԥ������</p>
 * �������һ���ѳ���δ����ĳ��ⵥ��ȷ�ϳ�������


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
