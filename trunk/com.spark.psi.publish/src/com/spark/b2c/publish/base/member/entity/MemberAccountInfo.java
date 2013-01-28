package com.spark.b2c.publish.base.member.entity;

import com.jiuqi.dna.core.type.GUID;

public interface MemberAccountInfo {
	public GUID getRecid();
	public double getMoneyBalance();
	public double getVantages();
	public String getPayPassword();

}
