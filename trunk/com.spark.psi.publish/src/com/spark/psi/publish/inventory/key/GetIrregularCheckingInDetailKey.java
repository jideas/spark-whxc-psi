package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��������ⵥkey������IrregualrCheckingInInfo
 * 
 *
 */
public class GetIrregularCheckingInDetailKey {
	
	// �����ⵥid
	private GUID id;
	
	public GetIrregularCheckingInDetailKey(GUID id) {
		this.id = id;
	}
	
	public GUID getId() {
		return this.id;
	}
}
