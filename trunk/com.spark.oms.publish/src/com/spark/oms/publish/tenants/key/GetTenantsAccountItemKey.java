package com.spark.oms.publish.tenants.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ�����˻��б�
 *  GetTenantsAccountItemKey & List<AccountInfo>
 */
public class GetTenantsAccountItemKey {
	//�⻧��Ϣ
	private GUID tenantsId;

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	

}