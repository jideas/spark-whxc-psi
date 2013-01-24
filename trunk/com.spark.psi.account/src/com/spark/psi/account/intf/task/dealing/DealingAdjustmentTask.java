/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.account.intf.task.dealing
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-13       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>往来调整应收/应付Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class DealingAdjustmentTask extends SimpleTask {

	/**
	 * 客户/供应商ID
	 */
	private GUID partnerId;
	
	/**
	 * 类型（客户/供应商）
	 */
	private String type;
	/**
	 * 调整金额（正、负值）
	 */
	private double amount;
	
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
