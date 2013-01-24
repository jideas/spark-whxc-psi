package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 抵减金额
 * 当初始化或者调整应该的时候
 * 如果之前的余额与变化的金额符号相反（一个为正一个为负）
 * 那么就去抵减出库单或者入库单里的金额
 * 
 * @author yanglin
 *
 */
public class CreditsAmountTask extends SimpleTask {
	/**
	 * @param cusproType 类型
	 * @param balance	之前的余额
	 * @param changeAmount	变化金额
	 */
	private String cusproType;
	private GUID cusproGuid;
	private double balance;
	private double changeAmount;
	
	/**
	 * @param cusproType
	 * @param balance
	 * @param changeAmount
	 */
	public CreditsAmountTask(String cusproType, GUID cusproGuid, double balance,
			double changeAmount) {
		super();
		this.cusproType = cusproType;
		this.cusproGuid = cusproGuid;
		this.balance = balance;
		this.changeAmount = changeAmount;
	}
	
	public GUID getCusproGuid() {
		return cusproGuid;
	}

	public void setCusproGuid(GUID cusproGuid) {
		this.cusproGuid = cusproGuid;
	}

	public String getCusproType() {
		return cusproType;
	}
	public void setCusproType(String cusproType) {
		this.cusproType = cusproType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}
}
