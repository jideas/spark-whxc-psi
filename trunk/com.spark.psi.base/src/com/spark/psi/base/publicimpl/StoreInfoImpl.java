package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.store.entity.ShelfItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;

/**
 * 仓库详细信息<br>
 * 查询方法：通过仓库ID直接查询StoreInfo对象
 */
public class StoreInfoImpl implements StoreInfo {

	protected int recver;
	protected ShelfItem[] shelfItems;
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
	 * 对应库管人员
	 */
	protected EmployeeInfo[] keepers;
	
	/**
	 * 仓库编号
	 */
	protected String storeNo;
	
	/**
	 * 货位数
	 */
	protected int shelfCount;
	
	/**
	 * 默认层数
	 */
	protected int defaultTiersCount;
	
	protected String storeType;
	

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	} 

	/**
	 * @return the city
	 */
	public EnumEntity getCity() {
		return city;
	}
 

	/**
	 * @return the consignee
	 */
	public String getConsignee() {
		return consignee;
	} 
	/**
	 * @return the keepers
	 */
	public EmployeeInfo[] getKeepers() {
		return keepers;
	}

	public void setId(GUID id){
    	this.id = id;
    }

	public void setName(String name){
    	this.name = name;
    } 

	public void setProvince(EnumEntity province){
    	this.province = province;
    }

	public void setCity(EnumEntity city){
    	this.city = city;
    } 
	public void setAddress(String address){
    	this.address = address;
    }  
	public void setConsignee(String consignee){
    	this.consignee = consignee;
    }  

	public void setKeepers(EmployeeInfo[] keepers){
    	this.keepers = keepers;
    }

	public GUID[] getKeeperIds(){
		GUID[] result = new GUID[keepers.length];
		for(int i=0;i<keepers.length;i++){
	        result[i] = keepers[i].getId();
        }
	    return result;
    }

	public StoreStatus getStatus() {
		return status;
	}

	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public EnumEntity getTown() {
		return town;
	}

	public void setTown(EnumEntity town) {
		this.town = town;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public EnumEntity getProvince() {
		return province;
	}

	public String getAddress() {
		return address;
	}

	public String getKepperInfo(){
		StringBuilder str = new StringBuilder();
		for(EmployeeInfo emp : keepers){
	        if(str.length()!=0){
	        	str.append(";"); 
	        }
	        str.append(emp.getName());
        }
	    return str.toString();
    }

	public int getRecver(){
    	return recver;
    }

	public void setRecver(int recver){
    	this.recver = recver;
    }

	public ShelfItem[] getShelfItems() {
		return shelfItems;
	}

	public void setShelfItems(ShelfItem[] shelfItems) {
		this.shelfItems = shelfItems;
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
