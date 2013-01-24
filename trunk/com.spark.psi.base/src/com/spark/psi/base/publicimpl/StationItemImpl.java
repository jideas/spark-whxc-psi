package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.station.entity.StationItem;

public class StationItemImpl implements StationItem {
	private GUID id;
	private String shortName;
	private String name;
	private String stationNo;
	private String manager;
	private String mobileNo;
	private String address;
	private boolean isStop;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isStop() {
		return isStop;
	}
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
}
