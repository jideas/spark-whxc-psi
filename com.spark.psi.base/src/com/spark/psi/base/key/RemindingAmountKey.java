
package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ȡ�ͻ���Ԥ�����</p>
 *
 */

public class RemindingAmountKey{
	
	private GUID tenantId;
	
	private GUID partnerId;
	/**
	 * Ԥ������
	 */
	private int remindingDay;
	/**
	 * ����
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
