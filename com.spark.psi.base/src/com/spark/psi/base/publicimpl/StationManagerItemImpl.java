package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.station.entity.StationManagerItem;

public class StationManagerItemImpl implements StationManagerItem {

	public StationManagerItemImpl(GUID id, String name, GUID deptId, String deptName, String mobileNo, String idNumber) {
		this.id = id;
		this.name = name;
		this.deptName = deptName;
		this.deptId = deptId;
		this.idNumber = idNumber;
		this.mobileNo = mobileNo;
	}

	private GUID id;
	private String name;

	private String deptName;
	private GUID deptId;
	private String mobileNo, idNumber;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public GUID getDeptId() {
		return deptId;
	}

	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}
}
