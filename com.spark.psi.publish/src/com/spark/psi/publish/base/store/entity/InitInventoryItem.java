package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * �ֿ��ʼ����Ʒ��Ŀ<br>
 * ��ѯ������ʹ�òֿ�ID��ѯStoreInitGoodsItem�б�
 */
public final class InitInventoryItem {

	/**
	 * ���ID
	 */
	private GUID stockId;

	/**
	 * ����
	 */
	private String code;
	/**
	 * ����
	 */
	private String stockNo;
	
	/**
	 * ����
	 */
	private String name;

	/**
	 * ���
	 */
	private String spec;
	
	/**
	 * ������
	 */
	private int shelfLife;
	
	/**
	 * ����
	 */
	private String properties;

	/**
	 * ��λ
	 */
	private String unit;

	/**
	 * ��ʼ����
	 */
	private double count;
	
	private int scale;

	/**
	 * ƽ�����ɱ�
	 */
	private double averageCost;

	/**
	 * �����
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
