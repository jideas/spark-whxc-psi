package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * 
 * <p>获得客户供应商的交易汇总</p>
 *	
 *	交易总额，交易总次数<Br>
 *  已审批、已中止、已完成的
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
