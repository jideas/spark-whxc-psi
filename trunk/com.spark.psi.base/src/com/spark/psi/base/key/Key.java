package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

public abstract class Key{
	
	protected final GUID id;
	
	public Key(final GUID id){
		this.id = id;
	}

	public GUID getId(){
    	return id;
    }
	
	
}
