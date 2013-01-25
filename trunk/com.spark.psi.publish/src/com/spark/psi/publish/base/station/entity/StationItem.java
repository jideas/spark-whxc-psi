package com.spark.psi.publish.base.station.entity;

import com.jiuqi.dna.core.type.GUID;

public interface StationItem {

	public GUID getId();

	public String getShortName();

	public String getName();

	public String getStationNo();

	public String getManager();

	public String getMobileNo();

	public String getAddress();

	public boolean isStop();
}
