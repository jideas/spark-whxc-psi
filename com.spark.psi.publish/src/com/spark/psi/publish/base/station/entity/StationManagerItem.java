package com.spark.psi.publish.base.station.entity;

import com.jiuqi.dna.core.type.GUID;

public interface StationManagerItem {
	public GUID getId();

	public String getName();

	public String getDeptName();

	public GUID getDeptId();

	public String getMobileNo();

	public String getIdNumber();
}
