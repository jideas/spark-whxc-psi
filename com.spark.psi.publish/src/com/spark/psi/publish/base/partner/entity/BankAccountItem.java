package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 供应商银行账户
 * 
 * @author Administrator
 * 
 */
public interface BankAccountItem {
	public GUID getId();

	public GUID getSupplierId();

	public String getBankName();

	public String getAccountHolder();

	public String getAccount();

	public String getRemark();
}
