/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.outstorage.module
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ������س��ⵥ���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-20
 */

public class GetRelationCheckOutSheetKey {

	private GUID relationOrderId;

	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}

	public GUID getRelationOrderId() {
		return relationOrderId;
	}

	
}
