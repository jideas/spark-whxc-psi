package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class StoreOrmEntity {
	
	protected GUID id;//	行标识
	protected int recver;//	行版本
	protected String storeNo;//	仓库编号
	protected String storeName;//	仓库名称
	protected String namePY;//	名称拼音
	protected String consignee;//	收货人
	protected String mobileNo;//	手机号
	protected String province;//	省份
	protected String city;//	城市
	protected String town;//	区县
	protected String address;//	地址
	protected String postCode;//	邮编
	protected int shelfCount;//	货位数量
	protected int defaultTiersCount;//	默认层数
	protected String status;//	仓库状态
	protected String storeType;//	仓库类型
	protected long createDate;//	创建日期
	protected GUID creatorId;//	创建人Guid
	protected String creator;//	创建人
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public int getRecver() {
		return recver;
	}
	public void setRecver(int recver) {
		this.recver = recver;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public int getShelfCount() {
		return shelfCount;
	}
	public void setShelfCount(int shelfCount) {
		this.shelfCount = shelfCount;
	}
	public int getDefaultTiersCount() {
		return defaultTiersCount;
	}
	public void setDefaultTiersCount(int defaultTiersCount) {
		this.defaultTiersCount = defaultTiersCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

}
