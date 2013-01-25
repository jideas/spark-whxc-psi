package com.spark.order.purchase.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.key.SelectKey;

/**
 * <p>���²ɹ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-12-16
 */
public class SelectPurchaseAmountByDateKey extends SelectKey{
	private long startDate;
	private long endDate;
	private final GUID tenantsGuid;
	
	public SelectPurchaseAmountByDateKey(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	/**
	 * @return the startDate
	 */
	public long getStartDate() {
		return startDate;
	}
	/**
	 * @return the endDate
	 */
	public long getEndDate() {
		return endDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	
}
