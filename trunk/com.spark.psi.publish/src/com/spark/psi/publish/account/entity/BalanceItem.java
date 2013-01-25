package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 客户或者供应商的应收或者应付信息<BR>
 * 查询方法：<br>
 * (1)查询所有客户的BalanceItem列表：BalanceListEntity+GetCustomerBalanceListKey<br>
 * (2)查询所有供应商的BalanceItem列表：BalanceListEntity+GetSupplierBalanceListKey<br>
 * (3)查询往来初始化列表：BalanceListEntity+GetInitBalanceItemListKey<br>
 */
public interface BalanceItem {

	public GUID getId();
	public GUID getPartnerId();
	public String getPartnerName();
	public String getNamePY();
	public String getPartnerShortName();
	public String getType();
	public double getAmount();
	public double getInitAmount();

}
