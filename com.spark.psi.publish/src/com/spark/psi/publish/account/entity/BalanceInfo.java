/**
 * 
 */
package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 *
 */
public interface BalanceInfo {

	public GUID getId();
	public GUID getPartnerId();
	public String getPartnerName();
	public String getNamePY();
	public String getPartnerShortName();
	public String getType();
	public double getAmount();
	public double getInitAmount();
	public BalanceInfoItem[] getItems() ;
}
