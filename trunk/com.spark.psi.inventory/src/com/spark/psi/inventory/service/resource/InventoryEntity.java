package com.spark.psi.inventory.service.resource;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Inventory;

/**
 * <p>
 * �����Ϣʵ��
 * </p>
 */
@StructClass
public class InventoryEntity implements Inventory {
	private GUID id;//	�б�ʶ
	private GUID storeId;//	�ֿ��ʶ
	private GUID stockId;//	�����ʶ
	private double initCount;//	��ʼ������
	private double initAmount;//	��ʼ�����
	private double initCost;//	��ʼ����λ�ɱ�
	private String name;//	�������
	private String code;//	�������
	private String stockNo;//	�������
	private double count;//	�������
	private double amount;//	������
	private String unit;//	�����λ
	private String spec;//	������
	private double onWayCount;//	�ɹ���;����
	private double toDeliverCount;//	��������
	private double upperLimitCount;//	�����������
	private double lowerLimitCount;//	�����������
	private double upperLimitAmount;//	���������
	private String inventoryType;//	�������
	private double lockedCount;//	�����������
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
