package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

/**
 * <p>Õ˘¿¥≥ı ºªØ</p>
 *
 *
 */

public class InitDealingTask extends Task<InitDealingTask.Method> {

	public static enum Method
	{
		Save,Finish;
	}
	private PartnerType partnerType; 
	
	private Item[] items;
	
	public InitDealingTask(Item[] items, PartnerType partnerType) {
		super();
		this.items = items;
		this.partnerType = partnerType;
	}

	public static class Item
	{
		private GUID partnerId;
		private PartnerType partnerType;
		private double amount;
		public GUID getPartnerId() {
			return partnerId;
		}
		public PartnerType getPartnerType() {
			return partnerType;
		}
		public double getAmount() {
			return amount;
		}
		public Item(GUID partnerId, PartnerType partnerType, double amount) {
			super();
			this.partnerId = partnerId;
			this.partnerType = partnerType;
			this.amount = amount;
		}
	}

	public Item[] getItems() {
		return items;
	}

	public PartnerType getPartnerType() {
		return partnerType;
	}
	
	
}
