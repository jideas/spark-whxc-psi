package com.spark.psi.account.intf.key.dealing;

/**
 * <p>客户/供应商 未初始化数量</p>
 *


 *
 * @author 杨霖
 * @version 2011-11-14
 */

public class CusdealInitCountKey {
	private int cusCount;
	private int proCount;
	
	public int getCusCount() {
		return cusCount;
	}
	public void setCusCount(int cusCount) {
		this.cusCount = cusCount;
	}
	public int getProCount() {
		return proCount;
	}
	public void setProCount(int proCount) {
		this.proCount = proCount;
	}
}
