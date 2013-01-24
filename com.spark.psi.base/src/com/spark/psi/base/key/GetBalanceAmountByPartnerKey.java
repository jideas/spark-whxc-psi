package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得客户供应商的往来金额</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetBalanceAmountByPartnerKey extends Key{

	/**
	 * 获得客户供应商的往来金额
	 * @param partnerId 客户供应商id
	 */
	public GetBalanceAmountByPartnerKey(GUID partnerId){
	    super(partnerId);
    }

}
