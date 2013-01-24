package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.store.entity.ShelfItem;

public class ShelfItemImpl implements ShelfItem {
	private GUID id;// 标识 guid
	private GUID storeId;// 仓库id guid
	private int shelfNo;// 货位编号 int
	private int tiersCount;// 层数 int

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public int getShelfNo() {
		return shelfNo;
	}

	public void setShelfNo(int shelfNo) {
		this.shelfNo = shelfNo;
	}

	public int getTiersCount() {
		return tiersCount;
	}

	public void setTiersCount(int tiersCount) {
		this.tiersCount = tiersCount;
	}

}
