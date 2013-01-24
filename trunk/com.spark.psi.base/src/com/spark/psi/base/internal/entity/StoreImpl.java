package com.spark.psi.base.internal.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Store;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;

@StructClass
public class StoreImpl implements Store {
	
	protected int recver;
	
	/**
	 * 仓库ID
	 */

	protected GUID id;

	/**
	 * 仓库名称
	 */

	protected String name;

	/**
	 * 仓库状态
	 */

	protected StoreStatus status;

	/**
	 * 所在省份
	 */
	protected EnumEntity province; 

	/**
	 * 所在城市
	 */
	protected EnumEntity city; 

	/**
	 * 所在区县
	 */
	protected EnumEntity town; 

	/**
	 * 地址
	 */
	protected String address;

	/**
	 * 邮编
	 */

	protected String postcode; 

	/**
	 * 收货人
	 */

	protected String consignee;

	/**
	 * 手机
	 */

	protected String mobileNo;
 

	/**
	 * 对应库管人员（库管离职，是否可以自动将最后一个离职库管的经理直接设置库管员？）
	 */

	protected List<GUID> keeperIdList = new ArrayList<GUID>();
	 

	private GUID creatorId;

	private String creator;

	private long createdDate;
	
	private String storeNo;
	private int shelfCount;//	货位数量
	private int defaultTiersCount;//	默认层数
	private String storeType;

 
	public GUID[] getKeeperIds(){ 
    	return keeperIdList.toArray(new GUID[keeperIdList.size()]);
    }

	public int getRecver() {
		return recver;
	}

	public void setRecver(int recver) {
		this.recver = recver;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StoreStatus getStatus() {
		return status;
	}

	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public EnumEntity getProvince() {
		return province;
	}

	public void setProvince(EnumEntity province) {
		this.province = province;
	}

	public EnumEntity getCity() {
		return city;
	}

	public void setCity(EnumEntity city) {
		this.city = city;
	}

	public EnumEntity getTown() {
		return town;
	}

	public void setTown(EnumEntity town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public List<GUID> getKeeperIdList() {
		return keeperIdList;
	}

	public void setKeeperIdList(List<GUID> keeperIdList) {
		this.keeperIdList = keeperIdList;
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

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
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

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

}
