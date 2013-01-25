package com.spark.psi.publish.inventory.checkout.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ProduceCheckoutInfoInfoItem {

	public GUID getMaterialId();

	public String getMaterialName();

	public String getMaterialNo();

	public String getMaterialCode();

	public String getMaterialSpec();

	public String getMaterialUnit();

	public int getScale();

	public double getPlanCount();

	public double getRealCount();
}
