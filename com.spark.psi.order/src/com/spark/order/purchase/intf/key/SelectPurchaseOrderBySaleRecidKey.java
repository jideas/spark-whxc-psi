package com.spark.order.purchase.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.key.SelectKey;

/**
 * <p>�������۶���GUID��ѯ��صĲɹ�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2012-1-5
 */
public class SelectPurchaseOrderBySaleRecidKey extends SelectKey{
	private final GUID saleRecid;
	private GUID tenantsGuid;
	public SelectPurchaseOrderBySaleRecidKey(GUID saleRecid) {
		this.saleRecid = saleRecid;
	}
	/**
	 * @return the saleRecid
	 */
	public GUID getSaleRecid() {
		return saleRecid;
	}
	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	/**
	 * @param tenantsGuid the tenantsGuid to set
	 */
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	
}
