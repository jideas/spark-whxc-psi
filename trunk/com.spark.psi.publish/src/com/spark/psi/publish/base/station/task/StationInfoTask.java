package com.spark.psi.publish.base.station.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class StationInfoTask extends SimpleTask{
	private GUID id	;//标识	guid	
	private String stationNo	;//站点编号	nvarchar	30
	private String stationName	;//站点名称	nvarchar	50
	private String namePY	;//名称拼音	nvarchar	50
	private String shortName	;//站点简称	nvarchar	20
	private String telephone	;//电话	nvarchar	20
	private String fax	;//传真	nvarchar	20
	private String remark	;//备注	nvarchar	1000
	private String province;
	private String city;
	private String town	;//地区	nvarchar	20
	private String address	;//详细地址	nvarchar	200
	private GUID managerId	;//负责人id	guid	
	private String manager	;//负责人	nvarchar	20
	private String managerPersonId	;//负责人身份证号	nvarchar	30
	private String managerPhone	;//负责人电话	nvarchar	20
	private String managerEmail	;//负责人邮箱	nvarchar	50
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	} 
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public GUID getManagerId() {
		return managerId;
	}
	public void setManagerId(GUID managerId) {
		this.managerId = managerId;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getManagerPersonId() {
		return managerPersonId;
	}
	public void setManagerPersonId(String managerPersonId) {
		this.managerPersonId = managerPersonId;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
}
