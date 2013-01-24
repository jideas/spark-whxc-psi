package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class StoreOrmEntity {
	
	protected GUID id;//	�б�ʶ
	protected int recver;//	�а汾
	protected String storeNo;//	�ֿ���
	protected String storeName;//	�ֿ�����
	protected String namePY;//	����ƴ��
	protected String consignee;//	�ջ���
	protected String mobileNo;//	�ֻ���
	protected String province;//	ʡ��
	protected String city;//	����
	protected String town;//	����
	protected String address;//	��ַ
	protected String postCode;//	�ʱ�
	protected int shelfCount;//	��λ����
	protected int defaultTiersCount;//	Ĭ�ϲ���
	protected String status;//	�ֿ�״̬
	protected String storeType;//	�ֿ�����
	protected long createDate;//	��������
	protected GUID creatorId;//	������Guid
	protected String creator;//	������
	
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
