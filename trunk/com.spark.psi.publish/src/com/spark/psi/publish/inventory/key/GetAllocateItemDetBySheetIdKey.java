package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 取得指定调拔单所有的货位信息（只有调出库的货位信息）
 * @author Administrator
 *
 */
public class GetAllocateItemDetBySheetIdKey {
	private GUID sheetId;
	public GetAllocateItemDetBySheetIdKey(GUID sheetId) {
		this.sheetId = sheetId;
	}
	
	public GUID getSheetId() {
		return this.sheetId;
	}
}
