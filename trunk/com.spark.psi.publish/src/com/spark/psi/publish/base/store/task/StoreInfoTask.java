package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 仓库新建和修改
 */
public class StoreInfoTask extends Task<StoreInfoTask.Method> {

	/**
	 * 操作方法
	 */
	public enum Method {
		Create, Update
	}

	private int recver;
	
	/**
	 * 仓库ID
	 */
	private GUID id;
	
	private String storeNo;

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	/**
	 * 仓库名称
	 */
	private String name;

	/**
	 * 仓库状态
	 */
	private StoreStatus status;

	/**
	 * 所在省份
	 */
	private String provinceCode;

	/**
	 * 所在城市
	 */
	private String cityCode;

	/**
	 * 所在区县
	 */
	private String townCode;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 邮编
	 */
	private String postcode; 

	/**
	 * 收货人
	 */
	private String consignee;

	/**
	 * 手机
	 */
	private String mobileNo;
 

	/**
	 * 对应库管人员
	 */
	private GUID[] keeperIds;
	
	/**
	 * 货位
	 */
	private ShelfInfoItem[] shelfInfoItems;
	
	private int shelfCount;
	
	private int defaultTiersCount;

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public StoreStatus getStoreStatus() {
		return status;
	}

	

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	} 

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	} 

	/**
	 * @return the consignee
	 */
	public String getConsignee() {
		return consignee;
	}

	/**
	 * @param consignee
	 *            the consignee to set
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}  

	/**
	 * @return the keeperIds
	 */
	public GUID[] getKeeperIds() {
		return keeperIds;
	}

	/**
	 * @param keeperIds
	 *            the keeperIds to set
	 */
	public void setKeeperIds(GUID[] keeperIds) {
		this.keeperIds = keeperIds;
	}

	public int getRecver(){
    	return recver;
    }

	public void setRecver(int recver){
    	this.recver = recver;
    }

	public StoreStatus getStatus() {
		return status;
	}

	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
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
	public ShelfInfoItem[] getShelfInfoItems() {
		return shelfInfoItems;
	}

	public void setShelfInfoItems(ShelfInfoItem[] shelfInfoItems) {
		this.shelfInfoItems = shelfInfoItems;
	}

	/**
	 * 获取仓库的货位个数
	 * @param shelfCount
	 */
	public int getShelfCount() {
		return shelfCount;
	}
	/**
	 * 设置仓库的货位个数
	 * @param shelfCount
	 */
	public void setShelfCount(int shelfCount) {
		this.shelfCount = shelfCount;
	}
	/**
	 * 获得仓库货位的默认层数
	 * @param shelfCount
	 */
	public int getDefaultTiersCount() {
		return defaultTiersCount;
	}
	/**
	 * 设置仓库货位的默认层数
	 * @param shelfCount
	 */
	public void setDefaultTiersCount(int defaultTiersCount) {
		this.defaultTiersCount = defaultTiersCount;
	}
	
	public interface ShelfInfoItem{
		/**
		 * 货位ID
		 * @return
		 */
		public GUID getId();
		
		/**
		 * 取得货位编号
		 * @return
		 */
		public int getShelfNo();
		
		/**
		 * 取得货位层数
		 * @return
		 */
		public int getTiersCount();
		
	}
	
}
