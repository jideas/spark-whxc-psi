package com.spark.psi.publish.account.key;

import com.spark.psi.publish.PartnerType;

public class GetInitBalanceItemListKey {

	private PartnerType partnerType;


	public PartnerType getPartnerType() {
		return partnerType;
	}


	/**
	 * 
	 * @param type �ͻ�or��Ӧ��
	 */
	public GetInitBalanceItemListKey(PartnerType partnerType) {
		super();
		this.partnerType = partnerType;
	}
}
