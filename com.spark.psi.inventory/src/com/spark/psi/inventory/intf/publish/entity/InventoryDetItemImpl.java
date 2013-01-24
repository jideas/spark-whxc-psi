package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryDetItem;

public class InventoryDetItemImpl implements InventoryDetItem {

	private GUID id;//	��¼��ʶ
	private GUID shelfId;//	��λ��ʶ
	private int shelfNo;//	��λ���
	private int tiersNo;//	������ڲ���
	private GUID stockId;//	�����ʶ
	private double count;//	�������
	private long produceDate;//	��������
	private GUID inventoryId;//	���id
	private GUID storeId;//	�ֿ�id
	//��������
	private String storeName;//�ֿ�����
	private String name;//�������
	private String code;//�������
	private String stockNo;//�������
	private String spec;//�������
	private String unit;//�������
	private String[]  properties;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getShelfId() {
		return shelfId;
	}
	public void setShelfId(GUID shelfId) {
		this.shelfId = shelfId;
	}
	public int getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(int shelfNo) {
		this.shelfNo = shelfNo;
	}
	public int getTiersNo() {
		return tiersNo;
	}
	public void setTiersNo(int tiersNo) {
		this.tiersNo = tiersNo;
	}
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public long getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(long produceDate) {
		this.produceDate = produceDate;
	}
	public GUID getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(GUID inventoryId) {
		this.inventoryId = inventoryId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setProperties(String[] properties) {
		this.properties = properties;
	}
	public String[] getProperties() {
		return properties;
	}

}
