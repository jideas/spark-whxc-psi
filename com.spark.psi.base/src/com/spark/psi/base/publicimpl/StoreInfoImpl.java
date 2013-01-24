package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.store.entity.ShelfItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;

/**
 * �ֿ���ϸ��Ϣ<br>
 * ��ѯ������ͨ���ֿ�IDֱ�Ӳ�ѯStoreInfo����
 */
public class StoreInfoImpl implements StoreInfo {

	protected int recver;
	protected ShelfItem[] shelfItems;
	/**
	 * �ֿ�ID
	 */
	protected GUID id;

	/**
	 * �ֿ�����
	 */
	protected String name;

	/**
	 * �ֿ�״̬
	 */
	protected StoreStatus status;

	/**
	 * ����ʡ��
	 */
	protected EnumEntity province;

	/**
	 * ���ڳ���
	 */
	protected EnumEntity city;

	/**
	 * ��������
	 */
	protected EnumEntity town;

	/**
	 * ��ַ
	 */
	protected String address;

	/**
	 * �ʱ�
	 */
	protected String postcode;

	/**
	 * �ջ���
	 */
	protected String consignee;

	/**
	 * �ֻ�
	 */
	protected String mobileNo;

	/**
	 * ��Ӧ�����Ա
	 */
	protected EmployeeInfo[] keepers;
	
	/**
	 * �ֿ���
	 */
	protected String storeNo;
	
	/**
	 * ��λ��
	 */
	protected int shelfCount;
	
	/**
	 * Ĭ�ϲ���
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
