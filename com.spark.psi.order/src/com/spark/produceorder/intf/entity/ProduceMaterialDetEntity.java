package com.spark.produceorder.intf.entity;

import com.jiuqi.dna.core.type.GUID;

public class ProduceMaterialDetEntity {

	private GUID id;//	�б�ʶ
	private GUID billsId;//	����GUID
	private GUID MaterialId;//	��ƷGuid
	private String MaterialCode;//	��Ʒ���
	private String MaterialNo;//	��Ʒ����
	private String MaterialName;//	��Ʒ����
	private String MaterialSpec;//	��Ʒ���
	private String unit;//	��λ
	private int MaterialScale;//	��ƷС��λ��
	private double count;//	����
	private double receivedCount;//	��������
	private double returnedCount;//	��������
	private GUID storeId;//	�ֿ�ID
	private String storeName;//	�ֿ�����
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}
	public GUID getMaterialId() {
		return MaterialId;
	}
	public void setMaterialId(GUID materialId) {
		MaterialId = materialId;
	}
	public String getMaterialCode() {
		return MaterialCode;
	}
	public void setMaterialCode(String materialCode) {
		MaterialCode = materialCode;
	}
	public String getMaterialNo() {
		return MaterialNo;
	}
	public void setMaterialNo(String materialNo) {
		MaterialNo = materialNo;
	}
	public String getMaterialName() {
		return MaterialName;
	}
	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}
	public String getMaterialSpec() {
		return MaterialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		MaterialSpec = materialSpec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getMaterialScale() {
		return MaterialScale;
	}
	public void setMaterialScale(int materialScale) {
		MaterialScale = materialScale;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(double receivedCount) {
		this.receivedCount = receivedCount;
	}
	public double getReturnedCount() {
		return returnedCount;
	}
	public void setReturnedCount(double returnedCount) {
		this.returnedCount = returnedCount;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
