/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.instorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ���������ⵥ���������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-27
 */

public class QueryRelationCheckedInCountKey {

	private GUID relationOrderId;
	
	public QueryRelationCheckedInCountKey(GUID relationOrderId)
	{
		this.relationOrderId = relationOrderId;
	}

	public void setRelationOrderId(GUID relationOrderId) {
		this.relationOrderId = relationOrderId;
	}

	public GUID getRelationOrderId() {
		return relationOrderId;
	}
}
