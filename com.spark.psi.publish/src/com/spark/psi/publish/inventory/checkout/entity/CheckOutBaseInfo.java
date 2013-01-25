package com.spark.psi.publish.inventory.checkout.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public interface CheckOutBaseInfo {
	public GUID getRECID();

	public String getSheetNo();

	public String getCheckoutType();

	public GUID getPartnerId();

	public String getPartnerName();

	public String getNamePY();

	public String getPartnerShortName();

	public GUID getRelaBillsId();

	public String getRelaBillsNo();

	public GUID getStoreId();

	public String getStoreName();

	public String getStoreNamePY();

	public String getGoodsFrom();

	public String getGoodsUse();

	public String getTakePerson();

	public String getTakeUnit();

	public String getVouchersNo();

	public String getRemark();

	public double getAmount();

	public double getReceiptedAmount();

	public String getReceiptedFlag();

	public long getCheckoutDate();

	public GUID getCheckoutPerson();

	public String getCheckoutPersonName();

	public GUID getDeptId();

	public boolean isReceipting();

	public GUID getCreatorId();

	public String getCreator();
	
	public List<CheckOutBaseInfoItem> getItems();
}
