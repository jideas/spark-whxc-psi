package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.InventoryDetItem;

public class InventoryDetItemImpl implements InventoryDetItem {

	private GUID id;//	记录标识
	private GUID shelfId;//	货位标识
	private int shelfNo;//	货位编号
	private int tiersNo;//	存货所在层数
	private GUID stockId;//	存货标识
	private double count;//	存货数量
	private long produceDate;//	生产日期
	private GUID inventoryId;//	库存id
	private GUID storeId;//	仓库id
	//其他属性
	private String storeName;//仓库名称
	private String name;//存货名称
	private String code;//存货名称
	private String stockNo;//存货名称
	private String spec;//存货名称
	private String unit;//存货名称
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
