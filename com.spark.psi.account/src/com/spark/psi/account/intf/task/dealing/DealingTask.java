package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.entity.dealing.Dealing;
import com.spark.psi.account.intf.entity.dealing.DealingItem;

/**
 * <p>ÐÂÔöÍùÀ´</p>
 * <p>service:com.spark.bus.finance.cusdeal.service.FinanceCusdealDaoService</p>
 *


 *
 * @author yanglin
 * @version 2011-11-15
 */
public class DealingTask extends SimpleTask{
	
	private Dealing dealing;
	private GUID partnerId;
	private double balance;
	private DealingItem item;

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setDealing(Dealing dealing) {
		this.dealing = dealing;
	}

	public Dealing getDealing() {
		return dealing;
	}

	public void setItem(DealingItem item) {
		this.item = item;
	}

	public DealingItem getItem() {
		return item;
	}

}
