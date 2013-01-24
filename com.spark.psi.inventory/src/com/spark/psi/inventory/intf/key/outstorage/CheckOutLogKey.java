/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.instorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage;

import com.jiuqi.dna.core.type.GUID;

public class CheckOutLogKey {

	/**
	 * 出库单ID
	 */
	private GUID relaBillsId;

	public CheckOutLogKey(GUID relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public GUID getRelaBillsId() {
		return relaBillsId;
	}

}
