package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.account.intf.entity.dealing.DealingItem;

/**
 * <p>������ϸ����</p>
 * <p>service:com.spark.bus.finance.cusdeal.service.FinanceCusdealService</p>
 *


 *
 * @author yanglin
 * @version 2011-11-15
 */
public class DealingItemTask extends SimpleTask{

	private DealingItem item;

	public void setItem(DealingItem item) {
		this.item = item;
	}

	public DealingItem getItem() {
		return item;
	}
	

}
