package com.spark.psi.publish.base.store.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * �ֿ��½����޸�
 */
public class StoreInfoTask extends Task<StoreInfoTask.Method> {

	/**
	 * ��������
	 */
	public enum Method {
		Create, Update
	}

	private int recver;
	
	/**
	 * �ֿ�ID
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
	 * �ֿ�����
	 */
	private String name;

	/**
	 * �ֿ�״̬
	 */
	private StoreStatus status;

	/**
	 * ����ʡ��
	 */
	private String provinceCode;

	/**
	 * ���ڳ���
	 */
	private String cityCode;

	/**
	 * ��������
	 */
	private String townCode;

	/**
	 * ��ַ
	 */
	private String address;

	/**
	 * �ʱ�
	 */
	private String postcode; 

	/**
	 * �ջ���
	 */
	private String consignee;

	/**
	 * �ֻ�
	 */
	private String mobileNo;
 

	/**
	 * ��Ӧ�����Ա
	 */
	private GUID[] keeperIds;
	
	/**
	 * ��λ
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
	 * ��ȡ�ֿ�Ļ�λ����
	 * @param shelfCount
	 */
	public int getShelfCount() {
		return shelfCount;
	}
	/**
	 * ���òֿ�Ļ�λ����
	 * @param shelfCount
	 */
	public void setShelfCount(int shelfCount) {
		this.shelfCount = shelfCount;
	}
	/**
	 * ��òֿ��λ��Ĭ�ϲ���
	 * @param shelfCount
	 */
	public int getDefaultTiersCount() {
		return defaultTiersCount;
	}
	/**
	 * ���òֿ��λ��Ĭ�ϲ���
	 * @param shelfCount
	 */
	public void setDefaultTiersCount(int defaultTiersCount) {
		this.defaultTiersCount = defaultTiersCount;
	}
	
	public interface ShelfInfoItem{
		/**
		 * ��λID
		 * @return
		 */
		public GUID getId();
		
		/**
		 * ȡ�û�λ���
		 * @return
		 */
		public int getShelfNo();
		
		/**
		 * ȡ�û�λ����
		 * @return
		 */
		public int getTiersCount();
		
	}
	
}
