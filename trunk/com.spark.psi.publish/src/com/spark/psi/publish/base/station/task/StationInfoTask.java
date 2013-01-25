package com.spark.psi.publish.base.station.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class StationInfoTask extends SimpleTask{
	private GUID id	;//��ʶ	guid	
	private String stationNo	;//վ����	nvarchar	30
	private String stationName	;//վ������	nvarchar	50
	private String namePY	;//����ƴ��	nvarchar	50
	private String shortName	;//վ����	nvarchar	20
	private String telephone	;//�绰	nvarchar	20
	private String fax	;//����	nvarchar	20
	private String remark	;//��ע	nvarchar	1000
	private String province;
	private String city;
	private String town	;//����	nvarchar	20
	private String address	;//��ϸ��ַ	nvarchar	200
	private GUID managerId	;//������id	guid	
	private String manager	;//������	nvarchar	20
	private String managerPersonId	;//���������֤��	nvarchar	30
	private String managerPhone	;//�����˵绰	nvarchar	20
	private String managerEmail	;//����������	nvarchar	50
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
