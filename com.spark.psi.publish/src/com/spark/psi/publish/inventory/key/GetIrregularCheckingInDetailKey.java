package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获得零采入库单key，返回IrregualrCheckingInInfo
 * 
 *
 */
public class GetIrregularCheckingInDetailKey {
	
	// 零采入库单id
	private GUID id;
	
	public GetIrregularCheckingInDetailKey(GUID id) {
		this.id = id;
	}
	
	public GUID getId() {
		return this.id;
	}
}
