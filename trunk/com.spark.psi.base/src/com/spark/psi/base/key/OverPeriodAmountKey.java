
package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ȡ�ͻ����ѳ����ڽ��</p>
 *
 */

public class OverPeriodAmountKey{

	private GUID tenantId;
	
	private GUID partnerId;
	/**
	 * ����
	 */
	private int accountPeriod;
	/**
	 * true:��ѯȫ��;false:��ѯ��Ͻ�ĵ���
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
	 * true:��ѯȫ��;false:��ѯ��Ͻ�ĵ���
	 */
	public void setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
	}
	public boolean isQueryAll() {
		return queryAll;
	}
	
}
