package com.spark.psi.publish.inventory.checkout.key;

import com.jiuqi.dna.core.type.GUID;

public class GetProduceTakeCheckoutInfoKey {

	private GUID id;

	public GetProduceTakeCheckoutInfoKey(GUID id){
		this.id = id;
	}
	
	public GUID getId(){
		return id;
	}
}
