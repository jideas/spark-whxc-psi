/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.instorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage;

import com.jiuqi.dna.core.type.GUID;

public class CheckOutLogKey {

	/**
	 * ���ⵥID
	 */
	private GUID relaBillsId;

	public CheckOutLogKey(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

}
