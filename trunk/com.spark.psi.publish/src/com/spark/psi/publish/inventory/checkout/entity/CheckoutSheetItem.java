package com.spark.psi.publish.inventory.checkout.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public interface CheckoutSheetItem {
	public GUID getId();

	public String getBillsNo();

	public String getRelaBillsNo();

	public GUID getRelaBillsId();

	public CheckingOutType getSheetType();

	public String getCheckoutPersonName();

	public long getCheckoutDate();
	
	public double getAmount();
	
	public String getStoreName();
}
