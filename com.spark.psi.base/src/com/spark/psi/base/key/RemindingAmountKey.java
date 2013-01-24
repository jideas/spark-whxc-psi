
package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>获取客户的预警金额</p>
 *
 */

public class RemindingAmountKey{
	
	private GUID tenantId;
	
	private GUID partnerId;
	/**
	 * 预警天数
	 */
	private int remindingDay;
	/**
	 * 账期
	 */
	private int accountPeriod;
	
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
	public void setRemindingDay(int remindingDay) {
		this.remindingDay = remindingDay;
	}
	public int getRemindingDay() {
		return remindingDay;
	}
	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	public int getAccountPeriod() {
		return accountPeriod;
	}
	
	
}
