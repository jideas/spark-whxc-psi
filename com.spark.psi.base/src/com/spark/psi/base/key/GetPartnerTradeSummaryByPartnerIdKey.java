package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * 
 * <p>��ÿͻ���Ӧ�̵Ľ��׻���</p>
 *	
 *	�����ܶ�����ܴ���<Br>
 *  ������������ֹ������ɵ�
 *


 *
 
 * @version 2012-3-30
 */
public class GetPartnerTradeSummaryByPartnerIdKey{
	
	private GUID partnerId;
	
	private PartnerType type;
	
	public GetPartnerTradeSummaryByPartnerIdKey(GUID partnerId,PartnerType type){
		this.partnerId = partnerId;
		this.type = type;
	}

	public GUID getPartnerId(){
    	return partnerId;
    }

	public PartnerType getType(){
    	return type;
    }	
	
}
