/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.instorage.module
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * ��ѯ���������ⵥ
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author Wangtiancai
 * @version 2012-3-20
 */

public class GetRelationCheckInSheetKey {

	private GUID relationOrdId;

	public GetRelationCheckInSheetKey(GUID relationOrdId) {
		this.relationOrdId = relationOrdId;
	}

	public GetRelationCheckInSheetKey() {
	}

	public void setRelationOrdId(GUID relationOrdId) {
		this.relationOrdId = relationOrdId;
	}

	public GUID getRelationOrdId() {
		return relationOrdId;
	}
}
