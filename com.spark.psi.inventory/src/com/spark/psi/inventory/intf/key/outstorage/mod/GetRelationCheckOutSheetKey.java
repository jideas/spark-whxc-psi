/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.outstorage.module
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>查询订单相关出库单情况</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
