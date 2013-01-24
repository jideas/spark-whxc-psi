package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得指定客户供应商的交易情况</p>
 * 订单次数，订单金额，退货次数，退货金额 ，收付款金额


 *
 
 * @version 2012-3-9
 */
public class GetPartnerTradedByPartnerIdKey extends Key{

	public GetPartnerTradedByPartnerIdKey(GUID id){
	    super(id);
    }

}
