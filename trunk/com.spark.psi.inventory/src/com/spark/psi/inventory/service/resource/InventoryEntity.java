package com.spark.psi.inventory.service.resource;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Inventory;

/**
 * <p>
 * 库存信息实现
 * </p>
 */
@StructClass
public class InventoryEntity implements Inventory {
	private GUID id;//	行标识
	private GUID storeId;//	仓库标识
	private GUID stockId;//	存货标识
	private double initCount;//	初始化数量
	private double initAmount;//	初始化金额
	private double initCost;//	初始化单位成本
	private String name;//	存货名称
	private String code;//	存货编码
	private String stockNo;//	存货条码
	private double count;//	存货数量
	private double amount;//	存货金额
	private String unit;//	存货单位
	private String spec;//	存货规格
	private double onWayCount;//	采购在途数量
	private double toDeliverCount;//	发货需求
	private double upperLimitCount;//	库存数量上限
	private double lowerLimitCount;//	库存数量下限
	private double upperLimitAmount;//	库存金额上限
	private String inventoryType;//	库存类型
	private double lockedCount;//	锁定库存数量
	private int scale;
	private String properties;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public double getInitCount() {
		return initCount;
	}
	public void setInitCount(double initCount) {
		this.initCount = initCount;
	}
	public double getInitAmount() {
		return initAmount;
	}
	public void setInitAmount(double initAmount) {
		this.initAmount = initAmount;
	}
	public double getInitCost() {
		return initCost;
	}
	public void setInitCost(double initCost) {
		this.initCost = initCost;
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
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public double getOnWayCount() {
		return onWayCount;
	}
	public void setOnWayCount(double onWayCount) {
		this.onWayCount = onWayCount;
	}
	public double getToDeliverCount() {
		return toDeliverCount;
	}
	public void setToDeliverCount(double toDeliverCount) {
		this.toDeliverCount = toDeliverCount;
	}
	public double getUpperLimitCount() {
		return upperLimitCount;
	}
	public void setUpperLimitCount(double upperLimitCount) {
		this.upperLimitCount = upperLimitCount;
	}
	public double getLowerLimitCount() {
		return lowerLimitCount;
	}
	public void setLowerLimitCount(double lowerLimitCount) {
		this.lowerLimitCount = lowerLimitCount;
	}
	public double getUpperLimitAmount() {
		return upperLimitAmount;
	}
	public void setUpperLimitAmount(double upperLimitAmount) {
		this.upperLimitAmount = upperLimitAmount;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public double getLockedCount() {
		return lockedCount;
	}
	public void setLockedCount(double lockedCount) {
		this.lockedCount = lockedCount;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getScale() {
		return scale;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getProperties() {
		return properties;
	}

}
