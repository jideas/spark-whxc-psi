package com.spark.psi.publish.inventory.checkout.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ProduceCheckoutInfoInfo {

	public GUID getStoreId();

	public String getStoreName();

	public String getCreator();

	public ProduceCheckoutInfoInfoItem[] getItems();
}
