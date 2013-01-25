package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * @author Administrator
 * 
 */
public interface ShelfItem {
	public GUID getId() ;
	public GUID getStoreId();
	public int getShelfNo();
	public int getTiersCount();
}
