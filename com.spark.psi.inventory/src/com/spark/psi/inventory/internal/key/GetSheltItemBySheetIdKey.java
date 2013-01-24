package com.spark.psi.inventory.internal.key;

import com.jiuqi.dna.core.type.GUID;

public class GetSheltItemBySheetIdKey {
	private GUID sheetId;
	public GetSheltItemBySheetIdKey(GUID sheetId) {
		this.sheetId = sheetId;
	}
	
	
	public GUID getSheetId() {
		return this.sheetId;
	}
}
