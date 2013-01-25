package com.spark.psi.publish.account.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PartnerType;

public class InitBalanceTask extends Task<InitBalanceTask.Method>{
	
	public static enum Method
	{
		/**
		 * ±£´æ
		 */
		Save,
		/**
		 * Íê³É
		 */
		Finish;
	}
	
	private PartnerType partnerType; 
	
	private Item[] items;
	
	public InitBalanceTask(Item[] items, PartnerType partnerType) {
		super();
		this.items = items;
		this.partnerType = partnerType;
	}
	public InitBalanceTask(PartnerType partnerType) {
		super();
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
