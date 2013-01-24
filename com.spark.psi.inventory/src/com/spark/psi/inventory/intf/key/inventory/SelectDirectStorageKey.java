package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯ���⻧ֱ�����,Ĭ�ϲ�ѯ��ǰ�⻧��
 * @author modi
 *
 */
public class SelectDirectStorageKey {
	private final GUID tenantsGuid;
	

	public SelectDirectStorageKey(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}

	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	
}
