package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����⻧��Ʒ������</p>
 *


 *
 
 * @version 2012-3-9
 */
public class GetGoodsInventoryByTenantIdKey {

	private GUID tenantId;
	
	public GUID getTenantId() {
		return tenantId;
	}

	/**
	 * ��ѯָ���⻧�Ŀ�����
	 * @param tenantId  �⻧id
	 */
	public GetGoodsInventoryByTenantIdKey(GUID tenantId){
		this.tenantId = tenantId;
    }

}
