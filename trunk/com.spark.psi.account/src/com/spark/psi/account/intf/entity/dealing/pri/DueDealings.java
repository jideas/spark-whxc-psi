/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.account.intf.entity.dealing.pri
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-30       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.entity.dealing.pri;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>应收/付</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-30
 */

public class DueDealings {

	private GUID id;
	private String cusproShortName;
	/**
	 * 信用额度
	 */
	private double creditline;
	/**
	 * 预警天数
	 */
	private int accountRemind;
	/**
	 * 应收/付金额
	 */
	private double amount;
	/**
	 * 账期
	 */
	private int accountPeriod;
	private String cusproName;
	/**
	 * 客户 or 供应商
	 */
	private String type;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getCusproShortName() {
		return cusproShortName;
	}
	public void setCusproShortName(String cusproShortName) {
		this.cusproShortName = cusproShortName;
	}
	/**
	 * 信用额度
	 */
	public double getCreditline() {
		return creditline;
	}
	/**
	 * 信用额度
	 */
	public void setCreditline(double creditline) {
		this.creditline = creditline;
	}
	/**
	 * 预警天数
	 */
	public int getAccountRemind() {
		return accountRemind;
	}
	/**
	 * 预警天数
	 */
	public void setAccountRemind(int accountRemind) {
		this.accountRemind = accountRemind;
	}
	/**
	 * 应收/付金额
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * 应收/付金额
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * 账期
	 */
	public int getAccountPeriod() {
		return accountPeriod;
	}
	/**
	 * 账期
	 */
	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	public String getCusproName() {
		return cusproName;
	}
	public void setCusproName(String cusproName) {
		this.cusproName = cusproName;
	}
	/**
	 * 客户 or 供应商
	 */
	public String getType() {
		return type;
	}
	/**
	 * 客户 or 供应商
	 */
	public void setType(String type) {
		this.type = type;
	}
}
