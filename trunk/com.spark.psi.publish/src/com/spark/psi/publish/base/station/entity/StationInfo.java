package com.spark.psi.publish.base.station.entity;

import com.jiuqi.dna.core.type.GUID;

public interface StationInfo {

	public GUID getId();

	public String getStationNo();

	public String getStationName();

	public String getNamePY();

	public String getShortName();

	public String getTelephone();

	public String getFax();

	public String getRemark();

	public String getAddress();

	public GUID getManagerId();

	public String getManager();

	public String getManagerPersonId();

	public String getManagerPhone();

	public String getManagerEmail();

	public long getCreateDate();

	public GUID getCreatorId();

	public String getCreator();
	
	public String getProvince();
	public String getCity(); 
	public String getTown();
}
