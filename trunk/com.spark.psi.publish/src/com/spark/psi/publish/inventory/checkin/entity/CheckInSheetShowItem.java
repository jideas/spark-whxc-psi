package com.spark.psi.publish.inventory.checkin.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;

public interface CheckInSheetShowItem {
	public GUID getId();

	public String getSheetNo();

	public CheckingInType getType();

	public long getCheckinDate();

	public String getRelaBillsNo();

	public String getStoreName();

	public String getCheckinPersonName();
}
