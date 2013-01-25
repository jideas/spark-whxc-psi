package com.spark.psi.publish.inventory.checkin.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;

public interface CheckInBaseInfo {
	public GUID getId();

	public String getSheetNo();

	public CheckingInType getSheetType();

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

	public String getRemark();

	public String getBuyPerson();

	public long getBuyDate();

	public double getAmount();

	public double getAskedAmount();

	public double getPaidAmount();

	public double getDisAmount();

	public long getCheckinDate();

	public GUID getCheckinPerson();

	public String getCheckinPersonName();

	public GUID getDeptId();

	public GUID getCreatorId();

	public String getCreator();
	
	public List<CheckInBaseInfoItem> getItems(); 
}
