package com.spark.psi.account.intf.key.dealing;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * <p>��ѯ������ʼ�����б�</p>
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
