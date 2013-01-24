package com.spark.psi.account.intf.key.dealing;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * <p>查询往来初始化的列表</p>
 *


 *
 * @author yanglin
 * @version 2011-11-19
 */

public class GetInitDealingListKey {
	private PartnerType partnerType;

	public PartnerType getPartnerType() {
		return partnerType;
	}

	public GetInitDealingListKey(PartnerType partnerType) {
		super();
		this.partnerType = partnerType;
	}
	
	
}
