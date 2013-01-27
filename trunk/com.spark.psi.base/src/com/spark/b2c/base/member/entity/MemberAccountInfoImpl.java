package com.spark.b2c.base.member.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.b2c.publish.base.member.entity.MemberAccountInfo;

public class MemberAccountInfoImpl implements MemberAccountInfo{
	private GUID recid;
	private double moneyBalance;
	private double vantages;
	private String payPassword;
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public double getMoneyBalance() {
		return moneyBalance;
	}
	public void setMoneyBalance(double moneyBalance) {
		this.moneyBalance = moneyBalance;
	}
	public double getVantages() {
		return vantages;
	}
	public void setVantages(double vantages) {
		this.vantages = vantages;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
	
}
