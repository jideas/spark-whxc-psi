package com.spark.psi.publish.inventory.checkout.key;

import com.jiuqi.dna.core.type.GUID;

public class GetProduceReturnCheckoutInfoKey {

	private GUID id;

	public GetProduceReturnCheckoutInfoKey(GUID id){
		this.id = id;
	}
	
	public GUID getId(){
		return id;
	}
}
