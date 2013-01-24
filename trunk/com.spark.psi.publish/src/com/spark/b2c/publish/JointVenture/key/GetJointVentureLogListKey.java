package com.spark.b2c.publish.JointVenture.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

public class GetJointVentureLogListKey extends LimitKey{

	public GetJointVentureLogListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private GUID supplierId;


	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}
	public GUID getSupplierId() {
		return supplierId;
	}
	
	
}
