
package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>获取客户的已超账期金额</p>
 *
 */

public class OverPeriodAmountKey{

	private GUID tenantId;
	
	private GUID partnerId;
	/**
	 * 账期
	 */
	private int accountPeriod;
	/**
	 * true:查询全部;false:查询管辖的单据
	 */
	private boolean queryAll = true;
	
	/**
     * @return the tenantId
     */
    public GUID getTenantId(){
    	return tenantId;
    }
	/**
     * @param tenantId the tenantId to set
     */
    public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public int getAccountPeriod() {
		return accountPeriod;
	}
	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	/**
	 * true:查询全部;false:查询管辖的单据
	 */
	public void setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
	}
	public boolean isQueryAll() {
		return queryAll;
	}
	
}
