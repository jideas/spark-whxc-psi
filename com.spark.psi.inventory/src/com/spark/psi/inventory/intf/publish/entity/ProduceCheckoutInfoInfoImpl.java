package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfo;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfoItem;

public class ProduceCheckoutInfoInfoImpl implements ProduceCheckoutInfoInfo {
 
	private GUID storeId;
	private String storeName;
	private String creator;

	private ProduceCheckoutInfoInfoItem[] items;
 
	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public ProduceCheckoutInfoInfoItem[] getItems() {
		return items;
	}

	public void setItems(ProduceCheckoutInfoInfoItem[] items) {
		this.items = items;
	}
}
