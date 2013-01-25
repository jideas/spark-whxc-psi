package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * 仓库初始化商品条目<br>
 * 查询方法：使用仓库ID查询StoreInitGoodsItem列表
 */
public final class InitInventoryItem {

	/**
	 * 存货ID
	 */
	private GUID stockId;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 条码
	 */
	private String stockNo;
	
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 规格
	 */
	private String spec;
	
	/**
	 * 保质期
	 */
	private int shelfLife;
	
	/**
	 * 属性
	 */
	private String properties;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 初始数量
	 */
	private double count;
	
	private int scale;

	/**
	 * 平均库存成本
	 */
	private double averageCost;

	/**
	 * 库存金额
	 */
	private double amount;
	
	private InventoryType inventoryType = InventoryType.Materials;
	
	private InitInventoryDetItem[] inventoryDetItems;

	/**
	 * @return the goodsItemId
	 */
	public GUID getStockId() {
		return stockId;
	}

	/**
	 * @param goodsItemId
	 *            the goodsItemId to set
	 */
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the goodsCode
	 */
	public String getsCode() {
		return code;
	}

	/**
	 * @param goodsCode
	 *            the goodsCode to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the goodsName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}

	/**
	 * @return the averageCost
	 */
	public double getAverageCost() {
		return averageCost;
	}

	/**
	 * @param averageCost
	 *            the averageCost to set
	 */
	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getProperties() {
		return properties;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryDetItems(InitInventoryDetItem[] inventoryDetItems) {
		this.inventoryDetItems = inventoryDetItems;
	}

	public InitInventoryDetItem[] getInventoryDetItems() {
		return inventoryDetItems;
	}

	public int getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	public int getScale() {
		return GoodsScale.PSI_SCALE;
	}

	public void setScale(int scale) {
		this.scale = scale;
	} 
	
	
	
}
