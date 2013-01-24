/**
 * 
 */
package com.spark.psi.account.browser;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 *
 */
public class PartnerSelectionMsg {
	public enum PartnerType{
		/**
		 * ��Ӧ��
		 */
		Supplier,
		/**
		 * �ͻ�
		 */
		Customer;
	}
	private GUID partnerId;
	private PartnerType partnerType;
	
	public PartnerSelectionMsg(GUID partnerId, PartnerType partnerType) {
		this.partnerId = partnerId;
		this.partnerType = partnerType;
	}
	
	public GUID getPartnerId() {
		return partnerId;
	}
	public PartnerType getPartnerType() {
		return partnerType;
	}
}
