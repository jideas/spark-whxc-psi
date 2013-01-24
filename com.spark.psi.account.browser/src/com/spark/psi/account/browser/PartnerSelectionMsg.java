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
		 * 供应商
		 */
		Supplier,
		/**
		 * 客户
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
