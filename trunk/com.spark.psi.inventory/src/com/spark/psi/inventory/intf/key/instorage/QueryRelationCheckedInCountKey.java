/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.instorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>查询订单相关入库单已入库数量</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
