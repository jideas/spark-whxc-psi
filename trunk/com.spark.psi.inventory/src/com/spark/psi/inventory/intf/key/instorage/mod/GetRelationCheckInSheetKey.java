/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.instorage.module
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * 查询订单相关入库单
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
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
