package com.spark.psi.account.intf.task.dealing;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * �ּ����
 * ����ʼ�����ߵ���Ӧ�õ�ʱ��
 * ���֮ǰ�������仯�Ľ������෴��һ��Ϊ��һ��Ϊ����
 * ��ô��ȥ�ּ����ⵥ������ⵥ��Ľ��
 * 
 * @author yanglin
 *
 */
public class CreditsAmountTask extends SimpleTask {
	/**
	 * @param cusproType ����
	 * @param balance	֮ǰ�����
	 * @param changeAmount	�仯���
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
